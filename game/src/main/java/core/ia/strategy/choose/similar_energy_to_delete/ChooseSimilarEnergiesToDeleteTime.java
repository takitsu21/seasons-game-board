package core.ia.strategy.choose.similar_energy_to_delete;

import core.board.enums.Energy;
import core.cards.Card;
import core.cards.comparator.TimeComparator;
import core.ia.Player;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.IContext;

import java.util.ArrayList;
import java.util.List;

public class ChooseSimilarEnergiesToDeleteTime extends ChooseSimilarEnergyToDelete {

    public ChooseSimilarEnergiesToDeleteTime(IContext context, StrategyChooseSimilarEnergyToDelete nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseSimilarEnergiesToDeleteTime(StrategyChooseSimilarEnergyToDelete nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseSimilarEnergiesToDeleteTime() {
        super();
    }

    @Override
    public List<Energy> choose(Player player, int nb) {
        List<Card> cardInHand = new ArrayList<>(player.getFacadeIA().getCardInHand());
        cardInHand = ChooseCardComparator.getInstance().sortCardCompare(cardInHand, new TimeComparator());
        return player.getFacadeIA().uselessSimilarEnergy(cardInHand, nb);
    }
}
