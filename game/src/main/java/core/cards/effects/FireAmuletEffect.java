package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.Card;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Renderer;

import java.util.ArrayList;
import java.util.List;

public class FireAmuletEffect extends AbstractCard {

    public FireAmuletEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    @Override
    public void use(Board board, Player player) {
        Renderer.add(String.format("- Utilise %s", getName()));
        //Au moment où vous mettez en jeu l’Amulette de feu, piochez
        //4 cartes pouvoir. Consultez-les et gardez-en une que vous placez
        //dans votre main. Placez les 3 cartes restantes dans la défausse

        Inventory inventory = board.getInventories().get(player);
        ArrayList<Card> cards = (ArrayList<Card>) board.getDeck().sampleCard(4);
        Card card = player.chooseCardBetweenMultipleToGet(cards);
        if (card != null) {
            cards.remove(card);
            inventory.discard(board, cards);
            inventory.addCard(card);
            Renderer.add(String.format("-- Pioche %s grâce à l'Amulette de Feu", card.getName()));
        }
    }

    @Override
    public boolean isMagic() {
        return true;
    }
}
