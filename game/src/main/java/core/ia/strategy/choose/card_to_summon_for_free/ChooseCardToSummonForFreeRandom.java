package core.ia.strategy.choose.card_to_summon_for_free;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardToSummonForFreeRandom extends ChooseCardToSummonForFree {

    public ChooseCardToSummonForFreeRandom(IContext context, StrategyChooseCardToSummonForFree nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardToSummonForFreeRandom(StrategyChooseCardToSummonForFree nextStrategy){ super(null, nextStrategy);}
    public ChooseCardToSummonForFreeRandom() {
        super();
    }

    public Card choose(Player player) {
        List<Card> cardInHand = player.getFacadeIA().getCardInHand();
        return (Card) ChooseRandom.getInstance().chooseRandom(cardInHand);
    }
}
