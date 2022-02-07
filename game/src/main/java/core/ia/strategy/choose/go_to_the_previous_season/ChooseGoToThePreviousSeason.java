package core.ia.strategy.choose.go_to_the_previous_season;

import core.ia.Player;
import core.ia.strategy.choose.AbstractStrategy;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.Strategy;

public abstract class ChooseGoToThePreviousSeason extends AbstractStrategy implements StrategyChooseGoToThePreviousSeason {
    public ChooseGoToThePreviousSeason(IContext context, StrategyChooseGoToThePreviousSeason nextStrategy) {
        super(context, (Strategy) nextStrategy);
    }

    public ChooseGoToThePreviousSeason() {
        super(Context.everyTime, null);
    }

    @Override
    public Boolean doChoose(Player player) {
        if (context.isVerified(player)) {
            return (Boolean) choose(player);
        } else {
            return(Boolean) nextStrategy.doChoose(player);
        }
    }

    @Override
    public abstract Boolean choose(Player player);
}
