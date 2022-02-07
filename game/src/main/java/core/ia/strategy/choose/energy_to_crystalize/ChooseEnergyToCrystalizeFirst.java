package core.ia.strategy.choose.energy_to_crystalize;

import core.board.enums.Energy;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseEnergyToCrystalizeFirst extends ChooseEnergyToCrystalize {

    public ChooseEnergyToCrystalizeFirst(IContext context, StrategyChooseEnergyToCrystalize nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseEnergyToCrystalizeFirst(StrategyChooseEnergyToCrystalize nextStrategy){ super(null, nextStrategy);}
    public ChooseEnergyToCrystalizeFirst() {
        super();
    }

    public Energy choose(Player player) {
        List<Energy> energies = player.getFacadeIA().getEnergyStock(player);
        return (Energy) ChooseFirst.getInstance().chooseFirst(energies);
    }

}
