package core.ia.strategy.choose.energy_to_throw;

import core.board.enums.Energy;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseEnergyToThrowFirst extends ChooseEnergyToThrow {

    public ChooseEnergyToThrowFirst(IContext context, StrategyChooseEnergyToThrow nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseEnergyToThrowFirst(StrategyChooseEnergyToThrow nextStrategy){ super(null, nextStrategy);}
    public ChooseEnergyToThrowFirst() {
        super();
    }

    @Override
    public Energy choose(Player player) {
        List<Energy> energies = player.getFacadeIA().getEnergyStock(player);
        return (Energy) ChooseFirst.getInstance().chooseFirst(energies);
    }
}
