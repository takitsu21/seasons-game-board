package core.ia.strategy.choose.energy_to_throw;

import core.board.enums.Energy;
import core.ia.Player;

public interface StrategyChooseEnergyToThrow {

    Energy doChoose(Player player);

    Energy choose(Player player);
}
