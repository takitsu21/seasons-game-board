package core.ia.strategy.choose.similar_energy_to_delete;

import core.board.enums.Energy;
import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.ChooseUtil;

import java.util.List;

public class ChooseSimilarEnergyToDeletePrefActivate extends ChooseSimilarEnergyToDelete {
    public ChooseSimilarEnergyToDeletePrefActivate(IContext context, StrategyChooseSimilarEnergyToDelete nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseSimilarEnergyToDeletePrefActivate(StrategyChooseSimilarEnergyToDelete nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseSimilarEnergyToDeletePrefActivate() {
        super(Context.everyTime, new ChooseSimilarEnergiesToDeleteRandom());
    }

    @Override
    public List<Energy> choose(Player player, int nb) {
        List<Card> cards = ChooseUtil.getInstance().chooseCardsPredictor(
                player.getFacadeIA().getCardInHand(), Card::isActivable);
        return player.getFacadeIA().uselessSimilarEnergy(cards, nb);
    }
}
