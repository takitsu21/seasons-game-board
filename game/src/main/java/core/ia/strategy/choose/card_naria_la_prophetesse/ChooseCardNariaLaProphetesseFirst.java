package core.ia.strategy.choose.card_naria_la_prophetesse;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.IContext;
import core.util.ChooseUtil;

import java.util.List;
import java.util.Map;

public class ChooseCardNariaLaProphetesseFirst extends ChooseCardNariaLaProphetesse {

    public ChooseCardNariaLaProphetesseFirst(IContext context, StrategyChooseCardNariaLaProphetesse nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardNariaLaProphetesseFirst(StrategyChooseCardNariaLaProphetesse nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseCardNariaLaProphetesseFirst() {
        super();
    }

    @Override
    public Map<Player, Card> choose(Player player, List<Card> cards) {
        return ChooseUtil.getInstance().chooseCardsForNaria(
                player,
                player.getFacadeIA().getOtherPLayers(),
                cards,
                (Card) ChooseFirst.getInstance().chooseFirst(cards));

    }
}
