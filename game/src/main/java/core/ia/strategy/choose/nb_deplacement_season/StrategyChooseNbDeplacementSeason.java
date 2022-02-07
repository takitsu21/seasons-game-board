package core.ia.strategy.choose.nb_deplacement_season;

import core.ia.Player;

public interface StrategyChooseNbDeplacementSeason {

    int doChoose(Player player, int min, int max);

    int choose(Player player, int min, int max);

}
