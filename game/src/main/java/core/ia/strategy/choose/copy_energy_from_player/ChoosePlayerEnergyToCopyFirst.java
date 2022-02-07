package core.ia.strategy.choose.copy_energy_from_player;

import core.ia.Player;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChoosePlayerEnergyToCopyFirst extends ChoosePlayerEnergyToCopy {

    public ChoosePlayerEnergyToCopyFirst(IContext context, StrategyChoosePlayerEnergyToCopy nextStrategy) {
        super(context, nextStrategy);
    }
    public ChoosePlayerEnergyToCopyFirst(StrategyChoosePlayerEnergyToCopy nextStrategy){ super(null, nextStrategy);}
    public ChoosePlayerEnergyToCopyFirst() {
        super();
    }

    @Override
    public Player choose(Player player) {
        List<Player> players = player.getFacadeIA().getOtherPLayers();
        return (Player) ChooseFirst.getInstance().chooseFirst(players);
    }
}
