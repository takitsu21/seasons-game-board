package core.ia.strategy.choose.card_to_delete;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.AbstractStrategy;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.Strategy;


public abstract class ChooseCardToDelete extends AbstractStrategy implements StrategyChooseCardToDelete {
    private IContext context;
    private StrategyChooseCardToDelete nextStrategy;

    public ChooseCardToDelete(IContext context, StrategyChooseCardToDelete nextStrategy) {
        super(context, (Strategy) nextStrategy);
    }

    public ChooseCardToDelete() {
        super(Context.everyTime, null);
    }

    @Override
    public Card doChoose(Player player) {
        if (player.getFacadeIA().getCardOnBoard().isEmpty()){
            return null;
        }
        else{
            return (Card) doTheChoose(player);
        }
    }

    @Override
    public abstract Card choose(Player player);
}
