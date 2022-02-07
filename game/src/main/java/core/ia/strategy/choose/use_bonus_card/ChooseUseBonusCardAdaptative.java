package core.ia.strategy.choose.use_bonus_card;

import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public class ChooseUseBonusCardAdaptative extends ChooseUseBonusCard {
    Adaptative self;

    public ChooseUseBonusCardAdaptative(IContext context, StrategyChooseUseBonusCard nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseUseBonusCardAdaptative(StrategyChooseUseBonusCard nextStrategy){
        super(Context.everyTime, nextStrategy);
    }

    public ChooseUseBonusCardAdaptative(Adaptative player) {
        super();
        self = player;
    }

    public Boolean choose(Player player) {
        self.analyzeOpponentStrategy();
        boolean result = self.chooseUseBonusCard();
        self.setStrategiesToAdaptative();
        return result;
    }
}
