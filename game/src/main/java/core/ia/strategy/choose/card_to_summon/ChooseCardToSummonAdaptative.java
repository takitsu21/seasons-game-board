package core.ia.strategy.choose.card_to_summon;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public class ChooseCardToSummonAdaptative extends ChooseCardToSummon {
    Adaptative self;

    public ChooseCardToSummonAdaptative(IContext context, StrategyChooseCardToSummon nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardToSummonAdaptative(StrategyChooseCardToSummon nextStrategy){ super(null, nextStrategy);}

    public ChooseCardToSummonAdaptative(Adaptative player) {
        super(Context.everyTime, new ChooseCardToSummonRandom());
        self = player;
    }

    @Override
    public Card choose(Player player) {
        self.analyzeOpponentStrategy();
        Card result = self.chooseCardToSummon();
        self.setStrategiesToAdaptative();
        return result;
    }
}