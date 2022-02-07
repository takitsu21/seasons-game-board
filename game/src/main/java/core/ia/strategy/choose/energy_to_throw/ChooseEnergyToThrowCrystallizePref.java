package core.ia.strategy.choose.energy_to_throw;

import core.board.enums.Energy;
import core.board.enums.Seasons;
import core.ia.Player;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.energy_to_get.ChooseEnergyToGet;
import core.ia.strategy.choose.energy_to_get.StrategyChooseEnergyToGet;

import java.util.List;

public class ChooseEnergyToThrowCrystallizePref extends ChooseEnergyToThrow {

    public ChooseEnergyToThrowCrystallizePref(IContext context, StrategyChooseEnergyToThrow nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseEnergyToThrowCrystallizePref(StrategyChooseEnergyToThrow nextStrategy){ super(null, nextStrategy);}
    public ChooseEnergyToThrowCrystallizePref() {
        super();
    }

    @Override
    public Energy choose(Player player) {
        List<Energy> energies = player.getFacadeIA().getEnergyStock(player);
        Energy energyMinValue = Energy.values()[0];
        List<Energy> energyList = player.getFacadeIA().getAllTypeEnergy();
        Seasons season = player.getFacadeIA().getSeason();
        for (Energy energy : energyList) {
            if (season.getEnergyValue(energy) < season.getEnergyValue(energyMinValue)) {
                if (energies.contains(energy)) {
                    energyMinValue = energy;
                }
            }
        }
        return energyMinValue;
    }
}
