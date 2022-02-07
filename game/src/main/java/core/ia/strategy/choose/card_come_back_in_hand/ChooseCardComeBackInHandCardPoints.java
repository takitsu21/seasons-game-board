package core.ia.strategy.choose.card_come_back_in_hand;

import core.cards.Card;
import core.cards.comparator.PrestigePointsCardComparator;
import core.ia.Player;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.Strategy;

import java.util.ArrayList;
import java.util.List;

public class ChooseCardComeBackInHandCardPoints extends ChooseCardComeBackInHand {

    public ChooseCardComeBackInHandCardPoints(IContext context, StrategyChooseCardComeBackInHand nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardComeBackInHandCardPoints(StrategyChooseCardComeBackInHand nextStrategy){
        super(null, nextStrategy);
    }

    public ChooseCardComeBackInHandCardPoints() {
        super(Context.everyTime, new ChooseCardComeBackInHandRandom());
    }

    @Override
    public Card choose(Player player) {
        List<Card> cards = player.getFacadeIA().getMagicCardsOnBoard();
        return ChooseCardComparator.getInstance().chooseCardMinCompare(cards, new PrestigePointsCardComparator());
    }
}
