package core.ia.strategy.choose.card_between_multiple_to_get;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardBetweenMultipleToGetRandom extends ChooseCardBetweenMultipleToGet {

    // Constructor
    public ChooseCardBetweenMultipleToGetRandom(IContext context, StrategyChooseCardBetweenMultipleToGet nextChooseCardBetweenMultipleToGet) {
        super(context, nextChooseCardBetweenMultipleToGet);
    }

    public ChooseCardBetweenMultipleToGetRandom(StrategyChooseCardBetweenMultipleToGet nextStrategy){
        super(null,nextStrategy);
    }

    public ChooseCardBetweenMultipleToGetRandom() {
        super();
    }

    // Choose
    @Override
    public Card choose(Player player, List<Card> cards) {
        return (Card) ChooseRandom.getInstance().chooseRandom(cards);
    }
}
