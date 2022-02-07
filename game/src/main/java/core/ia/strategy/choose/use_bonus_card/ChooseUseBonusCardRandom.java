package core.ia.strategy.choose.use_bonus_card;

import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.Strategy;

public class ChooseUseBonusCardRandom extends ChooseUseBonusCard {

    public ChooseUseBonusCardRandom(IContext context, StrategyChooseUseBonusCard nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseUseBonusCardRandom(StrategyChooseUseBonusCard nextStrategy){
        super(Context.everyTime, nextStrategy);
    }

    public ChooseUseBonusCardRandom() {
        super();
    }

    public Boolean choose(Player player) {
        return ChooseRandom.getInstance().chooseRandom();
    }
}
