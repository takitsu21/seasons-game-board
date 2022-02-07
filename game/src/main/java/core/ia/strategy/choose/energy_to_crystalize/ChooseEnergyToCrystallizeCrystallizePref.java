package core.ia.strategy.choose.energy_to_crystalize;

import core.board.enums.Energy;
import core.board.enums.Seasons;
import core.ia.Player;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseEnergyToCrystallizeCrystallizePref extends ChooseEnergyToCrystalize {

    public ChooseEnergyToCrystallizeCrystallizePref(IContext context, StrategyChooseEnergyToCrystalize nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseEnergyToCrystallizeCrystallizePref(StrategyChooseEnergyToCrystalize nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseEnergyToCrystallizeCrystallizePref() {
        super();
    }

    @Override
    public Energy choose(Player player) {
        List<Energy> energyList = player.getFacadeIA().getEnergyStock(player);
        Energy energyMaxValue = energyList.get(0);
        Seasons season = player.getFacadeIA().getSeason();
        for (Energy energy : energyList) {
            if (season.getEnergyValue(energy) > season.getEnergyValue(energyMaxValue)) {
                energyMaxValue = energy;
            }
        }
        return energyMaxValue;
    }
}
