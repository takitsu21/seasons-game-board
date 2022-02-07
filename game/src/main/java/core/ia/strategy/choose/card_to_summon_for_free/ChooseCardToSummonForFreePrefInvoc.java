package core.ia.strategy.choose.card_to_summon_for_free;

import core.cards.Card;
import core.cards.comparator.CostCardComparator;
import core.ia.Player;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardToSummonForFreePrefInvoc extends ChooseCardToSummonForFreeFirst {

    public ChooseCardToSummonForFreePrefInvoc(IContext context, StrategyChooseCardToSummonForFree nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardToSummonForFreePrefInvoc(StrategyChooseCardToSummonForFree nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseCardToSummonForFreePrefInvoc() {
        super(Context.everyTime, new ChooseCardToSummonForFreeRandom());
    }

    public Card choose(Player player) {
        List<Card> cardInHand = player.getFacadeIA().getCardInHand();
        return ChooseCardComparator.getInstance().chooseCardMaxCompare(cardInHand, new CostCardComparator());
    }

}
