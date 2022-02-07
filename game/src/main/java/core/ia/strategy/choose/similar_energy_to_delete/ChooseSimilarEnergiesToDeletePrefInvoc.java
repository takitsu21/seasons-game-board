package core.ia.strategy.choose.similar_energy_to_delete;

import core.board.enums.Energy;
import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;

import java.util.ArrayList;
import java.util.List;

public class ChooseSimilarEnergiesToDeletePrefInvoc extends ChooseSimilarEnergyToDelete {

    public ChooseSimilarEnergiesToDeletePrefInvoc(IContext context, StrategyChooseSimilarEnergyToDelete nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseSimilarEnergiesToDeletePrefInvoc(StrategyChooseSimilarEnergyToDelete nextStrategy){ super(null, nextStrategy);}
    public ChooseSimilarEnergiesToDeletePrefInvoc() {
        super();
    }

    @Override
    public List<Energy> choose(Player player, int nb) {
        return player.getFacadeIA().uselessSimilarEnergy(player.getFacadeIA().getCardInHand(), nb);
    }
}
