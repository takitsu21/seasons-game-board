package core.ia.strategy.choose.copy_energy_from_player;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseBestCombos;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.ChooseUtil;

public class ChoosePlayerEnergyToCopyCombos extends ChoosePlayerEnergyToCopy {
    public ChoosePlayerEnergyToCopyCombos(IContext context, StrategyChoosePlayerEnergyToCopy nextStrategy) {
        super(context, nextStrategy);
    }

    public ChoosePlayerEnergyToCopyCombos(StrategyChoosePlayerEnergyToCopy nextStrategy){ super(null, nextStrategy);}

    public ChoosePlayerEnergyToCopyCombos() {
        super(Context.everyTime, new ChoosePlayerEnergyToCopyRandom());
    }

    @Override
    public Player choose(Player player) {
        Card mostCardCombos = ChooseBestCombos.getInstance().chooseCardForCombosMaxEnergiesToCopy(
                player.getFacadeIA().getCardInHand(),
                player.getFacadeIA().getCardOnBoard()
        );
        return mostCardCombos==null? null: ChooseUtil.getInstance().getBestSimilaritiesPlayerEnergies(
                player.getFacadeIA().getOtherPLayers(), mostCardCombos);
    }
}
