package core.ia.strategy.choose.card_to_summon;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.ChooseUtil;

public class ChooseCardToSummonPrefActivate extends ChooseCardToSummon {

    public ChooseCardToSummonPrefActivate(IContext context, StrategyChooseCardToSummon nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardToSummonPrefActivate(StrategyChooseCardToSummon nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseCardToSummonPrefActivate() {
        this(Context.everyTime, new ChooseCardToSummonRandom());
    }

    public Card choose(Player player) {
        return ChooseUtil.getInstance().chooseCardPredictor(
                player.getFacadeIA().getCardInHand(),
                c -> c.isActivable() && player.getFacadeIA().isSummonable(c, c.getEnergyCost()));
    }
}
