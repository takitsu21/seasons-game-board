package core.ia.strategy.choose.card_naria_la_prophetesse;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseBestCombos;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.ChooseUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChooseCardNariaLaProphetessePrefActivate extends ChooseCardNariaLaProphetesse {
    public ChooseCardNariaLaProphetessePrefActivate(IContext context, StrategyChooseCardNariaLaProphetesse nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardNariaLaProphetessePrefActivate() {
        super(Context.everyTime, new ChooseCardNariaLaProphetesseRandom());
    }

    @Override
    public Map<Player, Card> choose(Player player, List<Card> cards) {
        return ChooseUtil.getInstance().chooseCardsForNaria(
                player,
                player.getFacadeIA().getOtherPLayers(),
                cards,
                ChooseUtil.getInstance().chooseCardPredictor(cards, Card::isActivable));
    }
}
