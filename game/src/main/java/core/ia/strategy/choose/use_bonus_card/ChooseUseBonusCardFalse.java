package core.ia.strategy.choose.use_bonus_card;

import core.ia.Player;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.IContext;

public class ChooseUseBonusCardFalse extends ChooseUseBonusCard {

    public ChooseUseBonusCardFalse(IContext context, StrategyChooseUseBonusCard nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseUseBonusCardFalse() {
        super();
    }

    @Override
    public Boolean choose(Player player) {
        return false;
    }
}
