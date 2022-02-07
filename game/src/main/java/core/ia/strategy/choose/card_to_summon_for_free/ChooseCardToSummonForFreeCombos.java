package core.ia.strategy.choose.card_to_summon_for_free;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseBestCombos;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardToSummonForFreeCombos extends ChooseCardToSummonForFree {

    public ChooseCardToSummonForFreeCombos(IContext context, StrategyChooseCardToSummonForFree nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardToSummonForFreeCombos(StrategyChooseCardToSummonForFree nextStrategy){ super(null,nextStrategy);}
    public ChooseCardToSummonForFreeCombos() {
        super(Context.everyTime, new ChooseCardToSummonForFreeRandom());
    }

    @Override
    public Card choose(Player player) {
        List<Card> cardInHand = player.getFacadeIA().getCardInHand();
        return ChooseBestCombos.getInstance().chooseCardForCombos(cardInHand);
    }
}
