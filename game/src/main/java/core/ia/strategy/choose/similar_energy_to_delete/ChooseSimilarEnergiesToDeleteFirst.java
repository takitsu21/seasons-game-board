package core.ia.strategy.choose.similar_energy_to_delete;

import core.board.enums.Energy;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.IContext;
import core.util.Renderer;

import java.util.ArrayList;
import java.util.List;

public class ChooseSimilarEnergiesToDeleteFirst extends ChooseSimilarEnergyToDelete {

    public ChooseSimilarEnergiesToDeleteFirst(IContext context, StrategyChooseSimilarEnergyToDelete nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseSimilarEnergiesToDeleteFirst(StrategyChooseSimilarEnergyToDelete nextStrategy){ super(null, nextStrategy);}
    public ChooseSimilarEnergiesToDeleteFirst() {
        super();
    }

    @Override
    public List<Energy> choose(Player player, int nb) {
        List<List<Energy>> listOfMultipleEnergy = player.getFacadeIA().getListOfMultipleEnergy(nb);
        Renderer.add("List of similar energy first: " + listOfMultipleEnergy.toString());
        if (!listOfMultipleEnergy.isEmpty()) {
            return (List<Energy>) ChooseFirst.getInstance().chooseFirst(listOfMultipleEnergy);
        }
        return new ArrayList<>();
    }

}
