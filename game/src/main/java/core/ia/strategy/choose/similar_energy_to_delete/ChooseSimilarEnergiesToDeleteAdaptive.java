package core.ia.strategy.choose.similar_energy_to_delete;

import core.board.enums.Energy;
import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseSimilarEnergiesToDeleteAdaptive extends ChooseSimilarEnergyToDelete {
    Adaptative self;

    public ChooseSimilarEnergiesToDeleteAdaptive(IContext context, StrategyChooseSimilarEnergyToDelete nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseSimilarEnergiesToDeleteAdaptive(StrategyChooseSimilarEnergyToDelete nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseSimilarEnergiesToDeleteAdaptive(Adaptative player) {
        super(Context.everyTime, new ChooseSimilarEnergiesToDeleteRandom());
        self = player;
    }

    @Override
    public List<Energy> choose(Player player, int nb) {
        self.analyzeOpponentStrategy();
        List<Energy> result = self.chooseSimilarEnergiesToDelete(nb);
        self.setStrategiesToAdaptative();
        return result;
    }
}