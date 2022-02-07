package core.ia.strategy.choose.card_to_summon_for_free;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardToSummonForFreeFirst extends ChooseCardToSummonForFree {

    public ChooseCardToSummonForFreeFirst(IContext context, StrategyChooseCardToSummonForFree nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardToSummonForFreeFirst(StrategyChooseCardToSummonForFree nextStrategy){ super(null, nextStrategy);}
    public ChooseCardToSummonForFreeFirst() {
        super();
    }

    public Card choose(Player player) {
        List<Card> cardInHand = player.getFacadeIA().getCardInHand();
        return (Card) ChooseFirst.getInstance().chooseFirst(cardInHand);
    }

}
