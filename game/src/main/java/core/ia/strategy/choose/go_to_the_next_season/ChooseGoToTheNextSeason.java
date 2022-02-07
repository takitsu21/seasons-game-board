package core.ia.strategy.choose.go_to_the_next_season;

import core.ia.Player;
import core.ia.strategy.choose.AbstractStrategy;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.Strategy;

public abstract class ChooseGoToTheNextSeason extends AbstractStrategy implements StrategyChooseGoToTheNextSeason {
    public ChooseGoToTheNextSeason(IContext context, StrategyChooseGoToTheNextSeason nextStrategy) {
        super(context, (Strategy) nextStrategy);
    }

    public ChooseGoToTheNextSeason() {
        super(Context.everyTime, null);
    }

    @Override
    public Boolean doChoose(Player player) {
        if (context.isVerified(player)) {
            return choose(player);
        } else {
            return  (Boolean) nextStrategy.doChoose(player);
        }
    }

    @Override
    public abstract Boolean choose(Player player);
}
