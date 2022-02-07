package core.ia.strategy.choose.energy_to_throw;

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

public class ChooseEnergyToThrowTime extends ChooseEnergyToThrow {

    public ChooseEnergyToThrowTime(IContext context, StrategyChooseEnergyToThrow nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseEnergyToThrowTime(StrategyChooseEnergyToThrow nextStrategy){ super(null, nextStrategy);}
    public ChooseEnergyToThrowTime() {
        super(Context.everyTime, new ChooseEnergyToThrowRandom());
    }

    public Energy choose(Player player) {
        List<Card> cardInHand = new ArrayList<>(player.getFacadeIA().getCardInHand());
        cardInHand = ChooseCardComparator.getInstance().sortCardCompare(cardInHand, new TimeComparator());

        Energy energy = player.getFacadeIA().uselessEnergy(cardInHand);
        if (energy == null) {
            energy = getEnergyToThrow(player, cardInHand);
        }
        return energy;
    }
}
