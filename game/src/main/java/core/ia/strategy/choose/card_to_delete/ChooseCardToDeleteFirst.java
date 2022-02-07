package core.ia.strategy.choose.card_to_delete;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardToDeleteFirst extends ChooseCardToDelete {

    public ChooseCardToDeleteFirst(IContext context, StrategyChooseCardToDelete nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardToDeleteFirst(StrategyChooseCardToDelete nextStrategy){ super(null,nextStrategy);}
    public ChooseCardToDeleteFirst() {
        super();
    }

    public Card choose(Player player) {
        List<Card> cards = player.getFacadeIA().getCardOnBoard();
        return (Card) ChooseFirst.getInstance().chooseFirst(cards);
    }
}
