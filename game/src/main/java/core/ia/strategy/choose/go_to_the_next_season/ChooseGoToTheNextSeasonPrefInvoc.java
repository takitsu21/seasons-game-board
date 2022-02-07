package core.ia.strategy.choose.go_to_the_next_season;

import core.ia.Player;
import core.ia.strategy.choose.IContext;

public class ChooseGoToTheNextSeasonPrefInvoc extends ChooseGoToTheNextSeason {

    public ChooseGoToTheNextSeasonPrefInvoc(IContext context, StrategyChooseGoToTheNextSeason nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseGoToTheNextSeasonPrefInvoc() {
        super();
    }

    @Override
    public Boolean choose(Player player) {
        return player.getFacadeIA().getCardInHand().isEmpty();
    }
}
