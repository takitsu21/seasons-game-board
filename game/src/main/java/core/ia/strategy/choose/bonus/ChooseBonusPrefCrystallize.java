package core.ia.strategy.choose.bonus;

import core.ia.Player;
import core.ia.inventory.BonusType;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import util.Util;

import java.util.List;

public class ChooseBonusPrefCrystallize extends ChooseBonus {

    public ChooseBonusPrefCrystallize(IContext context, StrategyChooseBonus strategyChooseBonus){
        super(context, strategyChooseBonus);
    }

    public ChooseBonusPrefCrystallize(StrategyChooseBonus nextStrategy){
        super(null, nextStrategy);
    }

    public ChooseBonusPrefCrystallize(){
        super(Context.everyTime, new ChooseBonusRandom());
    }

    @Override
    public BonusType choose(Player player) {
        List<BonusType> possibleBonus = player.getFacadeIA().getPossibleBonus();
        if (possibleBonus.contains(BonusType.CRYSTALLIZE)) {
            return BonusType.CRYSTALLIZE;
        } else {
            return null;
        }
    }
}
