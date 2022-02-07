package core.ia.strategy.choose.stay_in_the_season;

import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;


public class ChooseStayInTheSeasonRandom extends ChooseStayInTheSeason {

    public ChooseStayInTheSeasonRandom(IContext context, StrategyChooseStayInTheSeason nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseStayInTheSeasonRandom(StrategyChooseStayInTheSeason nextStrategy){
        super(Context.everyTime, nextStrategy);
    }

    public ChooseStayInTheSeasonRandom() {
        super();
    }

    @Override
    public Boolean choose(Player player) {
        return ChooseRandom.getInstance().chooseRandom();
    }

}
