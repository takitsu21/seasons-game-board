package core.ia.strategy.choose.energy_to_get;

import core.board.enums.Energy;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public class ChooseEnergyToGetPrefActivate extends ChooseEnergyToGet {
    public ChooseEnergyToGetPrefActivate(IContext context, StrategyChooseEnergyToGet nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseEnergyToGetPrefActivate(StrategyChooseEnergyToGet nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseEnergyToGetPrefActivate() {
        super(Context.everyTime, new ChooseEnergyToGetRandom());
    }

    @Override
    public Energy choose(Player player) {
        return player.getFacadeIA().missingEnergy(player.getFacadeIA().getActivableCard());
    }
}
