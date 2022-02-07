package core.ia.strategy.choose.card_for_init_hand;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFromPrefList;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;
import core.util.Config;

import java.util.List;

public class ChooseCardForInitHandPrefPermanent extends ChooseCardForInitHand {

    public ChooseCardForInitHandPrefPermanent(IContext context, StrategyChooseCardForInitHand nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardForInitHandPrefPermanent(StrategyChooseCardForInitHand nextStrategy){
        super(null, nextStrategy);
    }

    public ChooseCardForInitHandPrefPermanent() {
        super();
    }

    @Override
    public Card[][] choose(Player player, List<Card> deck, Config config) {
        return ChooseFromPrefList.getInstance().chooseInitHand(deck, CardUtil.getPermanentEffectCardsClassIn(deck), config);
    }
}
