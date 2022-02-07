package core.ia.strategy.choose.energy_to_crystalize;

import core.board.enums.Energy;
import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public class ChooseEnergyToCrystalizeAdaptative extends ChooseEnergyToCrystalize {
    Adaptative self;

    public ChooseEnergyToCrystalizeAdaptative(IContext context, StrategyChooseEnergyToCrystalize nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseEnergyToCrystalizeAdaptative(StrategyChooseEnergyToCrystalize nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseEnergyToCrystalizeAdaptative(Adaptative player) {
        super(Context.everyTime, new ChooseEnergyToCrystalizeRandom());
        self = player;
    }

    @Override
    public Energy choose(Player player) {
        self.analyzeOpponentStrategy();
        Energy result = self.chooseEnergyToCrystalize();
        self.setStrategiesToAdaptative();
        return result;
    }
}