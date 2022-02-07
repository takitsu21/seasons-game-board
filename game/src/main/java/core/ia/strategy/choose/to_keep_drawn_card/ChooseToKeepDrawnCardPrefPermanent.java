package core.ia.strategy.choose.to_keep_drawn_card;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public class ChooseToKeepDrawnCardPrefPermanent extends ChooseToKeepDrawnCard {

    public ChooseToKeepDrawnCardPrefPermanent(IContext context, StrategyChooseToKeepDrawnCard nextStrategy){
        super(context, nextStrategy);
    }

    public ChooseToKeepDrawnCardPrefPermanent(StrategyChooseToKeepDrawnCard nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseToKeepDrawnCardPrefPermanent() {
        super(Context.everyTime, new ChooseToKeepDrawnCardRandom());
    }

    public Boolean choose(Player player, Card card) {
        return card.hasPermanentEffect();
    }
}
