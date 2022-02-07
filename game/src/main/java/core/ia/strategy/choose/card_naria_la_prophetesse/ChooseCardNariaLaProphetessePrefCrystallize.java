package core.ia.strategy.choose.card_naria_la_prophetesse;

import core.cards.Card;
import core.cards.comparator.*;
import core.ia.Player;
import core.ia.strategy.choose.*;
import core.util.CardUtil;
import core.util.ChooseUtil;

import java.util.List;
import java.util.Map;

public class ChooseCardNariaLaProphetessePrefCrystallize extends ChooseCardNariaLaProphetesse {
    public ChooseCardNariaLaProphetessePrefCrystallize(IContext context, StrategyChooseCardNariaLaProphetesse nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardNariaLaProphetessePrefCrystallize() {
        super(Context.everyTime, new ChooseCardNariaLaProphetesseRandom());
    }

    @Override
    public Map<Player, Card> choose(Player player, List<Card> cards) {
        cards = ChooseCardComparator.getInstance().sortCardCompare(cards, new PrefCardListComparator(CardUtil.getCrystalCards()));

        List<Player> otherPlayer = player.getFacadeIA().getOtherPLayers();
        otherPlayer.sort(new PlayerCrystalPointComparator());

        return ChooseUtil.getInstance().chooseCardsForNaria(
                player,
                otherPlayer,
                cards,
                ChooseFromPrefList.getInstance().chooseCardToGetFromList(cards, CardUtil.getCrystalCards()));
    }
}
