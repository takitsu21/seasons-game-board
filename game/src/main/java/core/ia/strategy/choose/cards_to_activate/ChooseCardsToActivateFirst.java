package core.ia.strategy.choose.cards_to_activate;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardsToActivateFirst extends ChooseCardsToActivate {

    public ChooseCardsToActivateFirst(IContext context, StrategyChooseCardsToActivate nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardsToActivateFirst(StrategyChooseCardsToActivate nextStrategy){ super(null, nextStrategy);}
    public ChooseCardsToActivateFirst() {
        super();
    }

    @Override
    public Card choose(Player player) {
        List<Card> cardsToActivate = player.getFacadeIA().getActivableCard();
        return (Card) ChooseFirst.getInstance().chooseFirst(cardsToActivate);
    }
}
