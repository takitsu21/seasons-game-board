package core.ia.strategy.choose.stay_in_the_season;

import core.ia.Player;
import core.ia.strategy.choose.AbstractStrategy;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.Strategy;

public abstract class ChooseStayInTheSeason extends AbstractStrategy implements StrategyChooseStayInTheSeason {

    public ChooseStayInTheSeason(IContext context, StrategyChooseStayInTheSeason nextStrategy) {
        super(context, (Strategy) nextStrategy);
    }

    public ChooseStayInTheSeason() {
        super(Context.everyTime, null);
    }

    @Override
    public Boolean doChoose(Player player) {
        if (context.isVerified(player)) {
            return choose(player);
        } else {
            return (Boolean) nextStrategy.doTheChoose(player);
        }
    }

    @Override
    public abstract Boolean choose(Player player);
}
