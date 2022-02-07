package core.ia.strategy.choose.bonus;

import core.cards.Card;
import core.ia.Player;
import core.ia.inventory.BonusType;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

import java.util.List;

public class ChooseBonusPrefPermanent extends ChooseBonus {

    public ChooseBonusPrefPermanent(IContext context, StrategyChooseBonus strategyChooseBonus) {
        super(context, strategyChooseBonus);
    }

    public ChooseBonusPrefPermanent(StrategyChooseBonus strategyChooseBonus) {
        super(null, strategyChooseBonus);
    }

    public ChooseBonusPrefPermanent() {
        super(Context.everyTime, new ChooseBonusRandom());
    }

    @Override
    public BonusType choose(Player player) {
        List<BonusType> possibleBonus = player.getFacadeIA().getPossibleBonus();
        if (possibleBonus.contains(BonusType.ADD_INVOCATION)){
            return BonusType.ADD_INVOCATION;
        } else {
            return null;
        }
    }
}
