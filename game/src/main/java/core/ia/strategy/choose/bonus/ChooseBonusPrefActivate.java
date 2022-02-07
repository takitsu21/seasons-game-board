package core.ia.strategy.choose.bonus;

import core.ia.Player;
import core.ia.inventory.BonusType;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseBonusPrefActivate extends ChooseBonus {
    public ChooseBonusPrefActivate(IContext context, StrategyChooseBonus nextChooseBonus) {
        super(context, nextChooseBonus);
    }

    public ChooseBonusPrefActivate(StrategyChooseBonus nextChooseBonus) {
        super(null, nextChooseBonus);
    }

    public ChooseBonusPrefActivate() {
        super(Context.everyTime, new ChooseBonusRandom());
    }

    @Override
    public BonusType choose(Player player) {
        List<BonusType> possibleBonus = player.getFacadeIA().getPossibleBonus();
        if (possibleBonus.contains(BonusType.CHANGE_ENERGY)) {
            return BonusType.CHANGE_ENERGY;
        } else {
            return null;
        }
    }
}
