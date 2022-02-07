package core.ia;

import core.board.Board;
import core.board.enums.Energy;
import core.board.enums.Seasons;
import core.cards.Card;
import core.cards.EffectFrequency;
import core.cards.effects.DeDeLaMaliceEffect;
import core.cards.effects.EffectType;
import core.dice.Face;
import core.exception.EnergyException;
import core.exception.SeasonException;
import core.game.GameController;
import core.game.states.Action;
import core.game.states.Move;
import core.ia.inventory.BonusType;
import core.ia.inventory.Inventory;
import core.util.Renderer;
import util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


public class PlayerTurnLoop {
    protected Player player;
    protected Seasons season;
    protected Inventory inventory;
    protected GameController controller;

    public PlayerTurnLoop(Player player, GameController controller) {
        this.player = player;
        this.controller = controller;
        this.season = controller.getBoard().getYear().getCurrentSeason();
        this.inventory = controller.getBoard().getInventories().get(player);
    }

    public void processPlayerTurnLoop() {

        boolean makeAction = true;
        Move currentMovesTurn = new Move();
        var currentGameStatePlayer = controller.getGameStates().getPlayersMoves();
        Renderer.add(String.format("Joueur %s:", player.getName()));

        // Effect of the played card


        // Receive what the dice gave
        for (Card card : inventory.getInvocation().getCardsOnBoard()) {
            if (card instanceof DeDeLaMaliceEffect) {
                if(player.chooseUseDeDeLaMalice()){
                    inventory.getCurrentDice().reRollDice();
                }
            }
        }

        actionDice();
        currentMovesTurn.addAction(new Action(EnumPlayerAction.CHOOSE_DICE,
                inventory.getCurrentDice(), controller.getBoard().getYear().getNbYear(),
                controller.getBoard().getYear().getCurrentSeason()));
        rendererPlayerInventory(controller.getBoard().getInventories().get(player));

        // Action of the player

        while (makeAction) {
            // Choose the action
            makeAction = runOneAction();
        }


        currentGameStatePlayer.get(player).add(currentMovesTurn);

        if (inventory.getBonus().getInUseCrystallizeBonus()) {
            inventory.setBonusCrystal(inventory.getBonusCrystal() - 1);
            inventory.getBonus().setInUseCrystallizeBonus(false);
        }
        inventory.setCanCrystalize(false);

    }

    public boolean runOneAction() {
        EnumPlayerAction action = player.choosePlayerAction();
        return makeAction(action, new Move());
    }

    protected void rendererPlayerInventory(Inventory inventory) {
        Renderer.add(String.format(". Cristaux: %s", inventory.getCrystals()));
        Renderer.add(String.format(". Cartes en main: %s", inventory.getHand().toStringCardInHand()));
        Renderer.add(String.format(". Cartes sur le plateau: %s", inventory.getInvocation().toStringCardOnBoard()));
        Renderer.add(String.format(". Energies: %s", inventory.getEnergyStock().getEnergyStock().toString()));
    }


    public boolean makeAction(EnumPlayerAction action, Move moves) {
        // Make the action
        Action currentAction = null;
        switch (action) {
            case CRYSTALLISE -> {
                if (inventory.getCanCrystalize() && (!inventory.getEnergyStock().getEnergyStock().isEmpty())) {
                    Energy energy = player.chooseEnergyToCrystalize();
                    currentAction = crystalize(energy);
                } else {
                    Renderer.add("- Ne peut pas cristaliser");
                }
            }
            case SUMMON -> {
                List<Card> playersHand = inventory.getHand().getCardsInHand();
                if (!playersHand.isEmpty()) {
                    if (inventory.getInvocation().getInvocationPoints() > inventory.getInvocation().getCardsOnBoard().size()) {
                        currentAction = summon();
                    } else {
                        Renderer.add("- N'a pas assez de points d'invocation");
                    }
                } else {
                    Renderer.add("- N'a pas de cartes a invoquer");
                }
            }
            case BONUS -> {
                BonusType bonusType = player.chooseBonus();
                if (bonusType == null){
                    Renderer.add("- Ne peux utiliser un bonus");
                }
                else {
                    inventory.getBonus().useBonus(bonusType, player, controller.getBoard());
                    currentAction = new Action(action, bonusType, controller.getBoard().getYear().getNbYear(),
                            controller.getBoard().getYear().getCurrentSeason());
                }
            }
            case ACTIVATE_CARD -> {
                currentAction = activateCard();
            }
            case NOTHING -> {
                Renderer.add("- A fini son tour");
                moves.addAction(new Action(action, controller.getBoard().getYear().getNbYear(),
                        controller.getBoard().getYear().getCurrentSeason()));
                return false;
            }
            default -> {
                return false;
            }
        }
        if (currentAction != null) {
            moves.addAction(currentAction);
        }
        return true;
    }

    public Action activateCard() {
        PlayerStats ps = controller.getBoard().getPlayersStats().get(player);
        Card card = player.chooseCardToActivate();
        if(card !=null) {
            Renderer.add(String.format("- Activation de la carte %s", card.getName()));
            card.use(controller.getBoard(), player);
            ps.setCardActivated(ps.getCardActivated() + 1);
            return new Action(EnumPlayerAction.ACTIVATE_CARD, card, controller.getBoard().getYear().getNbYear(),
                    controller.getBoard().getYear().getCurrentSeason());
        }
        return null;
    }


    public Action crystalize(Energy energy) {
        int valueEnergy = 0;
        try {
            //Crystals gained
            valueEnergy = season.getEnergyValue(energy);
            inventory.getEnergyStock().removeEnergy(energy);

            //Stats
            PlayerStats ps = controller.getBoard().getPlayersStats().get(player);
            ps.setCristalsGainedByCristalization(ps.getCristalsGainedByCristalization() + valueEnergy);

            // Renderer
            Renderer.add(String.format("- A cristalisé %s pour %d cristal(aux)", energy, valueEnergy));

            inventory.addCrystals(getCrystalizeBonusesByCard(valueEnergy) + inventory.getBonusCrystal());

            if (inventory.getBonus().getInUseCrystallizeBonus()) {
                Renderer.add("-- Le bonus Cristalisation vous fait obtenir 1 cristal en plus");
            }

        } catch (SeasonException | EnergyException e) {
            Util.logger.log(Level.SEVERE, "Cristallisation", e);
        }
        return new Action(EnumPlayerAction.CRYSTALLISE, energy, valueEnergy,
                controller.getBoard().getYear().getNbYear(),
                controller.getBoard().getYear().getCurrentSeason());
    }

    private int getCrystalizeBonusesByCard(int value) {
        for (Card card : inventory.getInvocation().getCardsOnBoard()) {
            if (card.hasPermanentEffect() && card.getEffectType() == EffectType.CRYSTAL) {
                Renderer.add(String.format(
                        "- %s vous fait obtenir %d cristaux", card.getName(), value));
                return value + 1;
            }
        }
        return value;
    }

    private void draw() {
        Board board = controller.getBoard();
        PlayerStats ps = controller.getBoard().getPlayersStats().get(player);


        Card drawedCard = board.getDeck().drawCard();
        if (drawedCard != null) {
            ps.setCardDrawn(ps.getCardDrawn() + 1);
            Renderer.add(String.format("- A pioché la carte: %s", drawedCard.getName()));
            boolean keepCard = player.chooseToKeepDrawnCard(drawedCard);
            if (!keepCard) {
                inventory.discard(board, drawedCard);
                Renderer.add(String.format("  N'a pas gardé %s", drawedCard.getName()));
            } else {
                inventory.addCard(drawedCard);
                Renderer.add(String.format("  A pris %s dans sa main ", drawedCard.getName()));
            }
        } else {
            Renderer.add("- La pioche est vide");
        }

    }

    public Action summon() {
        // Chooses which to summon and summons it

        Card cardToSummon = player.chooseCardToSummon();
        // If the energyStock is empty and the card need energy to be summon
        if (cardToSummon == null){
            return null;
        }

        if (inventory.summonCard(cardToSummon, controller.getBoard())) {
            //System.out.println("Before invoke: "+inventory.getCardsInHand());
            inventory.triggerVaseAndBaton(controller.getBoard(), player);


            if (cardToSummon.getEffectFrequency() == EffectFrequency.ON_PLAY
                    || cardToSummon.getEffectFrequency() == EffectFrequency.ON_PLAY_AND_EACH_TURN) {
                //System.out.println("Before invoke:"+cardToSummon+" \ninvenotry: "+inventory.getCardsInHand());
                cardToSummon.use(controller.getBoard(), player);
                //System.out.println("After invoke:"+cardToSummon+" \ninvenotry: "+inventory.getCardsInHand());
            }
            //System.out.println("After invoke: "+inventory.getCardsInHand());
            // Stats
            PlayerStats ps = controller.getBoard().getPlayersStats().get(player);
            ps.setCardInvoked(ps.getCardInvoked() + 1);

            return new Action(EnumPlayerAction.SUMMON, cardToSummon, controller.getBoard().getYear().getNbYear(),
                    controller.getBoard().getYear().getCurrentSeason());
        }
        return null;
    }

    public void actionDice() {
        Face faceOfTheDice = inventory.getCurrentDice().getCurrentFace();

        // Add crystals
        inventory.addCrystals(faceOfTheDice.getNbCrystals());

        // Add invocation points
        if (faceOfTheDice.isInvocationSup()) {
            inventory.getInvocation().setInvocationPoints(inventory.getInvocation().getInvocationPoints() + 1);
        }

        // Set fields canCristalize
        if (faceOfTheDice.isCrystalize()) {
            inventory.setCanCrystalize(true);
        }

        // Give the energies
        Energy[] diceEnergies = faceOfTheDice.getEnergy();
        if (diceEnergies != null) {
            for (Energy energy : diceEnergies) {
                Energy toThrow = null;
                if (inventory.getEnergyStock().getEnergyStock().size() == inventory.getEnergyStock().getNbMaxEnergy()) {
                    List<Energy> stock = new ArrayList<>(inventory.getEnergyStock().getEnergyStock());
                    stock.add(energy);
                    toThrow = player.chooseEnergyToThrow();
                }
                inventory.getEnergyStock().addEnergy(energy, toThrow);
            }
        }

        // Give the card
        if (inventory.canDraw()) {
            if (player.chooseUseBonusCard()) {
                inventory.getBonus().useBonus(BonusType.DRAW_2_CARDS, player, controller.getBoard());
            } else {
                draw();
            }
        }
    }

}
