package core.ia.strategy.choose.to_keep_drawn_card;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public abstract class ChooseToKeepDrawnCard implements StrategyChooseToKeepDrawnCard {
    private IContext context;
    private StrategyChooseToKeepDrawnCard nextStrategy;

    public ChooseToKeepDrawnCard(IContext context, StrategyChooseToKeepDrawnCard nextStrategy) {
        this.context = context;
        this.nextStrategy = nextStrategy;
    }

    public ChooseToKeepDrawnCard() {
        this.nextStrategy = null;
        this.context = Context.everyTime;
    }

    @Override
    public Boolean doChoose(Player player, Card card) {
        // Don't know if it really necessary to verify the good ending
        if (context == null || context.isVerified(player)){
            Boolean res = choose(player, card);
            if (res == null){
                if (nextStrategy == null){
                    return null;
                }
                else{
                    return nextStrategy.doChoose(player, card);
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
                return nextStrategy.doChoose(player, card);
            }
        }
    }

    @Override
    public abstract Boolean choose(Player player, Card card);
}
