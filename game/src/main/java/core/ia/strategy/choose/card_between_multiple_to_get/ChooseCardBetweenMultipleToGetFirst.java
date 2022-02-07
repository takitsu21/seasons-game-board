package core.ia.strategy.choose.card_between_multiple_to_get;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardBetweenMultipleToGetFirst extends ChooseCardBetweenMultipleToGet {


    // Constructor
    public ChooseCardBetweenMultipleToGetFirst(IContext context, StrategyChooseCardBetweenMultipleToGet nextChooseCardBetweenMultipleToGet) {
        super(context, nextChooseCardBetweenMultipleToGet);
    }

    public ChooseCardBetweenMultipleToGetFirst(StrategyChooseCardBetweenMultipleToGet nextStrategy){
        super(null,nextStrategy);
    }

    public ChooseCardBetweenMultipleToGetFirst() {
        super();
    }


    // Choose
    @Override
    public Card choose(Player player, List<Card> cards) {
        return (Card) ChooseFirst.getInstance().chooseFirst(cards);
    }
}
