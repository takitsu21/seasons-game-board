package core.ia.strategy.choose.nb_deplacement_season;

import core.ia.Player;
import core.ia.strategy.choose.IContext;

public class ChooseNbDeplacementSeasonFirst extends ChooseNbDeplacementSeason {

    public ChooseNbDeplacementSeasonFirst(IContext context, StrategyChooseNbDeplacementSeason nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseNbDeplacementSeasonFirst(StrategyChooseNbDeplacementSeason nextStrategy){ super(null, nextStrategy);}
    public ChooseNbDeplacementSeasonFirst() {
        super();
    }

    @Override
    public int choose(Player player, int min, int max) {
        return 1;
    }
}
