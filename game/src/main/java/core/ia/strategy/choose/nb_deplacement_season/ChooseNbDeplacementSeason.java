package core.ia.strategy.choose.nb_deplacement_season;

import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public abstract class ChooseNbDeplacementSeason implements StrategyChooseNbDeplacementSeason {
    private IContext context;
    private StrategyChooseNbDeplacementSeason nextStrategy;

    public ChooseNbDeplacementSeason(IContext context, StrategyChooseNbDeplacementSeason nextStrategy) {
        this.context = context;
        this.nextStrategy = nextStrategy;
    }

    public ChooseNbDeplacementSeason() {
        this.context = Context.everyTime;
        this.nextStrategy = null;
    }

    @Override
    public int doChoose(Player player, int min, int max) {
        if (context == null || context.isVerified(player)){
            Integer res = choose(player,min,max);
            return (Integer) choose(player,min,max);
        }
        else {
            return nextStrategy.doChoose(player,min,max);
        }
    }

    @Override
    public abstract int choose(Player player, int min, int max);
}
