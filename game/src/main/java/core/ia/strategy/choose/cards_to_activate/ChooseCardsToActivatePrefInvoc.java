package core.ia.strategy.choose.cards_to_activate;

import core.cards.Card;
import core.cards.effects.KairnDestructeurEffect;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

import java.util.ArrayList;
import java.util.List;

public class ChooseCardsToActivatePrefInvoc extends ChooseCardsToActivate {

    public ChooseCardsToActivatePrefInvoc(IContext context, StrategyChooseCardsToActivate nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardsToActivatePrefInvoc(StrategyChooseCardsToActivate nextStrategy){ super(null, nextStrategy);}
    public ChooseCardsToActivatePrefInvoc() {
        super(Context.everyTime, new ChooseCardsToActivateRandom());
    }

    @Override
    public Card choose(Player player) {
        ArrayList<Class<?>> cards = CardUtil.getCardsForMoreInvocation();
        ArrayList<Card> cardsOnBord = (ArrayList<Card>) player.getFacadeIA().getActivableCard();
        for (Card card : cardsOnBord) {
            if (cards.contains(card.getClass())) {
                return card;
            }
        }
        return null;
    }
}
