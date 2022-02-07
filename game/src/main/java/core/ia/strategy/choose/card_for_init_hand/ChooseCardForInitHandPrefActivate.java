package core.ia.strategy.choose.card_for_init_hand;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseActivate;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.Config;

import java.util.List;

public class ChooseCardForInitHandPrefActivate extends ChooseCardForInitHand {
    public ChooseCardForInitHandPrefActivate(IContext context, StrategyChooseCardForInitHand nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardForInitHandPrefActivate(StrategyChooseCardForInitHand nextStrategy) {
        this(null, nextStrategy);
    }

    public ChooseCardForInitHandPrefActivate() {
        super();
    }

    public Card[][] choose(Player player, List<Card> deck, Config config) {
        return ChooseActivate.getInstance().initHand(deck, config);
    }
}
