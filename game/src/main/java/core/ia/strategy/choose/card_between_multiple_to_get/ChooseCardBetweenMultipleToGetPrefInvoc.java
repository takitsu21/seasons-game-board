package core.ia.strategy.choose.card_between_multiple_to_get;

import core.cards.Card;
import core.cards.comparator.CostCardComparator;
import core.ia.Player;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardBetweenMultipleToGetPrefInvoc extends ChooseCardBetweenMultipleToGet {

    // Constructor
    public ChooseCardBetweenMultipleToGetPrefInvoc(IContext context, StrategyChooseCardBetweenMultipleToGet nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardBetweenMultipleToGetPrefInvoc(StrategyChooseCardBetweenMultipleToGet nextStrategy){
        super(null,nextStrategy);
    }

    public ChooseCardBetweenMultipleToGetPrefInvoc() {
        super(Context.everyTime, new ChooseCardBetweenMultipleToGetRandom());
    }


    // Choose
    @Override
    public Card choose(Player player, List<Card> cardList) {
        return ChooseCardComparator.getInstance().chooseCardMinCompare(cardList, new CostCardComparator());
    }
}