package core.ia.strategy.choose.card_between_multiple_to_get;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.ChooseUtil;

import java.util.List;

public class ChooseCardBetweenMultipleToGetPrefActivate extends ChooseCardBetweenMultipleToGet {
    public ChooseCardBetweenMultipleToGetPrefActivate(IContext context,
                                                      StrategyChooseCardBetweenMultipleToGet nextChooseCardBetweenMultipleToGet) {
        super(context, nextChooseCardBetweenMultipleToGet);
    }

    public ChooseCardBetweenMultipleToGetPrefActivate(StrategyChooseCardBetweenMultipleToGet nextChooseCardBetweenMultipleToGet) {
        this(null, new ChooseCardBetweenMultipleToGetRandom());
    }

    public ChooseCardBetweenMultipleToGetPrefActivate() {
        super(Context.everyTime, new ChooseCardBetweenMultipleToGetRandom());
    }

    @Override
    public Card choose(Player player, List<Card> cardList) {
        return ChooseUtil.getInstance().chooseCardPredictor(cardList, Card::isActivable);
    }
}
