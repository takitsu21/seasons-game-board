package core.ia.strategy.choose.card_naria_la_prophetesse;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.ChooseUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChooseCardNariaLaProphetesseRandom extends ChooseCardNariaLaProphetesse {

    public ChooseCardNariaLaProphetesseRandom(IContext context, StrategyChooseCardNariaLaProphetesse nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardNariaLaProphetesseRandom(StrategyChooseCardNariaLaProphetesse nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseCardNariaLaProphetesseRandom() {
        super();
    }

    @Override
    public Map<Player, Card> choose(Player player, List<Card> cards) {

        Map<Player, Card> map = new HashMap<>();
        //List<Player> otherPlayers = player.getFacadeIA().getOtherPLayers();
        Card card=(Card) ChooseRandom.getInstance().chooseRandom(cards);
        map.put(player, card);
        cards.remove(card);
        for (Player p : player.getFacadeIA().getOtherPLayers()) {
            card = (Card) ChooseRandom.getInstance().chooseRandom(cards);
            map.put(p, card);
            cards.remove(card);
        }
        return map;
    }
}
