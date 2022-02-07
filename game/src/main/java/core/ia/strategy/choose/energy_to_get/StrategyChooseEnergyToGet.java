package core.ia.strategy.choose.energy_to_get;

import core.board.enums.Energy;
import core.ia.Player;

public interface StrategyChooseEnergyToGet {

    Energy doChoose(Player player);

    Energy choose(Player player);
}
