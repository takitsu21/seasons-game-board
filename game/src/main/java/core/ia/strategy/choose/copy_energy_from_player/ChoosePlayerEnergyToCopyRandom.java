package core.ia.strategy.choose.copy_energy_from_player;

import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChoosePlayerEnergyToCopyRandom extends ChoosePlayerEnergyToCopy {

    public ChoosePlayerEnergyToCopyRandom(IContext context, StrategyChoosePlayerEnergyToCopy nextStrategy) {
        super(context, nextStrategy);
    }
    public ChoosePlayerEnergyToCopyRandom(StrategyChoosePlayerEnergyToCopy nextStrategy){ super(null, nextStrategy);}
    public ChoosePlayerEnergyToCopyRandom() {
        super();
    }

    @Override
    public Player choose(Player player) {
        List<Player> players = player.getFacadeIA().getOtherPLayers();
        return (Player) ChooseRandom.getInstance().chooseRandom(players);
    }
}
