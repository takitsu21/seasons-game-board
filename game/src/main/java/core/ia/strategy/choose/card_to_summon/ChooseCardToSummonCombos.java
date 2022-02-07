package core.ia.strategy.choose.card_to_summon;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseBestCombos;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.card_to_delete.StrategyChooseCardToDelete;

import java.util.List;

public class ChooseCardToSummonCombos extends ChooseCardToSummon {

    public ChooseCardToSummonCombos(IContext context, StrategyChooseCardToSummon nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardToSummonCombos(StrategyChooseCardToSummon nextStrategy){ super(null, nextStrategy);}
    public ChooseCardToSummonCombos() {
        super(Context.everyTime, new ChooseCardToSummonRandom());
    }

    @Override
    public Card choose(Player player) {
        List<Card> cardInHand = player.getFacadeIA().getSummonableCards();
        return ChooseBestCombos.getInstance().chooseCardForCombos(cardInHand);
    }
}
