package core.ia.strategy.choose.bonus;

import core.ia.Player;
import core.ia.inventory.BonusType;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseBonusFirst extends ChooseBonus {

    public ChooseBonusFirst(IContext context, StrategyChooseBonus strategyChooseBonus) {
        super(context, strategyChooseBonus);
    }

    public ChooseBonusFirst(StrategyChooseBonus nextStrategy){
        super(null, nextStrategy);
    }

    public ChooseBonusFirst() {
        super();
    }

    public BonusType choose(Player player) {
        List<BonusType> possibleBonus = player.getFacadeIA().getPossibleBonus();
        return (BonusType) ChooseFirst.getInstance().chooseFirst(possibleBonus);
    }

}
