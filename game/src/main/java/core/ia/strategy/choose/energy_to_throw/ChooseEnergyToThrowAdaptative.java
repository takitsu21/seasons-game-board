package core.ia.strategy.choose.energy_to_throw;

import core.board.enums.Energy;
import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public class ChooseEnergyToThrowAdaptative extends ChooseEnergyToThrow {
    Adaptative self;

    public ChooseEnergyToThrowAdaptative(IContext context, StrategyChooseEnergyToThrow nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseEnergyToThrowAdaptative(StrategyChooseEnergyToThrow nextStrategy){ super(null, nextStrategy);}

    public ChooseEnergyToThrowAdaptative(Adaptative player) {
        super(Context.everyTime, new ChooseEnergyToThrowRandom());
        self = player;
    }

    @Override
    public Energy choose(Player player) {
        self.analyzeOpponentStrategy();
        Energy result = self.chooseEnergyToThrow();
        self.setStrategiesToAdaptative();
        return result;
    }
}