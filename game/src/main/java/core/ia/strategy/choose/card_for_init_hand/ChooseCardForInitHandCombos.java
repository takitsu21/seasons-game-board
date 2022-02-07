package core.ia.strategy.choose.card_for_init_hand;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;
import core.util.Config;

import java.util.List;

public class ChooseCardForInitHandCombos extends ChooseCardForInitHand {

    public ChooseCardForInitHandCombos(IContext context, StrategyChooseCardForInitHand nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardForInitHandCombos(StrategyChooseCardForInitHand nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseCardForInitHandCombos() {
        super();
    }

    public Card[][] choose(Player player, List<Card> deck, Config config) {
        return CardUtil.classifiedCardsByYearAndCombos(deck, config);
    }
}
