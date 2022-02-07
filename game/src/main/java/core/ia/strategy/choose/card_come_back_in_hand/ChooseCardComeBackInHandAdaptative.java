package core.ia.strategy.choose.card_come_back_in_hand;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public class ChooseCardComeBackInHandAdaptative extends ChooseCardComeBackInHand {
    Adaptative self;

    public ChooseCardComeBackInHandAdaptative(IContext context, StrategyChooseCardComeBackInHand nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardComeBackInHandAdaptative(StrategyChooseCardComeBackInHand nextStrategy){
        super(null, nextStrategy);
    }

    public ChooseCardComeBackInHandAdaptative(Adaptative player) {
        super(Context.everyTime, new ChooseCardComeBackInHandRandom());
        self = player;
    }

    @Override
    public Card choose(Player player) {
        self.analyzeOpponentStrategy();
        Card result = self.chooseCardComeBackInHand();
        self.setStrategiesToAdaptative();
        return result;
    }
}