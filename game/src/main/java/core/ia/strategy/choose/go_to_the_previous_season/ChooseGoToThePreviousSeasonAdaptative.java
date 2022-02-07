package core.ia.strategy.choose.go_to_the_previous_season;

import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.IContext;

public class ChooseGoToThePreviousSeasonAdaptative extends ChooseGoToThePreviousSeason {
    Adaptative self;

    public ChooseGoToThePreviousSeasonAdaptative(IContext context, StrategyChooseGoToThePreviousSeason nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseGoToThePreviousSeasonAdaptative(Adaptative player) {
        super();
        self = player;
    }

    @Override
    public Boolean choose(Player player) {
        self.analyzeOpponentStrategy();
        boolean result = self.chooseGoToThePreviousSeason();
        self.setStrategiesToAdaptative();
        return result;
    }
}