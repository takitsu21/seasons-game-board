package core.ia.strategy.choose.card_come_back_in_hand;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFromPrefList;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

import java.util.List;

public class ChooseCardComeBackInHandMalus extends ChooseCardComeBackInHand {

    public ChooseCardComeBackInHandMalus(IContext context, StrategyChooseCardComeBackInHand nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardComeBackInHandMalus(StrategyChooseCardComeBackInHand nextStrategy){ super(null,nextStrategy);}
    public ChooseCardComeBackInHandMalus() {
        super(Context.everyTime, new ChooseCardComeBackInHandRandom());
    }

    @Override
    public Card choose(Player player) {
        List<Card> cards = player.getFacadeIA().getMagicCardsOnBoard();
        //la méthode appelée est déjà testée dans CardUtilTest donc ya pas de test pour cette classe
        return ChooseFromPrefList.getInstance().chooseCardToRemoveFromBoard(cards, CardUtil.getMalusCards());
    }
}
