package core.ia.strategy.choose.card_between_multiple_to_get;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseBestCombos;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardBetweenMultipleToGetCombos extends ChooseCardBetweenMultipleToGet {

    public ChooseCardBetweenMultipleToGetCombos(IContext context, StrategyChooseCardBetweenMultipleToGet nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardBetweenMultipleToGetCombos(StrategyChooseCardBetweenMultipleToGet nextStrategy){
        super(null,nextStrategy);
    }

    public ChooseCardBetweenMultipleToGetCombos() {
        super(Context.everyTime, new ChooseCardBetweenMultipleToGetRandom());
    }

    @Override
    public Card choose(Player player, List<Card> cardList) {
        return ChooseBestCombos.getInstance().chooseCardForCombos(cardList);
    }
}
