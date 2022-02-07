package core.ia.strategy.choose.dice;

import core.dice.Dice;
import core.dice.DiceSet;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.IContext;

public class ChooseDiceFirst extends ChooseDice {

    public ChooseDiceFirst(IContext context, StrategyChooseDice nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseDiceFirst(StrategyChooseDice nextStrategy){
        super(null, nextStrategy);
    }
    public ChooseDiceFirst() {
        super();
    }

    @Override
    public Dice choose(Player player) {
        Dice[] diceSet = player.getFacadeIA().getDiceSet();
        return (Dice) ChooseFirst.getInstance().chooseFirst(diceSet);
    }
}
