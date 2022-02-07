package core.ia.strategy.choose.stay_in_the_season;

import core.ia.Player;
import core.ia.strategy.choose.*;


public class ChooseStayInTheSeasonFirst extends ChooseStayInTheSeason {

    public ChooseStayInTheSeasonFirst(IContext context, StrategyChooseStayInTheSeason nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseStayInTheSeasonFirst(StrategyChooseStayInTheSeason nextStrategy){
        super(Context.everyTime, nextStrategy);
    }

    public ChooseStayInTheSeasonFirst() {
        super();
    }

    @Override
    public Boolean choose(Player player) {
        return ChooseFirst.getInstance().chooseFirst();
    }

}
