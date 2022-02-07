package core.ia;

import core.cards.Card;
import core.cards.effects.DeDeLaMaliceEffect;
import core.game.GameController;
import core.game.states.Action;
import core.game.states.Move;
import core.util.Renderer;
import util.Util;

import java.util.logging.Level;

public class MonteCarloTurnLoop extends PlayerTurnLoop {
    public MonteCarloTurnLoop(Player player, GameController controller) {
        super(player, controller);
    }

    @Override
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
        try {
            EnumPlayerAction[] actions = ((MonteCarlo) player).generateMoves();
            for (EnumPlayerAction action : actions) {
                makeAction(action, new Move());
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        if (inventory.getBonus().getInUseCrystallizeBonus()) {
            inventory.setBonusCrystal(inventory.getBonusCrystal() - 1);
            inventory.getBonus().setInUseCrystallizeBonus(false);
        }
        inventory.setCanCrystalize(false);

    }
}
