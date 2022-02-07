package core.ia.strategy.choose.card_between_multiple_to_get;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.AbstractStrategy;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.Strategy;

import java.util.List;

public abstract class ChooseCardBetweenMultipleToGet implements StrategyChooseCardBetweenMultipleToGet {
    protected StrategyChooseCardBetweenMultipleToGet nextChooseCardBetweenMultipleToGet;
    protected IContext context;

    public ChooseCardBetweenMultipleToGet(IContext context, StrategyChooseCardBetweenMultipleToGet nextChooseCardBetweenMultipleToGet) {
        this.context = context;
        this.nextChooseCardBetweenMultipleToGet = nextChooseCardBetweenMultipleToGet;
    }

    public ChooseCardBetweenMultipleToGet() {
        this.context = Context.everyTime;
        this.nextChooseCardBetweenMultipleToGet = null;
    }

    @Override
    public Card doChoose(Player player, List<Card> cardList) {
        if (cardList.isEmpty()){
            return null;
        }
        if (context == null || context.isVerified(player)){
            Card res = choose(player,cardList);
            if (res == null){
                return nextChooseCardBetweenMultipleToGet.doChoose(player,cardList);
            }
            else {
                return res;
            }
        }
        else {
            return nextChooseCardBetweenMultipleToGet.doChoose(player, cardList);
        }
    }
}


