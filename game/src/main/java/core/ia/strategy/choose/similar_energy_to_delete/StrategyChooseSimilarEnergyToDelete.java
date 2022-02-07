package core.ia.strategy.choose.similar_energy_to_delete;

import core.board.enums.Energy;
import core.ia.Player;

import java.util.List;

public interface StrategyChooseSimilarEnergyToDelete {

    List<Energy> doChoose(Player player, int nb);

    List<Energy> choose(Player player, int nb);
}
