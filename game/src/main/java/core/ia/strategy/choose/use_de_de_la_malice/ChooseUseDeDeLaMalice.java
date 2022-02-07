package core.ia.strategy.choose.use_de_de_la_malice;

import core.ia.Player;
import core.ia.strategy.choose.AbstractStrategy;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.Strategy;

public abstract class ChooseUseDeDeLaMalice extends AbstractStrategy implements StrategyChooseUseDeDeLaMalice {

    public ChooseUseDeDeLaMalice(IContext context, StrategyChooseUseDeDeLaMalice nextStrategy) {
        super(context,(Strategy) nextStrategy);
    }

    public ChooseUseDeDeLaMalice() {
        super(Context.everyTime,null);
    }


    @Override
    public Boolean doChoose(Player player) {
        // Don't know if it really necessary to verify the good ending
        if (context.isVerified(player)) {
            return choose(player);
        } else {
            return (Boolean) nextStrategy.doChoose(player);
        }
    }

    @Override
    public abstract Boolean choose(Player player);

    public Boolean useDeDeLaMalice(Player player){
        if(!player.isSatisfyingDice()) {
            player.setSatisfyingDice(true);
            return true;
        }
        return false;
    }
}
