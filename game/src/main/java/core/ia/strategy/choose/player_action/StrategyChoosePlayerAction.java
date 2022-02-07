package core.ia.strategy.choose.player_action;

import core.ia.EnumPlayerAction;
import core.ia.Player;

public interface StrategyChoosePlayerAction {

    EnumPlayerAction doChoose(Player player);

    EnumPlayerAction choose(Player player);
}
