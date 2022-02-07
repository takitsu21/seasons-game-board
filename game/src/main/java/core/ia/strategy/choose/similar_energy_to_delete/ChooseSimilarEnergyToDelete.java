package core.ia.strategy.choose.similar_energy_to_delete;

import core.board.enums.Energy;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.List;

public abstract class ChooseSimilarEnergyToDelete implements StrategyChooseSimilarEnergyToDelete {
    private IContext context;
    private StrategyChooseSimilarEnergyToDelete nextStrategy;

    public ChooseSimilarEnergyToDelete(IContext context, StrategyChooseSimilarEnergyToDelete nextStrategy) {
        this.context = context;
        this.nextStrategy = nextStrategy;
    }

    public ChooseSimilarEnergyToDelete() {
        this.nextStrategy = null;
        this.context = Context.everyTime;
    }

    @Override
    public List<Energy> doChoose(Player player, int nb) {
        if (context == null || context.isVerified(player)){
            List<Energy> res = choose(player, nb);
            if (res == null){
                return nextStrategy.doChoose(player, nb);
            }
            else{
                return res;
            }
        }
        else{
            return nextStrategy.doChoose(player, nb);
        }
    }

    @Override
    public abstract List<Energy> choose(Player player, int nb);
}
