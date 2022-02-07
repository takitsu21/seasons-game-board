package core.ia.strategy.choose.card_naria_la_prophetesse;

import core.cards.Card;
import core.cards.comparator.TimeComparator;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.ChooseUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ChooseCardNariaLaProphetesseTime extends ChooseCardNariaLaProphetesse {
    public ChooseCardNariaLaProphetesseTime(IContext context, StrategyChooseCardNariaLaProphetesse nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardNariaLaProphetesseTime() {
        super(Context.everyTime, new ChooseCardNariaLaProphetesseRandom());
    }

    @Override
    public Map<Player, Card> choose(Player player, List<Card> cards) {
        return ChooseUtil.getInstance().chooseCardsForNaria(
                player,
                player.getFacadeIA().getOtherPLayers(),
                cards,
                Collections.min(cards, new TimeComparator()));
    }
}
