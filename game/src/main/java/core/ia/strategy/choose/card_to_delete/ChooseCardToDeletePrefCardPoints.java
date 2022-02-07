package core.ia.strategy.choose.card_to_delete;

import core.cards.Card;
import core.cards.comparator.PrestigePointsCardComparator;
import core.ia.Player;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardToDeletePrefCardPoints extends ChooseCardToDelete {

    public ChooseCardToDeletePrefCardPoints(IContext context, StrategyChooseCardToDelete nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardToDeletePrefCardPoints(StrategyChooseCardToDelete nextStrategy){ super(null, nextStrategy);}
    public ChooseCardToDeletePrefCardPoints() {
        super(Context.everyTime, new ChooseCardToDeleteRandom());
    }

    @Override
    public Card choose(Player player) {
        List<Card> cards = player.getFacadeIA().getCardOnBoard();
        return ChooseCardComparator.getInstance().chooseCardMinCompare(cards, new PrestigePointsCardComparator());
    }
}
