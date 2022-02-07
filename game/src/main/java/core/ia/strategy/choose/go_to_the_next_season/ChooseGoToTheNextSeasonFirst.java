package core.ia.strategy.choose.go_to_the_next_season;

import core.ia.Player;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;


public class ChooseGoToTheNextSeasonFirst extends ChooseGoToTheNextSeason {

    public ChooseGoToTheNextSeasonFirst(IContext context, StrategyChooseGoToTheNextSeason nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseGoToTheNextSeasonFirst() {
        super();
    }

    @Override
    public Boolean choose(Player player) {
        return ChooseFirst.getInstance().chooseFirst();
    }

}
