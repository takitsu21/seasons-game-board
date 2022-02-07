package core.ia.strategy.choose.card_to_summon_for_free;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFromPrefList;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

public class ChooseCardToSummonForFreePrefCrystallize extends ChooseCardToSummonForFree {

    public ChooseCardToSummonForFreePrefCrystallize(IContext context, StrategyChooseCardToSummonForFree nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardToSummonForFreePrefCrystallize(StrategyChooseCardToSummonForFree nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseCardToSummonForFreePrefCrystallize() {
        super(Context.everyTime, new ChooseCardToSummonForFreeRandom());
    }

    @Override
    public Card choose(Player player) {
        return ChooseFromPrefList.getInstance().chooseCardToSummonForFree(player, CardUtil.getCrystalCards());
    }
}
