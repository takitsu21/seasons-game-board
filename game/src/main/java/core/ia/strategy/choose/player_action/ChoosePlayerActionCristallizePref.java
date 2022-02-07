package core.ia.strategy.choose.player_action;

import core.ia.EnumPlayerAction;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChoosePlayerActionCristallizePref extends ChoosePlayerAction {

    public ChoosePlayerActionCristallizePref(IContext context, StrategyChoosePlayerAction nextStrategy) {
        super(context, nextStrategy);
    }

    public ChoosePlayerActionCristallizePref(StrategyChoosePlayerAction nextStrategy){ super(null, nextStrategy);}

    public ChoosePlayerActionCristallizePref() {
        super(Context.everyTime, new ChoosePlayerActionRandom());
    }

    @Override
    public EnumPlayerAction choose(Player player) {
        List<EnumPlayerAction> possiblePlayerAction = player.getFacadeIA().getPossibleAction();
        if (possiblePlayerAction.contains(EnumPlayerAction.CRYSTALLISE)) {
            return EnumPlayerAction.CRYSTALLISE;
        } else {
            return null;
        }
    }
}
