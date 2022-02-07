package core.ia.strategy.choose.dice;

import core.board.enums.Seasons;
import core.cards.comparator.AdvanceDiceComparator;
import core.dice.Dice;
import core.ia.Player;
import core.ia.strategy.choose.IContext;
import util.Util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

public class ChooseDiceTime extends ChooseDice {

    public ChooseDiceTime(IContext context, StrategyChooseDice nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseDiceTime() {
        super();
    }

    @Override
    public Dice choose(Player player) {
        Dice[] diceSet = player.getFacadeIA().getDiceSet();

        Dice dice;

        if(player.chooseGoToTheNextSeason()){
            dice = Arrays.stream(diceSet).max(new AdvanceDiceComparator())
                    .orElseThrow(NoSuchElementException::new); //on essaie partir de la season
        }else{
            dice = Arrays.stream(diceSet).min(new AdvanceDiceComparator())
                    .orElseThrow(NoSuchElementException::new); //on essaie de rester dans la season
        }

        return dice;
    }
}
