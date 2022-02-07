package core.ia.strategy.choose.energy_to_crystalize;

import core.board.enums.Energy;
import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseEnergyToCrystalizeRandom extends ChooseEnergyToCrystalize {

    public ChooseEnergyToCrystalizeRandom(IContext context, StrategyChooseEnergyToCrystalize nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseEnergyToCrystalizeRandom(StrategyChooseEnergyToCrystalize nextStrategy){ super(null, nextStrategy);}
    public ChooseEnergyToCrystalizeRandom() {
        super();
    }

    public Energy choose(Player player) {
        List<Energy> energies = player.getFacadeIA().getEnergyStock(player);
        return (Energy) ChooseRandom.getInstance().chooseRandom(energies);
    }
}
