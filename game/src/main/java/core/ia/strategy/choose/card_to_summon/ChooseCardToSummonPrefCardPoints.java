package core.ia.strategy.choose.card_to_summon;

import core.cards.Card;
import core.cards.comparator.PrestigePointsCardComparator;
import core.ia.Player;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.Collections;
import java.util.List;

public class ChooseCardToSummonPrefCardPoints extends ChooseCardToSummon {

    public ChooseCardToSummonPrefCardPoints(IContext context, StrategyChooseCardToSummon nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardToSummonPrefCardPoints(StrategyChooseCardToSummon nextStrategy){ super(null, nextStrategy);}
    public ChooseCardToSummonPrefCardPoints() {
        super(Context.everyTime, new ChooseCardToSummonRandom());
    }

    public Card choose(Player player) {
        List<Card> cardInHand = player.getFacadeIA().getCardInHand();
        cardInHand = ChooseCardComparator.getInstance().sortCardCompare(cardInHand, new PrestigePointsCardComparator());
        Collections.reverse(cardInHand);
        return getCardToSummon(player, cardInHand);
    }
}
