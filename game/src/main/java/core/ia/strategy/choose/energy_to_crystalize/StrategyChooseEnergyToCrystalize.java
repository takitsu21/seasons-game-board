package core.ia.strategy.choose.energy_to_crystalize;

import core.board.enums.Energy;
import core.ia.Player;

public interface StrategyChooseEnergyToCrystalize {

    Energy doChoose(Player player);

    Energy choose(Player player);

}
