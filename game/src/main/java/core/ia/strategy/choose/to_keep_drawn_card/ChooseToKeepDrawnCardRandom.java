package core.ia.strategy.choose.to_keep_drawn_card;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;

public class ChooseToKeepDrawnCardRandom extends ChooseToKeepDrawnCard {

    public ChooseToKeepDrawnCardRandom(IContext context, StrategyChooseToKeepDrawnCard nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseToKeepDrawnCardRandom() {
        super();
    }

    public Boolean choose(Player player, Card card) {
        return ChooseRandom.getInstance().chooseRandom();
    }
}
