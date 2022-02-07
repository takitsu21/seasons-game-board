package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.Card;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.ia.inventory.Invocation;
import core.util.Renderer;

import java.util.List;

public class SyllasLeFideleEffect extends AbstractCard {

    public SyllasLeFideleEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    public void use(Board board, Player player) {
        Renderer.add(String.format("- Utilise %s", getName()));

        //Au moment où vous mettez en jeu Syllas le fidèle, chaque adversaire doit sacrifier une carte pouvoir en jeu qu’il possède.

        for (Player currentPlayer : board.getPlayers()) {
            if (currentPlayer != player) {
                Inventory currentInventory = board.getInventories().get(currentPlayer);
                Invocation currentInvocation = currentInventory.getInvocation();
                Card cardToDelete = currentPlayer.chooseCardToDelete(currentInvocation.getCardsOnBoard());
                if (cardToDelete != null){
                    if (currentInvocation.removeCardFromBoard(cardToDelete)) {
                        currentInventory.discard(board, cardToDelete);
                        Renderer.add(String.format("-- %s sacrifie %s", currentPlayer.getName(), cardToDelete.getName()));
                    } else {
                        Renderer.add(String.format("-- %s n'a pas de carte à sacrifier", currentPlayer.getName()));
                    }
                }
                else{
                    Renderer.add(String.format("-- %s n'a pas de carte à sacrifier", currentPlayer.getName()));
                }


            }
        }
    }
}
