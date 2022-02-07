package core.ia.strategy.choose.go_to_the_previous_season;

import core.board.enums.Energy;
import core.board.enums.Seasons;
import core.ia.Player;
import core.ia.strategy.choose.IContext;

public class ChooseGoToThePreviousSeasonPrefCrystallize extends ChooseGoToThePreviousSeason {

    public ChooseGoToThePreviousSeasonPrefCrystallize(IContext context, StrategyChooseGoToThePreviousSeason nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseGoToThePreviousSeasonPrefCrystallize() {
        super();
    }

    @Override
    public Boolean choose(Player player) {
        Seasons season = player.getFacadeIA().getPreviousSeason();
        for(Energy energy: player.getFacadeIA().getEnergyStock(player)){
            if(season.getMaxEnergyBySeason()==energy){
                return true;
            }
        }
        return false;
    }
}
