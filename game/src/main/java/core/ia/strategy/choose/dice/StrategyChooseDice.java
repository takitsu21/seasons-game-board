package core.ia.strategy.choose.dice;

import core.dice.Dice;
import core.ia.Player;

public interface StrategyChooseDice {

    Dice doChoose(Player player);

    Dice choose(Player player);

}
