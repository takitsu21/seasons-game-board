package core.ia.strategy.choose.dice;

import core.dice.Dice;
import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public class ChooseDiceAdaptative extends ChooseDice {
    Adaptative self;

    public ChooseDiceAdaptative(IContext context, StrategyChooseDice nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseDiceAdaptative(StrategyChooseDice nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseDiceAdaptative(Adaptative player) {
        super(Context.everyTime, new ChooseDiceRandom());
        self = player;
    }

    @Override
    public Dice choose(Player player) {
        self.analyzeOpponentStrategy();
        Dice result = self.chooseDice();
        self.setStrategiesToAdaptative();
        return result;
    }
}