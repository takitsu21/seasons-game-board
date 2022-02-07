package core.ia.strategy.choose.dice;

import core.cards.Card;
import core.cards.comparator.PrestigePointsCardComparator;
import core.dice.Dice;
import core.ia.Player;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.ChooseUtil;

public class ChooseDicePrefCardPoints extends ChooseDice {
    public ChooseDicePrefCardPoints(IContext context, StrategyChooseDice nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseDicePrefCardPoints(StrategyChooseDice nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseDicePrefCardPoints() {
        super(Context.everyTime, new ChooseDiceRandom());
    }

    @Override
    public Dice choose(Player player) {
        Card card = ChooseCardComparator.getInstance().chooseCardMaxCompare(player.getFacadeIA().getCardInHand(),
                new PrestigePointsCardComparator());
        return ChooseUtil.getInstance().chooseDiceByEnergyForCards(player, card);
    }
}
