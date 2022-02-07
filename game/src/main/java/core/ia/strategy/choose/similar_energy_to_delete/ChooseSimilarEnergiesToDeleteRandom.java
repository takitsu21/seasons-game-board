package core.ia.strategy.choose.similar_energy_to_delete;

import core.board.enums.Energy;
import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;

import java.util.ArrayList;
import java.util.List;

public class ChooseSimilarEnergiesToDeleteRandom extends ChooseSimilarEnergyToDelete {

    public ChooseSimilarEnergiesToDeleteRandom(IContext context, StrategyChooseSimilarEnergyToDelete nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseSimilarEnergiesToDeleteRandom(StrategyChooseSimilarEnergyToDelete nextStrategy){ super(null, nextStrategy);}
    public ChooseSimilarEnergiesToDeleteRandom() {
        super();
    }

    @Override
    public List<Energy> choose(Player player, int nb) {
        List<List<Energy>> listOfMultipleEnergy = player.getFacadeIA().getListOfMultipleEnergy(nb);
        if (!listOfMultipleEnergy.isEmpty()) {
            return (List<Energy>) ChooseRandom.getInstance().chooseRandom(listOfMultipleEnergy);
        }
        return new ArrayList<>();
    }
}
