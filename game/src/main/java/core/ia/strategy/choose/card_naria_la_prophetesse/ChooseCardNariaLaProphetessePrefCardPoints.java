package core.ia.strategy.choose.card_naria_la_prophetesse;

import core.cards.Card;
import core.cards.comparator.CostCardComparator;
import core.cards.comparator.PlayerPrestigePointsCardsComparator;
import core.cards.comparator.PrestigePointsCardComparator;
import core.cards.comparator.SizeCardOnBoardPlayerComparator;
import core.ia.Player;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.ChooseUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ChooseCardNariaLaProphetessePrefCardPoints extends ChooseCardNariaLaProphetesse {
    public ChooseCardNariaLaProphetessePrefCardPoints(IContext context, StrategyChooseCardNariaLaProphetesse nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardNariaLaProphetessePrefCardPoints() {
        super(Context.everyTime, new ChooseCardNariaLaProphetesseRandom());
    }

    @Override
    public Map<Player, Card> choose(Player player, List<Card> cards) {

        cards = ChooseCardComparator.getInstance().sortCardCompare(cards, new PrestigePointsCardComparator());
        List<Player> otherPlayer = player.getFacadeIA().getOtherPLayers();
        otherPlayer.sort(new PlayerPrestigePointsCardsComparator());
        Collections.reverse(otherPlayer);

        return ChooseUtil.getInstance().chooseCardsForNaria(
                player,
                otherPlayer,
                cards,
                Collections.max(cards, new PrestigePointsCardComparator()));
    }
}
