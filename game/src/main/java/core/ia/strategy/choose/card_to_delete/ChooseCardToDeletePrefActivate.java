package core.ia.strategy.choose.card_to_delete;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.ChooseUtil;

import java.util.List;

public class ChooseCardToDeletePrefActivate extends ChooseCardToDelete {
    public ChooseCardToDeletePrefActivate(IContext context, StrategyChooseCardToDelete nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardToDeletePrefActivate(StrategyChooseCardToDelete nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseCardToDeletePrefActivate() {
        this(Context.everyTime, new ChooseCardToDeleteRandom());
    }

    @Override
    public Card choose(Player player) {
        List<Card> cards = player.getFacadeIA().getCardOnBoard();
        return ChooseUtil.getInstance().chooseCardPredictor(cards, c -> (!c.isActivable()));
    }
}
