package core.util;

import core.game.App;
import core.game.GameController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfigTest {
    private GameController controller;
    private Config config;


    @BeforeEach
    void setUp() {
        config = new Config();
        controller = new GameController();
        controller.initGame(App.initPlayer(2, config), config);
    }

    @Test
    void nbDice3() {
        controller.getBoard().initDiceSet();
        assertEquals(config.getNbPlayer() + 1, controller.getBoard().getDices().getSetOfDices().length);
    }

    @Test
    void nbDice5() {
        controller.getBoard().initDiceSet();
        assertEquals(config.getNbPlayer() + 1, controller.getBoard().getDices().getSetOfDices().length);
    }

    @Test
    void initCursor() {
        assertEquals(0, controller.getBoard().getCurrentCursor());
    }

    @Test
    void getStatsTest() {
        assertEquals(0, config.getStats());
    }

    @Test
    void getArgumentTest() {
        assertEquals(new ArrayList<>(), config.getArguments());
    }

    @Test
    void getPrintTest() {
        assertEquals(1, config.getPrint());
    }

    @Test
    void getNbGameTest() {
        assertEquals(1, config.getNbGame());
    }
}
