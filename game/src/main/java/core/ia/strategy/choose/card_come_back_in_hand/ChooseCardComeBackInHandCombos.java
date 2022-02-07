package core.ia.strategy.choose.card_come_back_in_hand;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseBestCombos;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardComeBackInHandCombos extends ChooseCardComeBackInHand {

    public ChooseCardComeBackInHandCombos(IContext context, StrategyChooseCardComeBackInHand nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardComeBackInHandCombos(StrategyChooseCardComeBackInHand nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseCardComeBackInHandCombos() {
        super(Context.everyTime, new ChooseCardComeBackInHandRandom());
    }

    @Override
    public Card choose(Player player) {
        List<Card> cards = player.getFacadeIA().getMagicCardsOnBoard();
        return ChooseBestCombos.getInstance().chooseCardForCombos(
                cards, player.getFacadeIA().getCardInHand()).getKey();
    }
}
