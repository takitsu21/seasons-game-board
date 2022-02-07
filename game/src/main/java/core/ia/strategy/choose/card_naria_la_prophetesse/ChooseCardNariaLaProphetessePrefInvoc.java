package core.ia.strategy.choose.card_naria_la_prophetesse;

import core.cards.Card;
import core.cards.comparator.CostCardComparator;
import core.cards.comparator.SizeCardOnBoardPlayerComparator;
import core.ia.Player;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.ChooseUtil;
import core.ia.strategy.choose.*;

import java.util.*;

public class ChooseCardNariaLaProphetessePrefInvoc extends ChooseCardNariaLaProphetesse {

    public ChooseCardNariaLaProphetessePrefInvoc(IContext context, StrategyChooseCardNariaLaProphetesse nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardNariaLaProphetessePrefInvoc(StrategyChooseCardNariaLaProphetesse nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseCardNariaLaProphetessePrefInvoc() {
        super(Context.everyTime, new ChooseCardNariaLaProphetesseRandom());
    }

    @Override
    public Map<Player, Card> choose(Player player, List<Card> cards) {
        cards = ChooseCardComparator.getInstance().sortCardCompare(cards, new CostCardComparator());
        List<Player> otherPlayer = player.getFacadeIA().getOtherPLayers();
        otherPlayer.sort(new SizeCardOnBoardPlayerComparator());
        Collections.reverse(otherPlayer);
        return ChooseUtil.getInstance().chooseCardsForNaria(
                player,
                otherPlayer,
                cards,
                Collections.min(cards, new CostCardComparator()));
    }
}
