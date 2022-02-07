package core.ia.strategy.choose.energy_to_reduce;

import core.board.enums.Energy;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.energy_to_get.ChooseEnergyToGetRandom;
import core.ia.strategy.choose.energy_to_get.StrategyChooseEnergyToGet;

import java.util.List;

public class ChooseEnergyToReduceFirst extends ChooseEnergyToReduce {

    public ChooseEnergyToReduceFirst(IContext context, StrategyChooseEnergyToReduce nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseEnergyToReduceFirst(StrategyChooseEnergyToReduce nextStrategy){ super(null, nextStrategy);}
    public ChooseEnergyToReduceFirst() {
        super();
    }

    @Override
    public Energy choose(Player player, List<Energy> energies) {
        return (Energy) ChooseFirst.getInstance().chooseFirst(energies);
    }
}
