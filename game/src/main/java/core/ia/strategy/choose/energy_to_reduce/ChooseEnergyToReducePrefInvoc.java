package core.ia.strategy.choose.energy_to_reduce;

import core.board.enums.Energy;
import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.energy_to_get.ChooseEnergyToGetPrefInvoc;
import core.ia.strategy.choose.energy_to_get.StrategyChooseEnergyToGet;

import java.util.List;

public class ChooseEnergyToReducePrefInvoc extends ChooseEnergyToReduce {

    public ChooseEnergyToReducePrefInvoc(IContext context, StrategyChooseEnergyToReduce nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseEnergyToReducePrefInvoc(StrategyChooseEnergyToReduce nextStrategy){ super(null, nextStrategy);}
    public ChooseEnergyToReducePrefInvoc() {
        super(Context.everyTime, new ChooseEnergyToReduceRandom());
    }

    @Override
    public Energy choose(Player player, List<Energy> energies) {
        return getEnergyToReduce(player, energies);
    }
}
