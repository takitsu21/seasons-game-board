package core.ia.strategy.choose.bonus;

import core.ia.Player;
import core.ia.inventory.Bonus;
import core.ia.inventory.BonusType;
import core.ia.strategy.choose.AbstractStrategy;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.Strategy;

public abstract class ChooseBonus extends AbstractStrategy implements StrategyChooseBonus {

    public ChooseBonus(IContext context, StrategyChooseBonus nextChooseBonus) {
        super(context, (Strategy) nextChooseBonus);
    }

    public ChooseBonus(){
        super(Context.everyTime, null);
    }

    public BonusType doChoose(Player player){
        return (BonusType) doTheChoose(player);
    }

    @Override
    public abstract BonusType choose(Player player);
}
