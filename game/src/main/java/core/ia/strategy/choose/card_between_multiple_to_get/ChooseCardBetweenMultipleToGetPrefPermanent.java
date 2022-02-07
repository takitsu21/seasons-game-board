package core.ia.strategy.choose.card_between_multiple_to_get;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFromPrefList;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

import java.util.List;

public class ChooseCardBetweenMultipleToGetPrefPermanent extends ChooseCardBetweenMultipleToGet {
    public ChooseCardBetweenMultipleToGetPrefPermanent(IContext context,
                                                      StrategyChooseCardBetweenMultipleToGet nextChooseCardBetweenMultipleToGet) {
        super(context, nextChooseCardBetweenMultipleToGet);
    }

    public ChooseCardBetweenMultipleToGetPrefPermanent(StrategyChooseCardBetweenMultipleToGet nextChooseCardBetweenMultipleToGet) {
        super(null, nextChooseCardBetweenMultipleToGet);
    }

    public ChooseCardBetweenMultipleToGetPrefPermanent() {
        super(Context.everyTime, new ChooseCardBetweenMultipleToGetRandom());
    }

    @Override
    public Card choose(Player player, List<Card> cardList) {
        return ChooseFromPrefList.getInstance().chooseCardToGetFromList(cardList, CardUtil.getPermanentEffectCardsClassIn(cardList));
    }
}
