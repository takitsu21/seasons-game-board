package core.ia.strategy.choose.card_come_back_in_hand;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFromPrefList;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

import java.util.List;

public class ChooseCardComeBackInHandPrefPermanent extends ChooseCardComeBackInHand {

    public ChooseCardComeBackInHandPrefPermanent(IContext context, StrategyChooseCardComeBackInHand nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardComeBackInHandPrefPermanent(StrategyChooseCardComeBackInHand nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseCardComeBackInHandPrefPermanent() {
        super(Context.everyTime, new ChooseCardComeBackInHandRandom());
    }

    @Override
    public Card choose(Player player) {
        //la méthode appelée est déjà testée dans CardUtilTest donc ya pas de test pour cette classe
        List<Card> cards = player.getFacadeIA().getMagicCardsOnBoard();
        return ChooseFromPrefList.getInstance().chooseCardToRemoveFromBoard(cards, CardUtil.getPermanentEffectCardsClassIn(cards));
    }
}
