package core.ia.strategy.choose.to_keep_drawn_card;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseBestCombos;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

import java.util.List;

public class ChooseToKeepDrawnCardCombo extends ChooseToKeepDrawnCard{
    public ChooseToKeepDrawnCardCombo(IContext context, StrategyChooseToKeepDrawnCard nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseToKeepDrawnCardCombo(StrategyChooseToKeepDrawnCard nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseToKeepDrawnCardCombo() {
        super(Context.everyTime, new ChooseToKeepDrawnCardRandom());
    }

    @Override
    public Boolean choose(Player player, Card card) {
        List<Card> cardsInHand = CardUtil.findBestCombos(player.getFacadeIA().getCardInHand(), card);
        List<Card> cardsOnBoard = CardUtil.findBestCombos(player.getFacadeIA().getCardOnBoard(), card);
        return cardsInHand.size() > 0 || cardsOnBoard.size() > 0;
    }
}
