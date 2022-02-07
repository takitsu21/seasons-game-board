package core.ia.strategy.choose.card_come_back_in_hand;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.ChooseUtil;

import java.util.List;

public class ChooseCardComeBackInHandPrefActivate extends ChooseCardComeBackInHand {

    public ChooseCardComeBackInHandPrefActivate(IContext context, StrategyChooseCardComeBackInHand nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardComeBackInHandPrefActivate(StrategyChooseCardComeBackInHand nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseCardComeBackInHandPrefActivate() {
        super(Context.everyTime, new ChooseCardComeBackInHandRandom());
    }

    @Override
    public Card choose(Player player) {
        List<Card> cards = player.getFacadeIA().getMagicCardsOnBoard();
        return ChooseUtil.getInstance().chooseCardPredictor(cards,
                c -> !c.isActivable());
    }
}
