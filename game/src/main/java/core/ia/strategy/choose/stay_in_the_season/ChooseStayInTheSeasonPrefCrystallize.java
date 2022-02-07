package core.ia.strategy.choose.stay_in_the_season;

import core.board.enums.Energy;
import core.board.enums.Seasons;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public class ChooseStayInTheSeasonPrefCrystallize extends ChooseStayInTheSeason {

    public ChooseStayInTheSeasonPrefCrystallize(IContext context, StrategyChooseStayInTheSeason nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseStayInTheSeasonPrefCrystallize(StrategyChooseStayInTheSeason nextStrategy){
        super(Context.everyTime, nextStrategy);
    }

    public ChooseStayInTheSeasonPrefCrystallize() {
        super();
    }

    @Override
    public Boolean choose(Player player) {
        Seasons season = player.getFacadeIA().getSeason();
        for(Energy energy: player.getFacadeIA().getEnergyStock(player)){
            if(season.getMaxEnergyBySeason()==energy){
                return true;
            }
        }
        return false;
    }
}
