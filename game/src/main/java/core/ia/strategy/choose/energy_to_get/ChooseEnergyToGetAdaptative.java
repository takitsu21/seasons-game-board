package core.ia.strategy.choose.energy_to_get;

import core.board.enums.Energy;
import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;


public class ChooseEnergyToGetAdaptative extends ChooseEnergyToGet{
    Adaptative self;

    public ChooseEnergyToGetAdaptative(IContext context, StrategyChooseEnergyToGet nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseEnergyToGetAdaptative(StrategyChooseEnergyToGet nextStrategy){ super(null, nextStrategy);}

    public ChooseEnergyToGetAdaptative(Adaptative player) {
        super(Context.everyTime, new ChooseEnergyToGetRandom());
        self = player;
    }

    @Override
    public Energy choose(Player player) {
        self.analyzeOpponentStrategy();
        Energy result = self.chooseEnergyToGet();
        self.setStrategiesToAdaptative();
        return result;
    }
}