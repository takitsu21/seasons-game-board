package core.ia.strategy.choose.energy_to_get;

import core.board.enums.Energy;
import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseEnergyToGetRandom extends ChooseEnergyToGet {

    public ChooseEnergyToGetRandom(IContext context, StrategyChooseEnergyToGet nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseEnergyToGetRandom(StrategyChooseEnergyToGet nextStrategy){ super(null, nextStrategy);}
    public ChooseEnergyToGetRandom() {
        super();
    }

    public Energy choose(Player player) {
        List<Energy> energies = player.getFacadeIA().getAllTypeEnergy();
        return (Energy) ChooseRandom.getInstance().chooseRandom(energies);
    }
}
