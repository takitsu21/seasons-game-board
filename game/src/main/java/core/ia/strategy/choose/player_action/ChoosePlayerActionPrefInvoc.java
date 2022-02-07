package core.ia.strategy.choose.player_action;

import core.ia.EnumPlayerAction;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.dice.ChooseDiceRandom;

import java.util.List;

public class ChoosePlayerActionPrefInvoc extends ChoosePlayerAction {

    public ChoosePlayerActionPrefInvoc(IContext context, StrategyChoosePlayerAction nextStrategy) {
        super(context, nextStrategy);
    }

    public ChoosePlayerActionPrefInvoc(StrategyChoosePlayerAction nextStrategy) {
        super(null, nextStrategy);
    }

    public ChoosePlayerActionPrefInvoc() {
        super();
    }


    @Override
    public EnumPlayerAction choose(Player player) {
        List<EnumPlayerAction> possiblePlayerAction = player.getFacadeIA().getPossibleAction();
        if (possiblePlayerAction.contains(EnumPlayerAction.SUMMON) && !player.getFacadeIA().getSummonableCards().isEmpty()) {
            return EnumPlayerAction.SUMMON;
        } else if (possiblePlayerAction.contains(EnumPlayerAction.BONUS) && !player.getFacadeIA().hasInvocationsLeft() && player.getFacadeIA().anyCardCostIsEnough()) {
            return EnumPlayerAction.BONUS;
        } else if (possiblePlayerAction.contains(EnumPlayerAction.ACTIVATE_CARD) && !player.getFacadeIA().getActivableCard().isEmpty()) {
            return EnumPlayerAction.ACTIVATE_CARD;
        }
        else {
            return EnumPlayerAction.NOTHING;
        }
    }
}
