package core.ia.strategy.choose.cards_to_activate;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseBestCombos;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.ChooseUtil;

import java.util.List;

public class ChooseCardsToActivateCombos extends ChooseCardsToActivate {

    public ChooseCardsToActivateCombos(IContext context, StrategyChooseCardsToActivate nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardsToActivateCombos(StrategyChooseCardsToActivate nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseCardsToActivateCombos() {
        super(Context.everyTime, new ChooseCardsToActivateRandom());
    }

    @Override
    public Card choose(Player player) {
        List<Card> cardsToActivate = ChooseUtil.getInstance().chooseCardsPredictor(
                player.getFacadeIA().getCardOnBoard(), Card::isActivable);
        return ChooseBestCombos.getInstance().chooseCardForCombos(
                cardsToActivate);
    }
}
