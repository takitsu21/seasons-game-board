package core.ia.strategy.choose.energy_to_throw;

import core.board.enums.Energy;
import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseEnergyToThrowRandom extends ChooseEnergyToThrow {

    public ChooseEnergyToThrowRandom(IContext context, StrategyChooseEnergyToThrow nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseEnergyToThrowRandom(StrategyChooseEnergyToThrow nextStrategy){ super(null, nextStrategy);}
    public ChooseEnergyToThrowRandom() {
        super();
    }

    @Override
    public Energy choose(Player player) {
        List<Energy> energies = player.getFacadeIA().getEnergyStock(player);
        return (Energy) ChooseRandom.getInstance().chooseRandom(energies);
    }
}
