package core.ia.strategy.choose.to_keep_drawn_card;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.IContext;

public class ChooseToKeepDrawnCardFirst extends ChooseToKeepDrawnCard {

    public ChooseToKeepDrawnCardFirst(IContext context, StrategyChooseToKeepDrawnCard nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseToKeepDrawnCardFirst() {
        super();
    }

    @Override
    public Boolean choose(Player player, Card card) {
        return ChooseFirst.getInstance().chooseFirst();
    }
}
