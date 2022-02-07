package core.ia.strategy.choose.bonus;

import core.ia.Player;
import core.ia.inventory.BonusType;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;


public class ChooseBonusAdaptative extends ChooseBonus {
    private Adaptative self;

    public ChooseBonusAdaptative(IContext context, StrategyChooseBonus strategyChooseBonus) {
        super(context, strategyChooseBonus);
    }

    public ChooseBonusAdaptative(StrategyChooseBonus strategyChooseBonus) {
        super(null, strategyChooseBonus);
    }

    public ChooseBonusAdaptative(Adaptative player) {
        super(Context.everyTime, new ChooseBonusRandom());
        self = player;
    }

    @Override
    public BonusType choose(Player player) {
        self.analyzeOpponentStrategy();
        BonusType result = self.chooseBonus();
        self.setStrategiesToAdaptative();
        return result;
    }
}