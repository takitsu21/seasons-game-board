package core.ia.strategy.choose.cards_to_activate;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

import java.util.ArrayList;

public class ChooseCardsToActivateTime extends ChooseCardsToActivate {

    public ChooseCardsToActivateTime(IContext context, StrategyChooseCardsToActivate nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardsToActivateTime(StrategyChooseCardsToActivate nextStrategy){ super(null, nextStrategy);}
    public ChooseCardsToActivateTime() {
        super(Context.everyTime, new ChooseCardsToActivateRandom());
    }

    @Override
    public Card choose(Player player) {
        ArrayList<Class<?>> cards = CardUtil.getCardsPlayedWithTime();
        ArrayList<Card> cardsOnBord = (ArrayList<Card>) player.getFacadeIA().getActivableCard();
        for (Card card : cardsOnBord) {
            if (cards.contains(card.getClass()) && !card.isActivated()) {
                return card;
            }
        }
        return null;
    }
}
