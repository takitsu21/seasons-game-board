package core.ia.strategy.choose.to_keep_drawn_card;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

import java.util.ArrayList;

public class ChooseToKeepDrawnCardTime extends ChooseToKeepDrawnCard {

    public ChooseToKeepDrawnCardTime(IContext context, StrategyChooseToKeepDrawnCard nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseToKeepDrawnCardTime() {
        super();
    }

    @Override
    public Boolean choose(Player player, Card card) {
        ArrayList<Class<?>> moreInvocCard = CardUtil.getCardsPlayedWithTime();
        if (moreInvocCard.contains(card.getClass())) {
            return true;
        }
        return false;
    }
}
