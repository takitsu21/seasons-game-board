package core.ia.strategy.choose.card_to_summon;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardToSummonFirst extends ChooseCardToSummon {

    public ChooseCardToSummonFirst(IContext context, StrategyChooseCardToSummon nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardToSummonFirst(StrategyChooseCardToSummon nextStrategy){ super(null, nextStrategy);}
    public ChooseCardToSummonFirst() {
        super();
    }

    @Override
    public Card choose(Player player) {
        List<Card> summonableCards = player.getFacadeIA().getSummonableCards();
        return (Card) ChooseFirst.getInstance().chooseFirst(summonableCards);
    }

}
