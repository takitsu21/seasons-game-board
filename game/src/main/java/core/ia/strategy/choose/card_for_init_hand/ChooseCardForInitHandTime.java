package core.ia.strategy.choose.card_for_init_hand;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFromPrefList;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;
import core.util.Config;

import java.util.List;

public class ChooseCardForInitHandTime extends ChooseCardForInitHand {

    public ChooseCardForInitHandTime(IContext context, StrategyChooseCardForInitHand nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardForInitHandTime(StrategyChooseCardForInitHand nextStrategy){ super(null, nextStrategy);}
    public ChooseCardForInitHandTime() {
        super();
    }

    @Override
    public Card[][] choose(Player player, List<Card> deck, Config config) {
        return ChooseFromPrefList.getInstance().chooseInitHand(deck, CardUtil.getCardsPlayedWithTime(), config);
    }
}
