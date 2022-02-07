package core.ia.strategy.choose.card_for_init_hand;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.IContext;
import core.util.Config;

import java.util.List;

public class ChooseCardForInitHandAdaptative extends ChooseCardForInitHand {
    Adaptative self;

    public ChooseCardForInitHandAdaptative(IContext context, StrategyChooseCardForInitHand nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardForInitHandAdaptative(StrategyChooseCardForInitHand nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseCardForInitHandAdaptative(Adaptative player) {
        super();
        self = player;
    }

    public Card[][] choose(Player player, List<Card> deck, Config config) {
        self.analyzeOpponentStrategy();
        Card[][] result = self.chooseCardForInitHand(deck);
        self.setStrategiesToAdaptative();
        return result;
    }
}