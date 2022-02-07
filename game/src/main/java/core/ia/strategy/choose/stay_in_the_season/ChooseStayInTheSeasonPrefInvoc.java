package core.ia.strategy.choose.stay_in_the_season;

import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public class ChooseStayInTheSeasonPrefInvoc extends ChooseStayInTheSeason {

    public ChooseStayInTheSeasonPrefInvoc(IContext context, StrategyChooseStayInTheSeason nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseStayInTheSeasonPrefInvoc(StrategyChooseStayInTheSeason nextStrategy){
        super(Context.everyTime, nextStrategy);
    }

    public ChooseStayInTheSeasonPrefInvoc() {
        super();
    }

    @Override
    public Boolean choose(Player player) {
        return !player.getFacadeIA().getCardInHand().isEmpty();
    }
}
