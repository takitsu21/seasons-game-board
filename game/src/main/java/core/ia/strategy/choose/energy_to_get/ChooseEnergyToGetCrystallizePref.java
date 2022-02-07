package core.ia.strategy.choose.energy_to_get;

import core.board.enums.Energy;
import core.board.enums.Seasons;
import core.ia.Player;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.energy_to_crystalize.ChooseEnergyToCrystallizeCrystallizePref;
import core.ia.strategy.choose.energy_to_crystalize.StrategyChooseEnergyToCrystalize;

import java.util.List;

public class ChooseEnergyToGetCrystallizePref extends ChooseEnergyToGet {

    public ChooseEnergyToGetCrystallizePref(IContext context, StrategyChooseEnergyToGet nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseEnergyToGetCrystallizePref(StrategyChooseEnergyToGet nextStrategy){ super(null, nextStrategy);}
    public ChooseEnergyToGetCrystallizePref() {
        super();
    }

    @Override
    public Energy choose(Player player) {
        Energy energyMaxValue = Energy.values()[0];
        List<Energy> energyList = player.getFacadeIA().getAllTypeEnergy();
        energyList.remove(Energy.ERROR);
        Seasons season = player.getFacadeIA().getSeason();
        for (Energy energy : energyList) {
            if (season.getEnergyValue(energy) > season.getEnergyValue(energyMaxValue)) {
                energyMaxValue = energy;
            }
        }
        return energyMaxValue;
    }
}
