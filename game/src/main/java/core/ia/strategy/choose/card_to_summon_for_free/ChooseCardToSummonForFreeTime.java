package core.ia.strategy.choose.card_to_summon_for_free;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFromPrefList;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

public class ChooseCardToSummonForFreeTime extends ChooseCardToSummonForFree {

    public ChooseCardToSummonForFreeTime(IContext context, StrategyChooseCardToSummonForFree nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardToSummonForFreeTime(StrategyChooseCardToSummonForFree nextStrategy){ super(null,nextStrategy);}
    public ChooseCardToSummonForFreeTime() {
        super(Context.everyTime, new ChooseCardToSummonForFreeRandom());
    }

    @Override
    public Card choose(Player player) {
        return ChooseFromPrefList.getInstance().chooseCardToSummonForFree(player, CardUtil.getCardsPlayedWithTime());
    }
}
