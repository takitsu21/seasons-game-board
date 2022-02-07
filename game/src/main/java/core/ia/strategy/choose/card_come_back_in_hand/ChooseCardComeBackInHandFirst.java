package core.ia.strategy.choose.card_come_back_in_hand;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardComeBackInHandFirst extends ChooseCardComeBackInHand {

    public ChooseCardComeBackInHandFirst(IContext context, StrategyChooseCardComeBackInHand nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardComeBackInHandFirst(StrategyChooseCardComeBackInHand nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseCardComeBackInHandFirst() {
        super();
    }

    public Card choose(Player player) {
        List<Card> cards = player.getFacadeIA().getMagicCardsOnBoard();
        return (Card) ChooseFirst.getInstance().chooseFirst(cards);
    }
}
