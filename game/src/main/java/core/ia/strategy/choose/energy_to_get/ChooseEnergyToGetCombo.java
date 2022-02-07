package core.ia.strategy.choose.energy_to_get;

import core.board.enums.Energy;
import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseBestCombos;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseEnergyToGetCombo extends ChooseEnergyToGet{
    public ChooseEnergyToGetCombo(IContext context, StrategyChooseEnergyToGet nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseEnergyToGetCombo(StrategyChooseEnergyToGet nextStrategy){ super(null, nextStrategy);}

    public ChooseEnergyToGetCombo() {
        super(Context.everyTime, new ChooseEnergyToGetRandom());
    }

    @Override
    public Energy choose(Player player) {
        List<Card> cardInHand = player.getFacadeIA().getCardInHand();
        List<Card> cards = ChooseBestCombos.getInstance().getComboCards(cardInHand, cardInHand);
        return player.getFacadeIA().missingEnergy(cards);
    }
}
