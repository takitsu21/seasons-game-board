package core.ia.strategy.choose.card_to_summon;

import core.cards.Card;
import core.cards.comparator.CostCardComparator;
import core.ia.Player;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardToSummonPrefInvoc extends ChooseCardToSummon {

    public ChooseCardToSummonPrefInvoc(IContext context, StrategyChooseCardToSummon nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardToSummonPrefInvoc(StrategyChooseCardToSummon nextStrategy){ super(null, nextStrategy);}
    public ChooseCardToSummonPrefInvoc() {
        super(Context.everyTime, new ChooseCardToSummonRandom());
    }

    @Override
    public Card choose(Player player) {
        List<Card> cardInHand = player.getFacadeIA().getCardInHand();
        cardInHand = ChooseCardComparator.getInstance().sortCardCompare(cardInHand, new CostCardComparator());
        return getCardToSummon(player, cardInHand);
    }

}
