package core.ia.strategy.choose.energy_to_get;

import core.board.enums.Energy;
import core.cards.Card;
import core.cards.comparator.CostCardComparator;
import core.ia.Player;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.ArrayList;
import java.util.List;

public class ChooseEnergyToGetPrefInvoc extends ChooseEnergyToGet {

    public ChooseEnergyToGetPrefInvoc(IContext context, StrategyChooseEnergyToGet nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseEnergyToGetPrefInvoc(StrategyChooseEnergyToGet nextStrategy){ super(null, nextStrategy);}
    public ChooseEnergyToGetPrefInvoc() {
        super(Context.everyTime, new ChooseEnergyToGetRandom());
    }

    public Energy choose(Player player) {
        List<Card> cardInHand = new ArrayList<>(player.getFacadeIA().getCardInHand());
        cardInHand = ChooseCardComparator.getInstance().sortCardCompare(cardInHand, new CostCardComparator());

        return player.getFacadeIA().missingEnergy(cardInHand);
    }
}
