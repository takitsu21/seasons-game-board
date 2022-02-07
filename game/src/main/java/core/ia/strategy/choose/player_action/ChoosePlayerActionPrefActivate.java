package core.ia.strategy.choose.player_action;

import core.ia.EnumPlayerAction;
import core.ia.Player;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChoosePlayerActionPrefActivate extends ChoosePlayerAction {
    public ChoosePlayerActionPrefActivate(IContext context, StrategyChoosePlayerAction nextStrategy) {
        super(context, nextStrategy);
    }

    public ChoosePlayerActionPrefActivate() {
        super();
    }

    public EnumPlayerAction choose(Player player) {
        List<EnumPlayerAction> possiblePlayerAction = player.getFacadeIA().getPossibleAction();
        if (possiblePlayerAction.contains(EnumPlayerAction.ACTIVATE_CARD)) {
            return EnumPlayerAction.ACTIVATE_CARD;
        } else if (possiblePlayerAction.contains(EnumPlayerAction.SUMMON)) {
            return EnumPlayerAction.SUMMON;
        } else if (possiblePlayerAction.contains(EnumPlayerAction.BONUS)) {
            return EnumPlayerAction.BONUS;
        } else {
            return EnumPlayerAction.NOTHING;
        }
    }
}
