package core.ia.strategy.choose.card_to_summon_for_free;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public class ChooseCardToSummonForFreeAdaptative extends ChooseCardToSummonForFree {
    Adaptative self;

    public ChooseCardToSummonForFreeAdaptative(IContext context, StrategyChooseCardToSummonForFree nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardToSummonForFreeAdaptative(StrategyChooseCardToSummonForFree nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseCardToSummonForFreeAdaptative(Adaptative player) {
        super(Context.everyTime, new ChooseCardToSummonForFreeRandom());
        self = player;
    }

    @Override
    public Card choose(Player player) {
        self.analyzeOpponentStrategy();
        Card result = self.chooseCardToSummonForFree();
        self.setStrategiesToAdaptative();
        return result;
    }
}