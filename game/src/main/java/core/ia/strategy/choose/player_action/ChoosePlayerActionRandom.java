package core.ia.strategy.choose.player_action;

import core.ia.EnumPlayerAction;
import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChoosePlayerActionRandom extends ChoosePlayerAction {

    public ChoosePlayerActionRandom(IContext context, StrategyChoosePlayerAction nextStrategy) {
        super(context, nextStrategy);
    }
    public ChoosePlayerActionRandom(StrategyChoosePlayerAction nextStrategy){ super(null, nextStrategy);}
    public ChoosePlayerActionRandom() {
        super();
    }

    public EnumPlayerAction choose(Player player) {
        List<EnumPlayerAction> possiblePlayerAction = player.getFacadeIA().getPossibleAction();
        return (EnumPlayerAction) ChooseRandom.getInstance().chooseRandom(possiblePlayerAction);
    }
}
