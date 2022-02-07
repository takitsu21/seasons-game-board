package core.ia.strategy.choose.nb_deplacement_season;

import core.ia.Player;
import core.ia.strategy.choose.IContext;

public class ChooseNbDeplacementSeasonTime extends ChooseNbDeplacementSeason {

    public ChooseNbDeplacementSeasonTime(IContext context, StrategyChooseNbDeplacementSeason nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseNbDeplacementSeasonTime() {
        super();
    }

    @Override
    public int choose(Player player, int min, int max) {
        int nbCaseByYears=player.getFacadeIA().getConfig().getNbCaseBySeason();
        if(player.chooseStayInTheSeason()){
            if((player.getFacadeIA().getCursor()+1)%nbCaseByYears == 0){
                return -1;
            }
            else {
                return 1;
            }
        }
        else {
            int i=(player.getFacadeIA().getCursor() % nbCaseByYears);
            if(player.chooseGoToTheNextSeason()){
                return max-i;
            }else if(player.chooseGoToThePreviousSeason()){
                return -(i+1);
            }
        }
        return 0;
    }
}
