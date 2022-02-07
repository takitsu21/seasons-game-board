package core.ia.strategy.choose.use_de_de_la_malice;

import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public class ChooseUseDeDeLaMaliceRandom extends ChooseUseDeDeLaMalice {

    public ChooseUseDeDeLaMaliceRandom(IContext context, StrategyChooseUseDeDeLaMalice nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseUseDeDeLaMaliceRandom(StrategyChooseUseDeDeLaMalice nextStrategy){
        super(Context.everyTime, nextStrategy);
    }

    public ChooseUseDeDeLaMaliceRandom() {
        super();
    }

    public Boolean choose(Player player) {
        return ChooseRandom.getInstance().chooseRandom();
    }
}
