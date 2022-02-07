package core.ia.strategy.choose.go_to_the_next_season;

import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;


public class ChooseGoToTheNextSeasonRandom extends ChooseGoToTheNextSeason {

    public ChooseGoToTheNextSeasonRandom(IContext context, StrategyChooseGoToTheNextSeason nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseGoToTheNextSeasonRandom() {
        super();
    }

    @Override
    public Boolean choose(Player player) {
        return ChooseRandom.getInstance().chooseRandom();
    }

}
