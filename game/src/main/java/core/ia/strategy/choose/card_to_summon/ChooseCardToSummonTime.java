package core.ia.strategy.choose.card_to_summon;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFromPrefList;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

public class ChooseCardToSummonTime extends ChooseCardToSummon {

    public ChooseCardToSummonTime(IContext context, StrategyChooseCardToSummon nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardToSummonTime(StrategyChooseCardToSummon nextStrategy){ super(null, nextStrategy);}
    public ChooseCardToSummonTime() {
        super(Context.everyTime, new ChooseCardToSummonRandom());
    }

    @Override
    public Card choose(Player player) {
        return ChooseFromPrefList.getInstance().chooseCardToSummon(player, CardUtil.getCardsPlayedWithTime());
    }
}
