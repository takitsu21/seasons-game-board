package core.ia.strategy.choose.card_for_init_hand;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFromPrefList;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;
import core.util.Config;

import java.util.List;

public class ChooseCardForInitHandPrefCrystallize extends ChooseCardForInitHand {

    public ChooseCardForInitHandPrefCrystallize(IContext context, StrategyChooseCardForInitHand nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardForInitHandPrefCrystallize(StrategyChooseCardForInitHand nextStrategy){
        super(null, nextStrategy);
    }

    public ChooseCardForInitHandPrefCrystallize() {
        super();
    }

    @Override
    public Card[][] choose(Player player, List<Card> deck, Config config) {
        return ChooseFromPrefList.getInstance().chooseInitHand(deck, CardUtil.getCrystalCards(), config);
    }
}
