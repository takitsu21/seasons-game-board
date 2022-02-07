package core.ia.strategy.choose.card_come_back_in_hand;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardComeBackInHandRandom extends ChooseCardComeBackInHand {

    public ChooseCardComeBackInHandRandom(IContext context, StrategyChooseCardComeBackInHand nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardComeBackInHandRandom(StrategyChooseCardComeBackInHand nextStrategy){ super(null, nextStrategy);}
    public ChooseCardComeBackInHandRandom() {
        super();
    }

    public Card choose(Player player) {
        List<Card> cards = player.getFacadeIA().getMagicCardsOnBoard();
        return (Card) ChooseRandom.getInstance().chooseRandom(cards);
    }
}
