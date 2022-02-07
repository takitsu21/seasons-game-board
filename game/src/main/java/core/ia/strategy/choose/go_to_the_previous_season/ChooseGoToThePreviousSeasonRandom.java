package core.ia.strategy.choose.go_to_the_previous_season;

import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;


public class ChooseGoToThePreviousSeasonRandom extends ChooseGoToThePreviousSeason {

    public ChooseGoToThePreviousSeasonRandom(IContext context, StrategyChooseGoToThePreviousSeason nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseGoToThePreviousSeasonRandom() {
        super();
    }

    @Override
    public Boolean choose(Player player) {
        return ChooseRandom.getInstance().chooseRandom();
    }

}
