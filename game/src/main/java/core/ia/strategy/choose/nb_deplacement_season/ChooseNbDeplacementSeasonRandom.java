package core.ia.strategy.choose.nb_deplacement_season;

import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;

public class ChooseNbDeplacementSeasonRandom extends ChooseNbDeplacementSeason {

    public ChooseNbDeplacementSeasonRandom(IContext context, StrategyChooseNbDeplacementSeason nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseNbDeplacementSeasonRandom(StrategyChooseNbDeplacementSeason nextStrategy){ super(null, nextStrategy);}
    public ChooseNbDeplacementSeasonRandom() {
        super();
    }

    @Override
    public int choose(Player player, int min, int max) {
        int deplacement;
        do {
            deplacement = ChooseRandom.getInstance().chooseRandom(min, max);
        } while (deplacement == 0);
        return deplacement;
    }
}
