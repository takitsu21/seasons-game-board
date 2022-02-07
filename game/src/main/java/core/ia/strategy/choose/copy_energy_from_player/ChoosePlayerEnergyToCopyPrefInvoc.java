package core.ia.strategy.choose.copy_energy_from_player;

import core.cards.Card;
import core.cards.comparator.CostCardComparator;
import core.cards.comparator.PrestigePointsCardComparator;
import core.ia.Player;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.ChooseUtil;

public class ChoosePlayerEnergyToCopyPrefInvoc extends ChoosePlayerEnergyToCopy {
    public ChoosePlayerEnergyToCopyPrefInvoc(IContext context, StrategyChoosePlayerEnergyToCopy nextStrategy) {
        super(context, nextStrategy);
    }

    public ChoosePlayerEnergyToCopyPrefInvoc(StrategyChoosePlayerEnergyToCopy nextStrategy) {
        super(null, nextStrategy);
    }

    public ChoosePlayerEnergyToCopyPrefInvoc() {
        super(Context.everyTime, new ChoosePlayerEnergyToCopyRandom());
    }

    @Override
    public Player choose(Player player) {
        Card card = ChooseCardComparator.getInstance().chooseCardMaxCompare(player.getFacadeIA().getCardInHand(),
                new CostCardComparator());
        return ChooseUtil.getInstance().getBestSimilaritiesPlayerEnergies(
                player.getFacadeIA().getOtherPLayers(), card);
    }
}
