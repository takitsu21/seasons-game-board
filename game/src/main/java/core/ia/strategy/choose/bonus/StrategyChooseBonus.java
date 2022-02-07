package core.ia.strategy.choose.bonus;

import core.ia.Player;
import core.ia.inventory.BonusType;
import core.ia.strategy.choose.Strategy;


public interface StrategyChooseBonus extends Strategy {

    BonusType doChoose(Player player);
    BonusType choose(Player player);

}
