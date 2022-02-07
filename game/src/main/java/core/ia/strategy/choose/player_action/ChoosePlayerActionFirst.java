package core.ia.strategy.choose.player_action;

import core.ia.EnumPlayerAction;
import core.ia.Player;
import core.ia.strategy.choose.IContext;

public class ChoosePlayerActionFirst extends ChoosePlayerAction {

    public ChoosePlayerActionFirst(IContext context, StrategyChoosePlayerAction nextStrategy) {
        super(context, nextStrategy);
    }
    public ChoosePlayerActionFirst(StrategyChoosePlayerAction nextStrategy){ super(null, nextStrategy);}
    public ChoosePlayerActionFirst() {
        super();
    }

    public EnumPlayerAction choose(Player player) {
        return EnumPlayerAction.NOTHING;
    }
}
