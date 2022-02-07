package core.ia.strategy.choose.dice;

import core.cards.Card;
import core.cards.comparator.PrestigePointsCardComparator;
import core.dice.Dice;
import core.ia.Player;
import core.ia.strategy.choose.ChooseBestCombos;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.ChooseUtil;

public class ChooseDiceCombos extends ChooseDice{
    public ChooseDiceCombos(IContext context, StrategyChooseDice nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseDiceCombos(StrategyChooseDice nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseDiceCombos() {
        super(Context.everyTime, new ChooseDiceRandom());
    }

    @Override
    public Dice choose(Player player) {
        Card card = ChooseBestCombos.getInstance().chooseCardForCombos(
                player.getFacadeIA().getCardInHand()
        );
        return ChooseUtil.getInstance().chooseDiceByEnergyForCards(player, card);
    }
}
