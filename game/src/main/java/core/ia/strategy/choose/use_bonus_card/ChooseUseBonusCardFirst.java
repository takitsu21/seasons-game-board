package core.ia.strategy.choose.use_bonus_card;

import core.ia.Player;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public class ChooseUseBonusCardFirst extends ChooseUseBonusCard {

    public ChooseUseBonusCardFirst(IContext context, StrategyChooseUseBonusCard nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseUseBonusCardFirst(StrategyChooseUseBonusCard nextStrategy){
        super(Context.everyTime, nextStrategy);
    }

    public ChooseUseBonusCardFirst() {
        super();
    }

    @Override
    public Boolean choose(Player player) {
        return ChooseFirst.getInstance().chooseFirst();
    }
}
