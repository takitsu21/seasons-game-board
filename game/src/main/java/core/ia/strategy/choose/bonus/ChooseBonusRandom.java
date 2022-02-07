package core.ia.strategy.choose.bonus;

import core.ia.Player;
import core.ia.inventory.BonusType;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseBonusRandom extends ChooseBonus {

    public ChooseBonusRandom(IContext context, StrategyChooseBonus nextChooseBonus) {
        super(context, nextChooseBonus);
    }
    public ChooseBonusRandom(StrategyChooseBonus nextStrategy){ super(null, nextStrategy);}
    public ChooseBonusRandom() {
        super();
    }

    public BonusType choose(Player player) {
        List<BonusType> possibleBonus = player.getFacadeIA().getPossibleBonus();
        return (BonusType) ChooseRandom.getInstance().chooseRandom(possibleBonus);
    }
}
