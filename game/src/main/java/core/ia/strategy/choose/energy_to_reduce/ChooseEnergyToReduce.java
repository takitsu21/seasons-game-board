package core.ia.strategy.choose.energy_to_reduce;

import core.board.enums.Energy;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.ArrayList;
import java.util.List;

public abstract class ChooseEnergyToReduce implements StrategyChooseEnergyToReduce {
    private StrategyChooseEnergyToReduce nextStrategy;
    private IContext context;

    public ChooseEnergyToReduce(IContext context, StrategyChooseEnergyToReduce nextStrategy) {
        this.context = context;
        this.nextStrategy = nextStrategy;
    }

    public ChooseEnergyToReduce() {
        this.nextStrategy = null;
        this.context = Context.everyTime;
    }

    @Override
    public Energy doChoose(Player player, List<Energy> energies) {
        if (energies.isEmpty()){
            return null;
        }
        if (context == null || context.isVerified(player)){
            Energy res = choose(player, energies);
            if (res == null){
                return nextStrategy.doChoose(player, energies);
            }
            else {
                return res;
            }
        }
        else {
            return nextStrategy.doChoose(player, energies);
        }
    }

    @Override
    public abstract Energy choose(Player player, List<Energy> energies);

    public Energy getEnergyToReduce(Player player, List<Energy> energies) {
        ArrayList<Energy> energyStock = (ArrayList<Energy>) player.getFacadeIA().getEnergyStock(player);
        for (Energy energyNeed : energies) {
            if (!energyStock.contains(energyNeed)) {
                return energyNeed;
            }
        }
        return null;
    }
}
