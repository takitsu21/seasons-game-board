package core.ia.strategy.choose.cards_to_activate;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;


public class ChooseCardsToActivateAdaptative extends ChooseCardsToActivate {
    Adaptative self;

    public ChooseCardsToActivateAdaptative(IContext context, StrategyChooseCardsToActivate nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardsToActivateAdaptative(StrategyChooseCardsToActivate nextStrategy){ super(null, nextStrategy);}

    public ChooseCardsToActivateAdaptative(Adaptative player) {
        super(Context.everyTime, new ChooseCardsToActivateRandom());
        self = player;
    }

    @Override
    public Card choose(Player player) {
        self.analyzeOpponentStrategy();
        Card result = self.chooseCardToActivate();
        self.setStrategiesToAdaptative();
        return result;
    }
}
