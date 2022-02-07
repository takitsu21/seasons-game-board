package core.ia.strategy.choose.player_action;

import core.ia.EnumPlayerAction;
import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public class ChoosePlayerActionAdaptative extends ChoosePlayerAction {
    Adaptative self;

    public ChoosePlayerActionAdaptative(IContext context, StrategyChoosePlayerAction nextStrategy) {
        super(context, nextStrategy);
    }

    public ChoosePlayerActionAdaptative(StrategyChoosePlayerAction nextStrategy){ super(null, nextStrategy);}

    public ChoosePlayerActionAdaptative(Adaptative player) {
        super(Context.everyTime, new ChoosePlayerActionRandom());
        self = player;
    }

    @Override
    public EnumPlayerAction choose(Player player) {
        self.analyzeOpponentStrategy();
        EnumPlayerAction result = self.choosePlayerAction();
        self.setStrategiesToAdaptative();
        return result;
    }
}
