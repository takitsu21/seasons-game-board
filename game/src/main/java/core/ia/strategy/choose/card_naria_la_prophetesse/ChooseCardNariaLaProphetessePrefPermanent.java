package core.ia.strategy.choose.card_naria_la_prophetesse;

import core.cards.Card;
import core.cards.comparator.CostCardComparator;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFromPrefList;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChooseCardNariaLaProphetessePrefPermanent  extends ChooseCardNariaLaProphetesse {
    public ChooseCardNariaLaProphetessePrefPermanent(IContext context, StrategyChooseCardNariaLaProphetesse nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardNariaLaProphetessePrefPermanent() {
        super(Context.everyTime, new ChooseCardNariaLaProphetesseRandom());
    }

    @Override
    public Map<Player, Card> choose(Player player, List<Card> cards) {
        Map<Player, Card> map = new HashMap<>();
        List<Player> otherPlayers = player.getFacadeIA().getOtherPLayers();

        List<Card> prefCards = CardUtil.getPermanentEffectCardsIn(cards);
        Card card;
        if (!prefCards.isEmpty()) {
            card = Collections.max(prefCards, new CostCardComparator());
        } else {
            card = Collections.max(cards, new CostCardComparator()); //TODO Faut appeler le choose de la nextStrategy non?
        }
        map.put(player, card); //Bon bah ça fait du dupliqué
        cards.remove(card);

        for (Player p : otherPlayers) {
            card = (Card) ChooseRandom.getInstance().chooseRandom(cards);
            map.put(p, card);
            cards.remove(card);
        }
        return map;
    }
}
