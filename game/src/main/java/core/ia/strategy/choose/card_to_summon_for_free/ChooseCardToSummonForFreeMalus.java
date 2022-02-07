package core.ia.strategy.choose.card_to_summon_for_free;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFromPrefList;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

public class ChooseCardToSummonForFreeMalus extends ChooseCardToSummonForFree {

    public ChooseCardToSummonForFreeMalus(IContext context, StrategyChooseCardToSummonForFree nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardToSummonForFreeMalus(StrategyChooseCardToSummonForFree nextStrategy){ super(null,nextStrategy);}
    public ChooseCardToSummonForFreeMalus() {
        super(Context.everyTime, new ChooseCardToSummonForFreeRandom());
    }

    @Override
    public Card choose(Player player) {
        return ChooseFromPrefList.getInstance().chooseCardToSummonForFree(player, CardUtil.getMalusCards());
    }
}
