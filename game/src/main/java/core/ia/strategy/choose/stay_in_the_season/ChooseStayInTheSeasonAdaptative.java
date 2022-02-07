package core.ia.strategy.choose.stay_in_the_season;

import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public class ChooseStayInTheSeasonAdaptative extends ChooseStayInTheSeason {
    Adaptative self;

    public ChooseStayInTheSeasonAdaptative(IContext context, StrategyChooseStayInTheSeason nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseStayInTheSeasonAdaptative(StrategyChooseStayInTheSeason nextStrategy){
        super(Context.everyTime, nextStrategy);
    }

    public ChooseStayInTheSeasonAdaptative(Adaptative player) {
        super();
        self = player;
    }

    @Override
    public Boolean choose(Player player) {
        self.analyzeOpponentStrategy();
        boolean result = self.chooseStayInTheSeason();
        self.setStrategiesToAdaptative();
        return result;
    }
}