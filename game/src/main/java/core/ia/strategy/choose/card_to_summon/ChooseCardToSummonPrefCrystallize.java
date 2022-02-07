package core.ia.strategy.choose.card_to_summon;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFromPrefList;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

public class ChooseCardToSummonPrefCrystallize extends ChooseCardToSummon {

    public ChooseCardToSummonPrefCrystallize(IContext context, StrategyChooseCardToSummon nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardToSummonPrefCrystallize(StrategyChooseCardToSummon nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseCardToSummonPrefCrystallize() {
        super(Context.everyTime, new ChooseCardToSummonRandom());
    }

    @Override
    public Card choose(Player player) {
        return ChooseFromPrefList.getInstance().chooseCardToSummon(player, CardUtil.getCrystalCards());
    }
}
