package core.ia.strategy.choose.copy_energy_from_player;

import core.ia.Player;
import core.ia.strategy.choose.AbstractStrategy;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.Strategy;

public abstract class ChoosePlayerEnergyToCopy extends AbstractStrategy implements StrategyChoosePlayerEnergyToCopy {

    public ChoosePlayerEnergyToCopy(IContext context, StrategyChoosePlayerEnergyToCopy nextStrategy) {
        super(context, (Strategy) nextStrategy);
    }

    public ChoosePlayerEnergyToCopy() {
        super(Context.everyTime, null);
    }

    @Override
    public Player doChoose(Player player) {
        return (Player) doTheChoose(player);
    }

    @Override
    public abstract Player choose(Player player);
}
