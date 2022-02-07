package core.dice;

import core.board.Board;
import core.board.Year;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class DiceSetTest {
    private DiceSet d1;
    private DiceSet d2;
    private Board board;
    private Config config;


    @BeforeEach
    void setUp() {
        config = new Config();
        board = new Board(config);
        board.initDiceSet();
        d1 = new DiceSet(4, new Year(config.getNbYears()));
        d2 = new DiceSet(2, new Year(config.getNbYears()));
    }

    @Test
    void diceSetSize() {
        assertEquals(5, d1.getSetOfDices().length);
        assertEquals(3, d2.getSetOfDices().length);
        assertFalse(d1.getSetOfDices().length > 5);
        assertFalse(d2.getSetOfDices().length < 2);
    }

    @Test
    void initDiceSetdefault() {
        board.initDiceSet();
        assertEquals(4, config.getNbPlayer());
        assertEquals(5, board.getDices().getSetOfDices().length);
    }

    @Test
    void removeDiceSet() {
        assertEquals(5, board.getDices().getSetOfDices().length);
        board.getDices().removeDice(board.getDices().getDice(0));
        assertEquals(4, board.getDices().getSetOfDices().length);
    }
}
