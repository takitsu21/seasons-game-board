package core.ia.strategy.choose.card_to_summon_for_free;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.ChooseUtil;

public class ChooseCardToSummonForFreePrefActivate extends ChooseCardToSummonForFree {
    public ChooseCardToSummonForFreePrefActivate(IContext context, StrategyChooseCardToSummonForFree nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardToSummonForFreePrefActivate(StrategyChooseCardToSummonForFree nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseCardToSummonForFreePrefActivate() {
        this(Context.everyTime, new ChooseCardToSummonForFreeRandom());
    }

    public Card choose(Player player) {
        return ChooseUtil.getInstance().chooseCardPredictor(
                player.getFacadeIA().getCardInHand(),
                c -> c.isActivable() && player.getFacadeIA().isSummonable(c, c.getEnergyCost()));
    }
}
