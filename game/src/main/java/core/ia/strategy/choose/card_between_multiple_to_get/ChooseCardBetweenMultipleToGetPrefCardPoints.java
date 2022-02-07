package core.ia.strategy.choose.card_between_multiple_to_get;

import core.cards.Card;
import core.cards.comparator.PrestigePointsCardComparator;
import core.ia.Player;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardBetweenMultipleToGetPrefCardPoints extends ChooseCardBetweenMultipleToGet {

    // Constructor
    public ChooseCardBetweenMultipleToGetPrefCardPoints(IContext context, StrategyChooseCardBetweenMultipleToGet nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardBetweenMultipleToGetPrefCardPoints(StrategyChooseCardBetweenMultipleToGet nextStrategy){
        super(null,nextStrategy);
    }

    public ChooseCardBetweenMultipleToGetPrefCardPoints() {
        super(Context.everyTime, new ChooseCardBetweenMultipleToGetRandom());
    }

    @Override
    public Card choose(Player player, List<Card> cardList) {
        return ChooseCardComparator.getInstance().chooseCardMaxCompare(cardList, new PrestigePointsCardComparator());
    }
}
