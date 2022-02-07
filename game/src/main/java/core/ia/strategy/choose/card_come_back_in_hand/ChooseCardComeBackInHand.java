package core.ia.strategy.choose.card_come_back_in_hand;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.AbstractStrategy;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.Strategy;

public abstract class ChooseCardComeBackInHand extends AbstractStrategy implements StrategyChooseCardComeBackInHand {

    public ChooseCardComeBackInHand(IContext context, StrategyChooseCardComeBackInHand nextStrategy) {
        super(context, (Strategy) nextStrategy);
    }

    public ChooseCardComeBackInHand() {
        super(Context.everyTime, null);
    }

    @Override
    public Card doChoose(Player player) {
        if (player.getFacadeIA().getMagicCardsOnBoard().isEmpty()){
            return null;
        }
        return (Card) doTheChoose(player);
    }

    @Override
    public abstract Card choose(Player player);
}
