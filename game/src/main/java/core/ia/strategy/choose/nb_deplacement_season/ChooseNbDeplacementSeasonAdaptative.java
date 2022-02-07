package core.ia.strategy.choose.nb_deplacement_season;

import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.IContext;

public class ChooseNbDeplacementSeasonAdaptative extends ChooseNbDeplacementSeason {
    Adaptative self;

    public ChooseNbDeplacementSeasonAdaptative(IContext context, StrategyChooseNbDeplacementSeason nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseNbDeplacementSeasonAdaptative(Adaptative player) {
        super();
        self = player;
    }

    @Override
    public int choose(Player player, int min, int max) {
        self.analyzeOpponentStrategy();
        int result = self.chooseNbDeplacementSeason(min, max);
        self.setStrategiesToAdaptative();
        return result;
    }
}