package core.ia.strategy.choose.energy_to_get;

import core.board.enums.Energy;
import core.cards.Card;
import core.cards.comparator.CostCardComparator;
import core.cards.comparator.TimeComparator;
import core.ia.Player;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.ArrayList;
import java.util.List;

public class ChooseEnergyToGetTime extends ChooseEnergyToGet {

    public ChooseEnergyToGetTime(IContext context, StrategyChooseEnergyToGet nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseEnergyToGetTime(StrategyChooseEnergyToGet nextStrategy){ super(null, nextStrategy);}
    public ChooseEnergyToGetTime() {
        super(Context.everyTime, new ChooseEnergyToGetRandom());
    }

    public Energy choose(Player player) {
        List<Card> cardInHand = new ArrayList<>(player.getFacadeIA().getCardInHand());
        cardInHand = ChooseCardComparator.getInstance().sortCardCompare(cardInHand, new TimeComparator());

        return player.getFacadeIA().missingEnergy(cardInHand);
    }
}
