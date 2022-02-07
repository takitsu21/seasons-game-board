package core.ia.strategy.choose.copy_energy_from_player;

import core.cards.Card;
import core.cards.comparator.CostCardComparator;
import core.cards.comparator.PrefCardListComparator;
import core.ia.Player;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;
import core.util.ChooseUtil;

public class ChoosePlayerEnergyToCopyTime extends ChoosePlayerEnergyToCopy {
    public ChoosePlayerEnergyToCopyTime(IContext context, StrategyChoosePlayerEnergyToCopy nextStrategy) {
        super(context, nextStrategy);
    }

    public ChoosePlayerEnergyToCopyTime(StrategyChoosePlayerEnergyToCopy nextStrategy){ super(null, nextStrategy);}


    public ChoosePlayerEnergyToCopyTime() {
        super(Context.everyTime, new ChoosePlayerEnergyToCopyRandom());
    }

    @Override
    public Player choose(Player player) {
        Card card = ChooseCardComparator.getInstance().chooseCardMaxCompare(player.getFacadeIA().getCardInHand(),
                new PrefCardListComparator(CardUtil.getCardsPlayedWithTime()));
        return ChooseUtil.getInstance().getBestSimilaritiesPlayerEnergies(
                player.getFacadeIA().getOtherPLayers(), card);
    }
}
