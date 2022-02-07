package core.ia.strategy.choose.card_between_multiple_to_get;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardBetweenMultipleToGetAdaptative extends ChooseCardBetweenMultipleToGet {
    private Adaptative self;

    public ChooseCardBetweenMultipleToGetAdaptative(IContext context, StrategyChooseCardBetweenMultipleToGet nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardBetweenMultipleToGetAdaptative(StrategyChooseCardBetweenMultipleToGet nextStrategy){
        super(null,nextStrategy);
    }

    public ChooseCardBetweenMultipleToGetAdaptative(Adaptative player) {
        super(Context.everyTime, new ChooseCardBetweenMultipleToGetRandom());
        self = player;
    }

    @Override
    public Card choose(Player player, List<Card> cardList) {
        self.analyzeOpponentStrategy();
        Card result = self.chooseCardBetweenMultipleToGet(cardList);
        self.setStrategiesToAdaptative();
        return result;
    }
}
