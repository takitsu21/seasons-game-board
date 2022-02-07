package core.ia.strategy.choose.to_keep_drawn_card;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public class ChooseToKeepDrawnCardPrefCardPoints extends ChooseToKeepDrawnCard {
    public ChooseToKeepDrawnCardPrefCardPoints(IContext context, StrategyChooseToKeepDrawnCard nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseToKeepDrawnCardPrefCardPoints(StrategyChooseToKeepDrawnCard nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseToKeepDrawnCardPrefCardPoints() {
        super(Context.everyTime, new ChooseToKeepDrawnCardRandom());
    }

    @Override
    public Boolean choose(Player player, Card card) {
        return card.getPrestigePointValue() > 0;
    }
}
