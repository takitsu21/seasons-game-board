package core.ia.strategy.choose.bonus;

import core.ia.Player;
import core.ia.inventory.BonusType;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import util.Util;

import java.util.List;

public class ChooseBonusPrefCardPoints extends ChooseBonus {
    public ChooseBonusPrefCardPoints(IContext context, StrategyChooseBonus strategyChooseBonus) {
        super(context, strategyChooseBonus);
    }

    public ChooseBonusPrefCardPoints(StrategyChooseBonus strategyChooseBonus) {
        super(null, strategyChooseBonus);
    }

    public ChooseBonusPrefCardPoints() {
        super(Context.everyTime, new ChooseBonusRandom());
    }

    @Override
    public BonusType choose(Player player) {
        List<BonusType> possibleBonus = player.getFacadeIA().getPossibleBonus();
        if (possibleBonus.contains(BonusType.ADD_INVOCATION)) {
            return BonusType.ADD_INVOCATION;
        } else {
            return null;
        }
    }
}
