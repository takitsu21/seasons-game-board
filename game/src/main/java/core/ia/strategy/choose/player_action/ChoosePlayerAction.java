package core.ia.strategy.choose.player_action;

import core.ia.EnumPlayerAction;
import core.ia.Player;
import core.ia.strategy.choose.AbstractStrategy;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.Strategy;
import core.ia.strategy.choose.card_to_summon.ChooseCardToSummon;

import javax.swing.*;


public abstract class ChoosePlayerAction extends AbstractStrategy implements StrategyChoosePlayerAction {

    public ChoosePlayerAction(IContext context, StrategyChoosePlayerAction nextStrategy) {
        super(context, (Strategy) nextStrategy);
    }

    public ChoosePlayerAction() {
        super( Context.everyTime, null);
    }

    @Override
    public EnumPlayerAction doChoose(Player player) {
        if (player.getFacadeIA().getPossibleAction().isEmpty()){
            return null;
        }
        else {
            return (EnumPlayerAction) doTheChoose(player);
        }
    }

    @Override
    public abstract EnumPlayerAction choose(Player player);
}
