package core.ia.strategy.choose.copy_energy_from_player;

import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public class ChoosePlayerEnergyToCopyAdaptative extends ChoosePlayerEnergyToCopy {
    Adaptative self;

    public ChoosePlayerEnergyToCopyAdaptative(IContext context, StrategyChoosePlayerEnergyToCopy nextStrategy) {
        super(context, nextStrategy);
    }

    public ChoosePlayerEnergyToCopyAdaptative(StrategyChoosePlayerEnergyToCopy nextStrategy) {
        super(null, nextStrategy);
    }

    public ChoosePlayerEnergyToCopyAdaptative(Adaptative player) {
        super(Context.everyTime, new ChoosePlayerEnergyToCopyRandom());
        self = player;
    }

    @Override
    public Player choose(Player player) {
        self.analyzeOpponentStrategy();
        Player result = self.choosePlayer();
        self.setStrategiesToAdaptative();
        return result;
    }
}