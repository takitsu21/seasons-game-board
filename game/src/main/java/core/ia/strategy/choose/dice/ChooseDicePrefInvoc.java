package core.ia.strategy.choose.dice;

import core.cards.Card;
import core.cards.comparator.CostCardComparator;
import core.cards.comparator.PrestigePointsCardComparator;
import core.dice.Dice;
import core.ia.Player;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.ChooseUtil;

public class ChooseDicePrefInvoc extends ChooseDice {

    public ChooseDicePrefInvoc(IContext context, StrategyChooseDice nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseDicePrefInvoc(StrategyChooseDice nextStrategy){ super(null, nextStrategy);}
    public ChooseDicePrefInvoc() {
        super(Context.everyTime, new ChooseDiceRandom());
    }

    @Override
    public Dice choose(Player player) {
        Dice[] diceSet = player.getFacadeIA().getDiceSet();
        for (Dice dice : diceSet) {
            if (dice.getCurrentFace().isInvocationSup()) {
                return dice;
            }
        }
        Card card = ChooseCardComparator.getInstance().chooseCardMinCompare(player.getFacadeIA().getCardInHand(),
                new CostCardComparator());
        return ChooseUtil.getInstance().chooseDiceByEnergyForCards(player, card);
    }
}
