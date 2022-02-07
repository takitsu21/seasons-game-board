package core.ia.strategy.choose.go_to_the_next_season;

import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.IContext;

public class ChooseGoToTheNextSeasonAdaptative extends ChooseGoToTheNextSeason {
    Adaptative self;

    public ChooseGoToTheNextSeasonAdaptative(IContext context, StrategyChooseGoToTheNextSeason nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseGoToTheNextSeasonAdaptative(Adaptative player) {
        super();
        self = player;
    }

    @Override
    public Boolean choose(Player player) {
        self.analyzeOpponentStrategy();
        boolean result = self.chooseGoToTheNextSeason();
        self.setStrategiesToAdaptative();
        return result;
    }
}