package core.ia.strategy.choose.dice;

import core.dice.Dice;
import core.ia.Player;
import core.ia.strategy.choose.AbstractStrategy;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.Strategy;

public abstract class ChooseDice extends AbstractStrategy implements StrategyChooseDice {

    public ChooseDice(IContext context, StrategyChooseDice nextStrategy) {
        super(context, (Strategy) nextStrategy);
    }

    public ChooseDice() {
        super(Context.everyTime,null);
    }

    @Override
    public Dice doChoose(Player player) {
        if (player.getFacadeIA().getDiceSet().length == 0){
            return null;
        }
        else {
            return (Dice) doTheChoose(player);
        }

    }

    @Override
    public abstract Dice choose(Player player);
}
