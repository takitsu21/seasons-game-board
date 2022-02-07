package core.ia.strategy.choose.card_to_summon_for_free;

import core.cards.Card;
import core.cards.comparator.PrestigePointsCardComparator;
import core.ia.Player;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardToSummonForFreePrefCardPoints extends ChooseCardToSummonForFreeFirst {

    public ChooseCardToSummonForFreePrefCardPoints(IContext context, StrategyChooseCardToSummonForFree nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardToSummonForFreePrefCardPoints(StrategyChooseCardToSummonForFree nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseCardToSummonForFreePrefCardPoints() {
        super(Context.everyTime, new ChooseCardToSummonForFreeRandom());
    }

    public Card choose(Player player) {
        List<Card> cardInHand = player.getFacadeIA().getCardInHand();
        return ChooseCardComparator.getInstance().chooseCardMaxCompare(cardInHand, new PrestigePointsCardComparator());
    }
}
