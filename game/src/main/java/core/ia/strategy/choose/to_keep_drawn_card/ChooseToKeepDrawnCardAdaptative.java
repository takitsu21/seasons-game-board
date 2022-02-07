package core.ia.strategy.choose.to_keep_drawn_card;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public class ChooseToKeepDrawnCardAdaptative extends ChooseToKeepDrawnCard {
    Adaptative self;

    public ChooseToKeepDrawnCardAdaptative(IContext context, StrategyChooseToKeepDrawnCard nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseToKeepDrawnCardAdaptative(StrategyChooseToKeepDrawnCard nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseToKeepDrawnCardAdaptative(Adaptative player) {
        super(Context.everyTime, new ChooseToKeepDrawnCardRandom());
        self = player;
    }

    @Override
    public Boolean choose(Player player, Card card) {
        self.analyzeOpponentStrategy();
        boolean result = self.chooseToKeepDrawnCard(card);
        self.setStrategiesToAdaptative();
        return result;
    }
}
