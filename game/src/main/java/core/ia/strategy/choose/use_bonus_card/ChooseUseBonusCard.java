package core.ia.strategy.choose.use_bonus_card;

import core.ia.Player;
import core.ia.strategy.choose.AbstractStrategy;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.Strategy;

public abstract class ChooseUseBonusCard extends AbstractStrategy implements StrategyChooseUseBonusCard {

    public ChooseUseBonusCard(IContext context, StrategyChooseUseBonusCard nextStrategy) {
        super(context,(Strategy) nextStrategy);
    }

    public ChooseUseBonusCard() {
        super(Context.everyTime,null);
    }


    @Override
    public Boolean doChoose(Player player) {
        // Don't know if it really necessary to verify the good ending
        if (context.isVerified(player)) {
            return choose(player);
        } else {
            return (Boolean) nextStrategy.doChoose(player);
        }
    }

    @Override
    public abstract Boolean choose(Player player);
}
