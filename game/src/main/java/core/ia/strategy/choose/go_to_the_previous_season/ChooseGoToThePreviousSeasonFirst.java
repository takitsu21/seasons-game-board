package core.ia.strategy.choose.go_to_the_previous_season;

import core.ia.Player;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;


public class ChooseGoToThePreviousSeasonFirst extends ChooseGoToThePreviousSeason {

    public ChooseGoToThePreviousSeasonFirst(IContext context, StrategyChooseGoToThePreviousSeason nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseGoToThePreviousSeasonFirst() {
        super();
    }

    @Override
    public Boolean choose(Player player) {
        return ChooseFirst.getInstance().chooseFirst();
    }

}
