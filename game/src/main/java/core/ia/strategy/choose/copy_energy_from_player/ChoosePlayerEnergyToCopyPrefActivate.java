package core.ia.strategy.choose.copy_energy_from_player;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.ChooseUtil;

public class ChoosePlayerEnergyToCopyPrefActivate extends ChoosePlayerEnergyToCopy {
    public ChoosePlayerEnergyToCopyPrefActivate(IContext context, StrategyChoosePlayerEnergyToCopy nextStrategy) {
        super(context, nextStrategy);
    }

    public ChoosePlayerEnergyToCopyPrefActivate(StrategyChoosePlayerEnergyToCopy nextStrategy) {
        super(null, nextStrategy);
    }

    public ChoosePlayerEnergyToCopyPrefActivate() {
        super(Context.everyTime, new ChoosePlayerEnergyToCopyRandom());
    }

    @Override
    public Player choose(Player player) {
        Card card = ChooseUtil.getInstance().chooseCardPredictor(
                player.getFacadeIA().getCardInHand(),
                Card::isActivable);
        return ChooseUtil.getInstance().getBestSimilaritiesPlayerEnergies(
                player.getFacadeIA().getOtherPLayers(), card);
    }
}
