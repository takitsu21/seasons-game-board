package core.ia.strategy.choose.bonus;

import core.ia.Player;
import core.ia.inventory.BonusType;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.IContext;

import java.net.ContentHandler;
import java.util.List;

public class ChooseNoBonus extends ChooseBonus {

    public ChooseNoBonus(IContext context, StrategyChooseBonus strategyChooseBonus) {
        super(context, strategyChooseBonus);
    }

    public ChooseNoBonus(StrategyChooseBonus nextStrategy){
        super(null, nextStrategy);
    }

    public ChooseNoBonus() {
        super();
    }

    public BonusType choose(Player player) {
        return null;
    }

}
