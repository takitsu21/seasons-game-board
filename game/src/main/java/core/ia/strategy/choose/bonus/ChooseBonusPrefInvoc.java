package core.ia.strategy.choose.bonus;

import core.ia.Player;
import core.ia.inventory.BonusType;
import core.ia.strategy.choose.IContext;
import util.Util;

import java.util.List;

public class ChooseBonusPrefInvoc extends ChooseBonus {
    public ChooseBonusPrefInvoc(IContext context, StrategyChooseBonus strategyChooseBonus) {
        super(context, strategyChooseBonus);
    }

    public ChooseBonusPrefInvoc(StrategyChooseBonus strategyChooseBonus) {
        super(null, strategyChooseBonus);
    }

    public ChooseBonusPrefInvoc() {
        super();
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
