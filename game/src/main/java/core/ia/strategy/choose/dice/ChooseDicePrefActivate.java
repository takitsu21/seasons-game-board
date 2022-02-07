package core.ia.strategy.choose.dice;

import core.cards.Card;
import core.dice.Dice;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.ChooseUtil;

public class ChooseDicePrefActivate extends ChooseDice {
    public ChooseDicePrefActivate(IContext context, StrategyChooseDice nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseDicePrefActivate(StrategyChooseDice nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseDicePrefActivate() {
        super(Context.everyTime, new ChooseDiceRandom());
    }

    @Override
    public Dice choose(Player player) {
        Card card = ChooseUtil.getInstance().chooseCardPredictor(
                player.getFacadeIA().getCardInHand(),
                Card::isActivable);
        return ChooseUtil.getInstance().chooseDiceByEnergyForCards(player, card);
    }
}
