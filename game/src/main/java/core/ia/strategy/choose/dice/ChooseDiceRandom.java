package core.ia.strategy.choose.dice;

import core.dice.Dice;
import core.dice.DiceSet;
import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;

public class ChooseDiceRandom extends ChooseDice {

    public ChooseDiceRandom(IContext context, StrategyChooseDice nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseDiceRandom(StrategyChooseDice nextStraetgy){ super(null, nextStraetgy);}
    public ChooseDiceRandom() {
        super();
    }

    @Override
    public Dice choose(Player player) {
        player.setSatisfyingDice(false);
        Dice[] diceSet = player.getFacadeIA().getDiceSet();
        return (Dice) ChooseRandom.getInstance().chooseRandom(diceSet);
    }
}
