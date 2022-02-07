package core.ia.strategy.choose.use_de_de_la_malice;

import core.ia.Player;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public class ChooseUseDeDeLaMaliceFirst extends ChooseUseDeDeLaMalice {

    public ChooseUseDeDeLaMaliceFirst(IContext context, StrategyChooseUseDeDeLaMalice nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseUseDeDeLaMaliceFirst(StrategyChooseUseDeDeLaMalice nextStrategy){
        super(Context.everyTime, nextStrategy);
    }

    public ChooseUseDeDeLaMaliceFirst() {
        super();
    }

    @Override
    public Boolean choose(Player player) {
        return ChooseFirst.getInstance().chooseFirst();
    }
}
