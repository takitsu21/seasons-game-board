package core.ia.strategy.choose.energy_to_get;

import core.board.enums.Energy;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseEnergyToGetFirst extends ChooseEnergyToGet {

    public ChooseEnergyToGetFirst(IContext context, StrategyChooseEnergyToGet nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseEnergyToGetFirst(StrategyChooseEnergyToGet nextStrategy){ super(null,nextStrategy);}
    public ChooseEnergyToGetFirst() {
        super();
    }

    public Energy choose(Player player) {
        List<Energy> energies = player.getFacadeIA().getAllTypeEnergy();
        return (Energy) ChooseFirst.getInstance().chooseFirst(energies);
    }
}
