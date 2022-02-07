package core.ia.strategy.choose.card_to_delete;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseBestCombos;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardToDeleteCombos extends ChooseCardToDelete {

    public ChooseCardToDeleteCombos(IContext context, StrategyChooseCardToDelete nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardToDeleteCombos(StrategyChooseCardToDelete nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseCardToDeleteCombos() {
        super(Context.everyTime, new ChooseCardToDeleteRandom());
    }

    public Card choose(Player player) {
        List<Card> cards = player.getFacadeIA().getCardOnBoard();
        return ChooseBestCombos.getInstance().worstComboCard(cards, player.getFacadeIA().getCardInHand());
    }
}
