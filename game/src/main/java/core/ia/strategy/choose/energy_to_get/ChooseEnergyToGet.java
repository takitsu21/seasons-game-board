package core.ia.strategy.choose.energy_to_get;

import core.board.enums.Energy;
import core.ia.Player;
import core.ia.strategy.choose.AbstractStrategy;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.Strategy;

public abstract class ChooseEnergyToGet extends AbstractStrategy implements StrategyChooseEnergyToGet {

    public ChooseEnergyToGet(IContext context, StrategyChooseEnergyToGet nextStrategy) {
        super(context, (Strategy) nextStrategy);
    }

    public ChooseEnergyToGet() {
        super(Context.everyTime, null);
    }

    @Override
    public Energy doChoose(Player player) {
        return (Energy) doTheChoose(player);
    }

    @Override
    public abstract Energy choose(Player player);
}
