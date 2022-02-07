package core.ia.strategy.choose.use_de_de_la_malice;

import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.IContext;

public class ChooseUseDeDeLaMaliveAdaptative extends ChooseUseDeDeLaMalice {
    Adaptative self;

    public ChooseUseDeDeLaMaliveAdaptative(IContext context, StrategyChooseUseDeDeLaMalice nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseUseDeDeLaMaliveAdaptative(Adaptative player) {
        super();
        self = player;
    }

    @Override
    public Boolean choose(Player player) {
        self.analyzeOpponentStrategy();
        boolean result = self.chooseUseDeDeLaMalice();
        self.setStrategiesToAdaptative();
        return result;
    }
}
