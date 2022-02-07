package core.ia.strategy.choose;

import core.ia.Player;

import java.util.List;

public abstract class AbstractStrategy implements Strategy {
    protected Strategy nextStrategy;
    protected IContext context;

    public AbstractStrategy(IContext context, Strategy nextStrategy){
        this.context = context;
        this.nextStrategy = nextStrategy;
    }

    public abstract Object choose(Player player);

    public Object doTheChoose(Player player){
        if (context == null || context.isVerified(player)){
            Object res = choose(player);
            if (res == null){
                if (nextStrategy == null){
                    return null;
                }
                else{
                    return nextStrategy.doChoose(player);
                }
            }
            else {
                return res;
            }
        }
        else {
            if (nextStrategy == null){
                return null;
            }
            else {
                return nextStrategy.doChoose(player);
            }
        }
    }
}
