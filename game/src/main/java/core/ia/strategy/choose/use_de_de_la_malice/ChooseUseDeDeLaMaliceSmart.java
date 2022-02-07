package core.ia.strategy.choose.use_de_de_la_malice;

import core.ia.Player;
import core.ia.strategy.choose.IContext;

public class ChooseUseDeDeLaMaliceSmart extends ChooseUseDeDeLaMalice {

    public ChooseUseDeDeLaMaliceSmart(IContext context, StrategyChooseUseDeDeLaMalice nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseUseDeDeLaMaliceSmart() {
        super();
    }

    @Override
    public Boolean choose(Player player) {
        return useDeDeLaMalice(player);
    }
}
