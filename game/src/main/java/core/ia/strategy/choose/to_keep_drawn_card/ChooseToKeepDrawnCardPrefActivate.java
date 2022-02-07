package core.ia.strategy.choose.to_keep_drawn_card;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public class ChooseToKeepDrawnCardPrefActivate extends ChooseToKeepDrawnCard {
    public ChooseToKeepDrawnCardPrefActivate(IContext context, StrategyChooseToKeepDrawnCard nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseToKeepDrawnCardPrefActivate(StrategyChooseToKeepDrawnCard nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseToKeepDrawnCardPrefActivate() {
        super(Context.everyTime, new ChooseToKeepDrawnCardRandom());
    }

    @Override
    public Boolean choose(Player player, Card card) {
        return card.isActivable();
    }
}
