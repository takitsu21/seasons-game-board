package core.ia.strategy.choose.card_come_back_in_hand;

import core.cards.Card;
import core.cards.comparator.CostCardComparator;
import core.ia.Player;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardComeBackInHandPrefInvoc extends ChooseCardComeBackInHand {

    public ChooseCardComeBackInHandPrefInvoc(IContext context, StrategyChooseCardComeBackInHand nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardComeBackInHandPrefInvoc(StrategyChooseCardComeBackInHand nextStrategy){ super(null, nextStrategy);}
    public ChooseCardComeBackInHandPrefInvoc() {
        super(Context.everyTime, new ChooseCardComeBackInHandRandom());
    }

    @Override
    public Card choose(Player player) {
        List<Card> cards = player.getFacadeIA().getMagicCardsOnBoard();
        return ChooseCardComparator.getInstance().chooseCardMinCompare(cards, new CostCardComparator());
    }

}