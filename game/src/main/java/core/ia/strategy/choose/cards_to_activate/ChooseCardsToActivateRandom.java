package core.ia.strategy.choose.cards_to_activate;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardsToActivateRandom extends ChooseCardsToActivate {

    public ChooseCardsToActivateRandom(IContext context, StrategyChooseCardsToActivate nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardsToActivateRandom(StrategyChooseCardsToActivate nextStrategy){ super(null, nextStrategy);}
    public ChooseCardsToActivateRandom() {
        super();
    }

    @Override
    public Card choose(Player player) {
        List<Card> cardsToActivate = player.getFacadeIA().getActivableCard();
        return (Card) ChooseRandom.getInstance().chooseRandom(cardsToActivate);
    }
}
