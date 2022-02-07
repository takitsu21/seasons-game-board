package core.ia.strategy.choose.card_to_delete;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardToDeleteRandom extends ChooseCardToDelete {

    public ChooseCardToDeleteRandom(IContext context, StrategyChooseCardToDelete nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardToDeleteRandom(StrategyChooseCardToDelete nextStrategy){ super(null, nextStrategy);}
    public ChooseCardToDeleteRandom() {
        super();
    }

    @Override
    public Card choose(Player player) {
        List<Card> cards = player.getFacadeIA().getCardOnBoard();
        return (Card) ChooseRandom.getInstance().chooseRandom(cards);
    }
}
