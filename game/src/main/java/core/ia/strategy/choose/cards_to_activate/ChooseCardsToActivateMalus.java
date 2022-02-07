package core.ia.strategy.choose.cards_to_activate;

import core.cards.Card;
import core.cards.effects.DeDeLaMaliceEffect;
import core.cards.effects.KairnDestructeurEffect;
import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardsToActivateMalus extends ChooseCardsToActivate {

    public ChooseCardsToActivateMalus(IContext context, StrategyChooseCardsToActivate nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardsToActivateMalus(StrategyChooseCardsToActivate nextStrategy){ super(null, nextStrategy);}
    public ChooseCardsToActivateMalus() {
        super(Context.everyTime, new ChooseCardsToActivateRandom());
    }

    @Override
    public Card choose(Player player) {
        List<Card> cards = player.getFacadeIA().getActivableCard();
        for (Card card : cards) {
            if (card instanceof KairnDestructeurEffect && !card.isActivated()) {
                return card;
            }
        }
        return null;
    }
}
