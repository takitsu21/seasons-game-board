package core.ia.strategy.choose.energy_to_reduce;

import core.board.enums.Energy;
import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseEnergyToReduceAdaptative extends ChooseEnergyToReduce {
    Adaptative self;

    public ChooseEnergyToReduceAdaptative(IContext context, StrategyChooseEnergyToReduce nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseEnergyToReduceAdaptative(StrategyChooseEnergyToReduce nextStrategy){ super(null, nextStrategy);}

    public ChooseEnergyToReduceAdaptative(Adaptative player) {
        super(Context.everyTime, new ChooseEnergyToReduceRandom());
        self = player;
    }

    @Override
    public Energy choose(Player player, List<Energy> energies) {
        self.analyzeOpponentStrategy();
        Energy result = self.chooseEnergyToReduce(energies);
        self.setStrategiesToAdaptative();
        return result;
    }
}