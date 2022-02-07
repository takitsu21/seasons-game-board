package core.ia.strategy.choose.go_to_the_next_season;

import core.board.enums.Energy;
import core.board.enums.Seasons;
import core.ia.Player;
import core.ia.strategy.choose.IContext;

public class ChooseGoToTheNextSeasonPrefCrystallize extends ChooseGoToTheNextSeason {

    public ChooseGoToTheNextSeasonPrefCrystallize(IContext context, StrategyChooseGoToTheNextSeason nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseGoToTheNextSeasonPrefCrystallize() {
        super();
    }

    @Override
    public Boolean choose(Player player) {
        Seasons season = player.getFacadeIA().getNextSeason();
        for(Energy energy: player.getFacadeIA().getEnergyStock(player)){
            if(season.getMaxEnergyBySeason()==energy){
                return true;
            }
        }
        return false;
    }
}
