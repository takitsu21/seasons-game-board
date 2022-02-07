package core.ia.strategy.choose.to_keep_drawn_card;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

import java.util.ArrayList;

public class ChooseToKeepDrawnCardPrefInvoc extends ChooseToKeepDrawnCard {

    public ChooseToKeepDrawnCardPrefInvoc(IContext context, StrategyChooseToKeepDrawnCard nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseToKeepDrawnCardPrefInvoc() {
        super();
    }

    @Override
    public Boolean choose(Player player, Card card) {
        ArrayList<Class<?>> moreInvocCard = CardUtil.getCardsForMoreInvocation();
        if (moreInvocCard.contains(card.getClass())) {
            return true;
        }
        if(player.getFacadeIA().getCardInHand().isEmpty()
                && player.getFacadeIA().getNbYear()<=player.getFacadeIA().getConfig().getNbYears()){
            return true;
        }
        return false;
    }
}
