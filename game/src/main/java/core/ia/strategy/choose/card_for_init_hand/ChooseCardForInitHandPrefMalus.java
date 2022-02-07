package core.ia.strategy.choose.card_for_init_hand;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFromPrefList;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;
import core.util.Config;

import java.util.ArrayList;
import java.util.List;

public class ChooseCardForInitHandPrefMalus extends ChooseCardForInitHand {

    public ChooseCardForInitHandPrefMalus(IContext context, StrategyChooseCardForInitHand nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardForInitHandPrefMalus(StrategyChooseCardForInitHand nextStrategy){ super(null, nextStrategy);}
    public ChooseCardForInitHandPrefMalus() {
        super();
    }

    @Override
    public Card[][] choose(Player player, List<Card> deck, Config config) {
        return ChooseFromPrefList.getInstance().chooseInitHand(deck, CardUtil.getMalusCards(), config);
    }
}
