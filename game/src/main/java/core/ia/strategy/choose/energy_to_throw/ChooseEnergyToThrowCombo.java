package core.ia.strategy.choose.energy_to_throw;

import core.board.enums.Energy;
import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseBestCombos;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseEnergyToThrowCombo extends ChooseEnergyToThrow {
    public ChooseEnergyToThrowCombo(IContext context, StrategyChooseEnergyToThrow nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseEnergyToThrowCombo(StrategyChooseEnergyToThrow nextStrategy){ super(null, nextStrategy);}

    public ChooseEnergyToThrowCombo() {
        super(Context.everyTime, new ChooseEnergyToThrowRandom());
    }

    @Override
    public Energy choose(Player player) {
        List<Card> cards = ChooseBestCombos.getInstance().getComboCards(
                player.getFacadeIA().getCardInHand(),
                player.getFacadeIA().getCardInHand()
        );


        Energy energy = player.getFacadeIA().uselessEnergy(cards);
        if (energy == null) {
            energy = getEnergyToThrow(player, cards);
        }
        return energy;
    }
}
