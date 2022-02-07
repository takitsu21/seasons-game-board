package core.ia.strategy.choose.dice;

import core.dice.Dice;
import core.dice.DiceSet;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import util.Util;

public class ChooseDicePrefCrystallize extends ChooseDice {

    public ChooseDicePrefCrystallize(IContext context, StrategyChooseDice nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseDicePrefCrystallize(StrategyChooseDice nextStrategy){ super(null, nextStrategy);}
    public ChooseDicePrefCrystallize() {
        super(Context.everyTime, new ChooseDiceRandom());
    }

    @Override
    public Dice choose(Player player) {
        Dice[] diceSet = player.getFacadeIA().getDiceSet();
        for (int i = 0; i < diceSet.length; i++) {
            Dice dice = diceSet[i];
            if (diceSet[i].getCurrentFace().isCrystalize()) {
                return dice;
            }
        }
        return null;
    }
}
