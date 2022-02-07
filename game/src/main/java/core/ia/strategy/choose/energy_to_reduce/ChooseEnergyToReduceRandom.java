package core.ia.strategy.choose.energy_to_reduce;

import core.board.enums.Energy;
import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseEnergyToReduceRandom extends ChooseEnergyToReduce {

    public ChooseEnergyToReduceRandom(IContext context, StrategyChooseEnergyToReduce nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseEnergyToReduceRandom(StrategyChooseEnergyToReduce nextStrategy){ super(null, nextStrategy);}
    public ChooseEnergyToReduceRandom() {
        super();
    }

    @Override
    public Energy choose(Player player, List<Energy> energies) {
        return (Energy) ChooseRandom.getInstance().chooseRandom(energies);
    }
}
