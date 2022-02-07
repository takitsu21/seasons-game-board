package core.ia.strategy.choose.card_to_summon_for_free;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFromPrefList;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

public class ChooseCardToSummonForFreePrefPermanent extends ChooseCardToSummonForFree {

    public ChooseCardToSummonForFreePrefPermanent(IContext context, StrategyChooseCardToSummonForFree nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardToSummonForFreePrefPermanent(StrategyChooseCardToSummonForFree nextStrategy){ super(null,nextStrategy);}
    public ChooseCardToSummonForFreePrefPermanent() {
        super(Context.everyTime, new ChooseCardToSummonForFreeRandom());
    }

    @Override
    public Card choose(Player player) {
        return ChooseFromPrefList.getInstance().chooseCardToSummonForFree(player, CardUtil.getPermanentEffectCardsClassIn(player.getFacadeIA().getCardInHand()));
    }
}
