package core.ia.strategy.choose.energy_to_crystalize;

import core.board.enums.Energy;
import core.ia.Player;
import core.ia.strategy.choose.AbstractStrategy;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.Strategy;

public abstract class ChooseEnergyToCrystalize extends AbstractStrategy implements StrategyChooseEnergyToCrystalize {

    public ChooseEnergyToCrystalize(IContext context, StrategyChooseEnergyToCrystalize nextStrategy) {
        super(context, (Strategy) nextStrategy);
    }

    public ChooseEnergyToCrystalize() {
        super(Context.everyTime, null);
    }

    @Override
    public Energy doChoose(Player player) {
        if (player.getFacadeIA().getEnergyStock().isEmpty()){
            return null;
        }
        else{
            return (Energy) doTheChoose(player);
        }
    }

    @Override
    public abstract Energy choose(Player player);
}
