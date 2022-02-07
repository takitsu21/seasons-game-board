package core.ia.strategy.choose.copy_energy_from_player;

import core.ia.Player;

public interface StrategyChoosePlayerEnergyToCopy {

    Player doChoose(Player player);

    Player choose(Player player);
}
