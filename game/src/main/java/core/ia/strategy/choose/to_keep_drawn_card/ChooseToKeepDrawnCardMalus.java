package core.ia.strategy.choose.to_keep_drawn_card;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFromPrefList;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

public class ChooseToKeepDrawnCardMalus extends ChooseToKeepDrawnCard {

    public ChooseToKeepDrawnCardMalus(IContext context, StrategyChooseToKeepDrawnCard nextStrategy){
        super(context, nextStrategy);
    }

    public ChooseToKeepDrawnCardMalus(StrategyChooseToKeepDrawnCard nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseToKeepDrawnCardMalus() {
        super(Context.everyTime, new ChooseToKeepDrawnCardRandom());
    }

    public Boolean choose(Player player, Card card) {
        return ChooseFromPrefList.getInstance().chooseToKeepCard(card, CardUtil.getMalusCards());
    }
}
