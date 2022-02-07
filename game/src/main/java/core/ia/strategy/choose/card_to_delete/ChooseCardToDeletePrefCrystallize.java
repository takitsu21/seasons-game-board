package core.ia.strategy.choose.card_to_delete;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFromPrefList;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

import java.util.List;

public class ChooseCardToDeletePrefCrystallize extends ChooseCardToDelete {

    public ChooseCardToDeletePrefCrystallize(IContext context, StrategyChooseCardToDelete nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardToDeletePrefCrystallize(StrategyChooseCardToDelete nextStrategy){
        super(null, nextStrategy);}

    public ChooseCardToDeletePrefCrystallize() {
        super(Context.everyTime, new ChooseCardToDeleteRandom());
    }

    @Override
    public Card choose(Player player) {
        List<Card> cards = player.getFacadeIA().getCardOnBoard();
        return ChooseFromPrefList.getInstance().chooseCardToRemoveFromBoard(cards, CardUtil.getCrystalCards());
    }
}
