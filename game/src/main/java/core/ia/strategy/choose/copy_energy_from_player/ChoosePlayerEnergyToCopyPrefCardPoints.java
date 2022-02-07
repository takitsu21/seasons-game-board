package core.ia.strategy.choose.copy_energy_from_player;

import core.cards.Card;
import core.cards.comparator.PrestigePointsCardComparator;
import core.ia.Player;
import core.ia.strategy.choose.ChooseCardComparator;
import core.util.ChooseUtil;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

public class ChoosePlayerEnergyToCopyPrefCardPoints extends ChoosePlayerEnergyToCopy {
    public ChoosePlayerEnergyToCopyPrefCardPoints(IContext context, StrategyChoosePlayerEnergyToCopy nextStrategy) {
        super(context, nextStrategy);
    }

    public ChoosePlayerEnergyToCopyPrefCardPoints(StrategyChoosePlayerEnergyToCopy nextStrategy) {
        super(null, nextStrategy);
    }

    public ChoosePlayerEnergyToCopyPrefCardPoints() {
        super(Context.everyTime, new ChoosePlayerEnergyToCopyRandom());
    }



    @Override
    public Player choose(Player player) {
        Card card = ChooseCardComparator.getInstance().chooseCardMaxCompare(player.getFacadeIA().getCardInHand(),
                new PrestigePointsCardComparator());
        return ChooseUtil.getInstance().getBestSimilaritiesPlayerEnergies(
                player.getFacadeIA().getOtherPLayers(), card);
    }
}
