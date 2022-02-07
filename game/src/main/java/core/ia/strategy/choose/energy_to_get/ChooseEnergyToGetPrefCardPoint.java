package core.ia.strategy.choose.energy_to_get;

import core.board.enums.Energy;
import core.cards.Card;
import core.cards.comparator.PrestigePointsCardComparator;
import core.ia.Player;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChooseEnergyToGetPrefCardPoint extends ChooseEnergyToGet {

    public ChooseEnergyToGetPrefCardPoint(IContext context, StrategyChooseEnergyToGet nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseEnergyToGetPrefCardPoint(StrategyChooseEnergyToGet nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseEnergyToGetPrefCardPoint() {
        super(Context.everyTime, new ChooseEnergyToGetRandom());
    }

    public Energy choose(Player player) {
        List<Card> cardInHand = new ArrayList<>(player.getFacadeIA().getCardInHand());
        cardInHand = ChooseCardComparator.getInstance().sortCardCompare(cardInHand, new PrestigePointsCardComparator());
        if (cardInHand != null) {
            Collections.reverse(cardInHand);
        }
        return player.getFacadeIA().missingEnergy(cardInHand);
    }
}
