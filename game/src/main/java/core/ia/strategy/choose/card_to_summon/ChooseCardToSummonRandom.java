package core.ia.strategy.choose.card_to_summon;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardToSummonRandom extends ChooseCardToSummon {

    public ChooseCardToSummonRandom(IContext context, StrategyChooseCardToSummon nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardToSummonRandom(StrategyChooseCardToSummon nextStrategy){super(null, nextStrategy);}
    public ChooseCardToSummonRandom() {
        super();
    }

    public Card choose(Player player) {
        List<Card> summonableCards = player.getFacadeIA().getSummonableCards();
        return (Card) ChooseRandom.getInstance().chooseRandom(summonableCards);
    }
}
