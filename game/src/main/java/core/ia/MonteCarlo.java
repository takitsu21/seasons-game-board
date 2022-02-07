package core.ia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.board.Board;
import core.board.Year;
import core.cards.AbstractCard;
import core.cards.Card;
import core.cards.Deck;
import core.dice.Dice;
import core.dice.DiceSet;
import core.game.GameController;
import core.game.TurnBasedGameLoop;
import core.game.states.GameStates;
import core.game.states.Move;
import core.ia.inventory.Hand;
import core.ia.inventory.Inventory;
import core.ia.strategy.choose.bonus.ChooseBonusRandom;
import core.ia.strategy.choose.card_between_multiple_to_get.ChooseCardBetweenMultipleToGetRandom;
import core.ia.strategy.choose.card_come_back_in_hand.ChooseCardComeBackInHandRandom;
import core.ia.strategy.choose.card_for_init_hand.ChooseCardForInitHandRandom;
import core.ia.strategy.choose.card_naria_la_prophetesse.ChooseCardNariaLaProphetesseRandom;
import core.ia.strategy.choose.card_to_delete.ChooseCardToDeleteRandom;
import core.ia.strategy.choose.card_to_summon.ChooseCardToSummonRandom;
import core.ia.strategy.choose.card_to_summon_for_free.ChooseCardToSummonForFreeRandom;
import core.ia.strategy.choose.cards_to_activate.ChooseCardsToActivateRandom;
import core.ia.strategy.choose.copy_energy_from_player.ChoosePlayerEnergyToCopyRandom;
import core.ia.strategy.choose.dice.ChooseDiceRandom;
import core.ia.strategy.choose.energy_to_crystalize.ChooseEnergyToCrystalizeRandom;
import core.ia.strategy.choose.energy_to_get.ChooseEnergyToGetRandom;
import core.ia.strategy.choose.energy_to_throw.ChooseEnergyToThrowRandom;
import core.ia.strategy.choose.go_to_the_next_season.ChooseGoToTheNextSeasonRandom;
import core.ia.strategy.choose.go_to_the_previous_season.ChooseGoToThePreviousSeasonRandom;
import core.ia.strategy.choose.nb_deplacement_season.ChooseNbDeplacementSeasonRandom;
import core.ia.strategy.choose.player_action.ChoosePlayerActionRandom;
import core.ia.strategy.choose.similar_energy_to_delete.ChooseSimilarEnergiesToDeleteRandom;
import core.ia.strategy.choose.stay_in_the_season.ChooseStayInTheSeasonRandom;
import core.ia.strategy.choose.to_keep_drawn_card.ChooseToKeepDrawnCardRandom;
import core.ia.strategy.choose.use_bonus_card.ChooseUseBonusCardRandom;
import core.ia.strategy.choose.use_de_de_la_malice.ChooseUseDeDeLaMaliceRandom;
import core.util.Config;
import core.util.ConfigMonteCarlo;
import util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MonteCarlo extends Player {
    private Board board;
    private boolean useMonteCarlo = true;
    private int nbBranch;
    private int depth;
    private int nbAction;

    public MonteCarlo(String name, Config config, ConfigMonteCarlo configMonteCarlo, TypeAIPlayer playerType) {
        super(name, config, playerType);
        this.nbBranch = configMonteCarlo.getNbBranch();
        this.depth = configMonteCarlo.getDepth();
        this.nbAction = configMonteCarlo.getNbAction();
    }

    public EnumPlayerAction[] generateMoves() throws CloneNotSupportedException {

        int max = Integer.MIN_VALUE;
        EnumPlayerAction[] moves = new EnumPlayerAction[0];

        int indexCurrentPlayer = 0;
        for (int i = 0; i < board.getPlayers().length; i++) {
            if (board.getPlayers()[i] == this) {
                indexCurrentPlayer = i;
            }
        }

        for (int branch = 0; branch < nbBranch; branch++) {
            Board cloneBoard = (Board) board.clone();
            Player[] playerList = cloneBoard.getPlayers();

            Player cloneCurrentPlayer = playerList[indexCurrentPlayer];
            for (int p = 0; p < playerList.length; p++) {
                if (p != indexCurrentPlayer) {
                    playerList[p] = transformPlayerRandom(playerList[p]);
                }
            }

            GameController cloneController = new GameController(cloneBoard);

            TurnBasedGameLoop turnBasedGameLoop = new TurnBasedGameLoop(cloneController, config);
            MonteCarloTurnLoop monteCarloTurnLoop = new MonteCarloTurnLoop(cloneCurrentPlayer, cloneController);


            Inventory inventory = cloneBoard.getInventories().get(cloneCurrentPlayer);
            List<EnumPlayerAction> possibleChoice = inventory.findPossibleAction(cloneBoard);


            int randomActions = Util.getNextInt(nbAction);
            EnumPlayerAction[] temporaryChoice = new EnumPlayerAction[randomActions + 1];

            for (int numAction = 0; numAction < randomActions; numAction++) {
                EnumPlayerAction action = cloneCurrentPlayer.choosePlayerAction();
                if (action == null) {
                    action = EnumPlayerAction.NOTHING;
                }
                temporaryChoice[numAction] = action;
                monteCarloTurnLoop.makeAction(action, new Move());

            }
            temporaryChoice[randomActions] = EnumPlayerAction.NOTHING;
            monteCarloTurnLoop.makeAction(EnumPlayerAction.NOTHING, new Move());
            if (inventory.getBonus().getInUseCrystallizeBonus()) {
                inventory.setBonusCrystal(inventory.getBonusCrystal() - 1);
                inventory.getBonus().setInUseCrystallizeBonus(false);
            }
            inventory.setCanCrystalize(false);

            ((MonteCarlo) cloneCurrentPlayer).setUseMonteCarlo(false);

            turnBasedGameLoop.turnsOfThePLayers((indexCurrentPlayer + 1) % config.getNbPlayer());
            // Turns of the players
            cloneBoard.setCurrentCursor(cloneBoard.getCurrentCursor() +
                    cloneBoard.getDices().getDice(0).getCurrentFace().getNbAdvance());

            // Rotation of the players
            cloneBoard.setRotation((cloneBoard.getRotation() + 1) % config.getNbPlayer());

            for (int n = 0; n < depth; n++) {
                turnBasedGameLoop.oneLoop();
            }

//            turnBasedGameLoop.processOneGameLoop();

            if (cloneCurrentPlayer.getFacadeIA().calculatePrestigePoints2() > max
                    || cloneBoard.getYear().getNbYear() >= config.getNbYears()) {
                max = cloneCurrentPlayer.getFacadeIA().calculatePrestigePoints();
                moves = temporaryChoice;
            }
        }
        return moves;
    }

    @Override
    public Card[][] chooseCardForInitHand(List<Card> deck) {
        if (!useMonteCarlo) {
            return super.chooseCardForInitHand(deck);
        }
        int max = Integer.MIN_VALUE;
        Card[][] finalChoice = new Card[config.getNbYears()][config.getNbCardsPerYear()];

        TurnBasedGameLoop turnBasedGameLoop;

        int indexCurrentPlayer = 0;
        for (int i = 0; i < board.getPlayers().length; i++) {
            if (board.getPlayers()[i] == this) {
                indexCurrentPlayer = i;
            }
        }

        for (int i = 0; i < nbBranch; i++) {
            try {
                Board cloneBoard = (Board) board.clone();
                Player[] playerList = cloneBoard.getPlayers();
                Player cloneCurrentPlayer = playerList[indexCurrentPlayer];
                turnBasedGameLoop = new TurnBasedGameLoop(new GameController(cloneBoard), config);

                for (int p = 0; p < playerList.length; p++) {
                    if (p != indexCurrentPlayer) {
                        playerList[p] = transformPlayerRandom(playerList[p]);
                    }
                }
                List<Card> cloneDeck = new ArrayList<>(deck);


                Card[][] temporaryChoice = new Card[0][];

                for (int p = indexCurrentPlayer; p < playerList.length; p++) {
                    if (playerList[p].equals(cloneCurrentPlayer)) {
                        temporaryChoice = super.chooseCardForInitHand(cloneDeck);
                        turnBasedGameLoop.getController().getBoard().getInventories().get(cloneCurrentPlayer).setHand(new Hand(cloneCardsForInitHand(temporaryChoice)));
                    } else {
                        turnBasedGameLoop.getController().getBoard().initHand(playerList[p]);
                    }
                }

                ((MonteCarlo) cloneCurrentPlayer).setUseMonteCarlo(false);

                while (cloneBoard.getYear().updateYearAndSeason(cloneBoard)) {
                    turnBasedGameLoop.oneLoop();
                }


                turnBasedGameLoop.checkEndGamePermanentCard();
                if (cloneCurrentPlayer.getFacadeIA().calculatePrestigePoints2() > max
                        || cloneBoard.getYear().getNbYear() >= config.getNbYears()) {
                    if (cloneBoard.getYear().getNbYear() >= config.getNbYears()) {
                        max = cloneCurrentPlayer.getFacadeIA().calculatePrestigePoints();
                    } else {
                        max = cloneCurrentPlayer.getFacadeIA().calculatePrestigePoints2();
                    }
                    finalChoice = temporaryChoice;
                }

            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

        }
        return finalChoice;
    }

    @Override
    public Dice chooseDice() {
        if (!useMonteCarlo) {
            return super.chooseDice();
        }
        int max = Integer.MIN_VALUE;
        Dice finalChoice = super.chooseDice();

        int indexCurrentPlayer = 0;
        for (int i = 0; i < board.getPlayers().length; i++) {
            if (board.getPlayers()[i].equals(this)) {
                indexCurrentPlayer = i;
                break;
            }
        }

        for (int i = 0; i < nbBranch; i++) {
            try {
                Board cloneBoard = (Board) board.clone();
                GameController cloneGameController = new GameController(cloneBoard);

                Player[] playerList = cloneBoard.getPlayers();
                MonteCarlo cloneCurrentPlayer = (MonteCarlo) playerList[indexCurrentPlayer];

                for (int p = 0; p < playerList.length; p++) {
                    if (p != indexCurrentPlayer) {
                        playerList[p] = transformPlayerRandom(playerList[p]);
                    }
                }

                Dice temporaryChoice = null;

                int rotation = cloneBoard.getRotation();

                int nbTour = (playerList.length - indexCurrentPlayer + rotation);
                if (nbTour > playerList.length) {
                    nbTour = nbTour % playerList.length;
                }


                TurnBasedGameLoop turnBasedGameLoop = new TurnBasedGameLoop(cloneGameController, config);


                for (int j = 0; j < nbTour; j++) {
                    int pMod = (indexCurrentPlayer + j) % playerList.length;

                    if (playerList[pMod].equals(cloneCurrentPlayer)) {
                        temporaryChoice = super.chooseDice();
                        Dice testDice = (Dice) temporaryChoice.clone();
                        cloneBoard.getInventories().get(cloneCurrentPlayer).setCurrentDice(testDice);
                        cloneBoard.getDices().removeDice(temporaryChoice);
                    } else {
                        Dice chosenDice = playerList[pMod].chooseDice();
                        cloneBoard.getInventories().get(playerList[pMod]).setCurrentDice(chosenDice);
                        cloneBoard.getDices().removeDice(chosenDice);
                    }
                }

                cloneCurrentPlayer.setUseMonteCarlo(false);

                turnBasedGameLoop.turnsOfThePLayers(cloneBoard.getRotation());
                // Turns of the players
                cloneBoard.setCurrentCursor(cloneBoard.getCurrentCursor() +
                        cloneBoard.getDices().getDice(0).getCurrentFace().getNbAdvance());

                // Rotation of the players
                cloneBoard.setRotation((rotation + 1) % config.getNbPlayer());

                for (int n = 0; n < depth; n++) {
                    turnBasedGameLoop.oneLoop();
                }

                if (cloneCurrentPlayer.getFacadeIA().calculatePrestigePoints2() > max
                        || cloneBoard.getYear().getNbYear() >= config.getNbYears()) {
                    if (cloneBoard.getYear().getNbYear() >= config.getNbYears()) {
                        max = cloneCurrentPlayer.getFacadeIA().calculatePrestigePoints();
                    } else {
                        max = cloneCurrentPlayer.getFacadeIA().calculatePrestigePoints2();
                    }
                    finalChoice = temporaryChoice;
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

        }

        for (Dice d : board.getDices().getSetOfDices()) {
            if (d.equals(finalChoice)) {
                finalChoice = d;
                break;
            }
        }
        useMonteCarlo = true;

        return finalChoice;

    }


    public Card[][] cloneCardsForInitHand(Card[][] cardsByYear) throws CloneNotSupportedException {
        Card[][] cloneCardsByYear = new Card[cardsByYear.length][cardsByYear[0].length];
        for (int i = 0; i < cardsByYear.length; i++) {
            for (int j = 0; j < cardsByYear[0].length; j++) {
                AbstractCard c = (AbstractCard) cardsByYear[i][j];
                cloneCardsByYear[i][j] = (Card) c.clone();
            }
        }
        return cloneCardsByYear;
    }

    public Player transformPlayerRandom(Player player) {
        player.setChooseBonus(new ChooseBonusRandom());
        player.setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetRandom());
        player.setChooseCardForInitHand(new ChooseCardForInitHandRandom());
        player.setChooseCardsToActivate(new ChooseCardsToActivateRandom());
        player.setChooseCardComeBackInHand(new ChooseCardComeBackInHandRandom());
        player.setChooseCardToSummon(new ChooseCardToSummonRandom());
        player.setChooseCardToSummonForFree(new ChooseCardToSummonForFreeRandom());
        player.setChooseCardToDelete(new ChooseCardToDeleteRandom());
        player.setChooseDice(new ChooseDiceRandom());
        player.setChooseEnergyToCrystallize(new ChooseEnergyToCrystalizeRandom());
        player.setChooseEnergyToGet(new ChooseEnergyToGetRandom());
        player.setChooseEnergyToThrow(new ChooseEnergyToThrowRandom());
        player.setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeleteRandom());
        player.setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyRandom());
        player.setChoosePlayerAction(new ChoosePlayerActionRandom());
        player.setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardRandom());
        player.setChooseUseBonusCard(new ChooseUseBonusCardRandom());
        player.setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceRandom());
        player.setChooseNbDeplacementSeason(new ChooseNbDeplacementSeasonRandom());
        player.setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetesseRandom());
        player.setChooseStayInTheSeason(new ChooseStayInTheSeasonRandom());
        player.setChooseGoToTheNextSeason(new ChooseGoToTheNextSeasonRandom());
        player.setChooseGoToThePreviousSeason(new ChooseGoToThePreviousSeasonRandom());
        if (player instanceof MonteCarlo) {
            ((MonteCarlo) player).setUseMonteCarlo(false);
        }
        return player;
    }

    /*
    Copy current object and the nested one
     */
    public <T> T deepCopy(T object, Class<T> type) {
        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Card.class, new InterfaceAdapter<Card>())
                    .create();
            String toJson =gson.toJson(object, type);
            T newObject = gson.fromJson(toJson , type);
            return newObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    Copy the entire game
     */
    public <T> GameController copyGame(T object, Class<T> type) {
        GameController controller = (GameController) object;
        Board board = controller.getBoard();
        Year year = deepCopy(board.getYear(), Year.class);
        Map inventories = deepCopy(board.getInventories(), Map.class);
        Map playersStats = deepCopy(board.getPlayersStats(), Map.class);
        Deck deck= deepCopy(board.getDeck(), Deck.class);
        Player[] players= deepCopy(board.getPlayers(), Player[].class);
        DiceSet dices= deepCopy(board.getDices(), DiceSet.class);
        int currentCursor = deepCopy(board.getCurrentCursor(), int.class);

        Board copiedBoard = new Board(year, inventories, playersStats, deck, players, dices, new Config(), currentCursor);
        GameController gameController = new GameController(copiedBoard, new GameStates(players));
        return gameController;
    }

    public void setUseMonteCarlo(boolean b) {
        useMonteCarlo = b;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isUseMonteCarlo() {
        return useMonteCarlo;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}