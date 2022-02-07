package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.Card;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.ia.PlayerStats;
import core.ia.inventory.Inventory;
import core.util.Renderer;

import java.util.ArrayList;
import java.util.List;

public class CaliceDivinEffect extends AbstractCard {

    public CaliceDivinEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    public void use(Board board, Player player) {
        Renderer.add(String.format("- Utilise %s", getName()));

//              Au moment où vous mettez en jeu le Calice divin, piochez 4 cartes
//        pouvoir et invoquez-en une gratuitement, sans payer son coût
//        d’invocation. Cette carte pouvoir ainsi mise en jeu ne déclenche
//        pas l’effet de l’Arcano Sangsue, du Bâton du printemps et du Vase
//        oublié d’Yjang. Placez les 3 cartes restantes dans la défausse.
//              Vous devez posséder une jauge d’invocation suffisante pour pouvoir
//        mettre en jeu cette nouvelle carte. Dans le cas contraire, la carte piochée
//        est défaussée.


        Inventory inventory = board.getInventories().get(player);
        ArrayList<Card> cards = (ArrayList<Card>) board.getDeck().sampleCard(4);
        Card card = player.chooseCardBetweenMultipleToGet(cards);

        if (card != null) {
            inventory.getHand().addCard(card);
            if (inventory.summonForFree(card, board)) {
                cards.remove(card);
                inventory.discard(board, cards);
                if (card.getEffectFrequency() == EffectFrequency.ON_PLAY) {
                    card.use(board, player);
                }
                PlayerStats ps = board.getPlayersStats().get(player);
                ps.setCardInvoked(ps.getCardInvoked() + 1);
            } else {
                Renderer.add(String.format("-- N'a pas pu invoqué %s par manque de place d'invocation", card.getName()));
                inventory.getHand().getCardsInHand().remove(card);
                inventory.discard(board, cards);
            }
        }

    }

    @Override
    public boolean isMagic() {
        return true;
    }
}
