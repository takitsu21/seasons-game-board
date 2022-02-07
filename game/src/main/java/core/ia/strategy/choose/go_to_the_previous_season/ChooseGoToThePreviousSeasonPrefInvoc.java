package core.ia.strategy.choose.go_to_the_previous_season;

import core.ia.Player;
import core.ia.strategy.choose.IContext;

public class ChooseGoToThePreviousSeasonPrefInvoc extends ChooseGoToThePreviousSeason {

    public ChooseGoToThePreviousSeasonPrefInvoc(IContext context, StrategyChooseGoToThePreviousSeason nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseGoToThePreviousSeasonPrefInvoc() {
        super();
    }

    @Override
    public Boolean choose(Player player) {
        return !player.getFacadeIA().getCardInHand().isEmpty();
    }
}
