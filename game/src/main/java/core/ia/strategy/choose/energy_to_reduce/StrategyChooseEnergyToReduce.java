package core.ia.strategy.choose.energy_to_reduce;

import core.board.enums.Energy;
import core.ia.Player;

import java.util.List;

public interface StrategyChooseEnergyToReduce {

    Energy doChoose(Player player, List<Energy> energies);

    Energy choose(Player player, List<Energy> energies);
}
