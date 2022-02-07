package core.ia.strategy.choose.card_between_multiple_to_get;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFromPrefList;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

import java.util.List;

public class ChooseCardBetweenMultipleToGetMalus extends ChooseCardBetweenMultipleToGet {


    // Constructor
    public ChooseCardBetweenMultipleToGetMalus(IContext context, StrategyChooseCardBetweenMultipleToGet nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardBetweenMultipleToGetMalus(StrategyChooseCardBetweenMultipleToGet nextStrategy){
        super(null, nextStrategy);
    }

    public ChooseCardBetweenMultipleToGetMalus() {
        super(Context.everyTime, new ChooseCardBetweenMultipleToGetRandom());
    }


    // Choose
    @Override
    public Card choose(Player player, List<Card> cardList) {
        return ChooseFromPrefList.getInstance().chooseCardToGetFromList(cardList, CardUtil.getMalusCards());
    }
}
