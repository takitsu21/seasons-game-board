package core.game;

import core.ia.Player;
import core.ia.PlayerStats;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class TurnBasedGameLoopTest {
    GameController controller;
    private Config config;
    private TurnBasedGameLoop gameLoop;

    @BeforeEach
    void setUp() {
        config = Mockito.mock(Config.class);

        when(config.getStats()).thenReturn(0);
        when(config.getNbGame()).thenReturn(1);
        when(config.getNbPlayer()).thenReturn(4);
        when(config.getPrint()).thenReturn(0);
        when(config.getNbCaseBySeason()).thenReturn(3);
        when(config.getNbCardsPerYear()).thenReturn(3);
        when(config.getNbYears()).thenReturn(3);
        when(config.getNbCaseBySeason()).thenReturn(3);

        Player[] players = App.initPlayer(4, config);

        gameLoop = new TurnBasedGameLoop(players, config);
        controller = gameLoop.getController();
        controller.initGame(players, config);
        controller.getBoard().initPLayersInventory();
    }


//    @Test
//    void runTest() {
//        boolean hasFinishedWell;
//        try {
//            int status = SystemLambda.catchSystemExit(() -> {
//                gameLoop.run();
//            });
//            assertEquals(0, status);
//            hasFinishedWell = true;
//        } catch (Exception e) {
//            hasFinishedWell = false;
//        }
//
//        assertTrue(hasFinishedWell);
//    }

    @Test
    void sendStatsToServerTest() {
        gameLoop.sendStatsToServer(new ArrayList<>());
        for (Player p : controller.getBoard().getPlayers()) {
            PlayerStats ps = controller.getBoard().getPlayersStats().get(p);
            assertEquals(0, ps.getPenaltyCard());
            assertEquals(0, ps.getPenaltyBonusStats());
            assertEquals(0, ps.getPointsCristal());
            assertEquals(0, ps.getPointsCard());
            assertEquals(0, ps.getCardDrawn());
            assertEquals(0, ps.getCardInvoked());
            assertEquals(0, ps.getCardActivated());
            assertEquals(0, ps.getFinalInvocationGauge());
            assertEquals(0, ps.getFinalCardGameInGame());
            assertEquals(0, ps.getCristalsGainedByCristalization());
            assertEquals(0, ps.getPrestigePoints());
        }
    }

//    @Test
//    void processOneGameLoopTest() {
//        boolean hasFinishedWell;
//        try {
//            gameLoop.processOneGameLoop();
//            hasFinishedWell = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            hasFinishedWell = false;
//        }
//
//        assertTrue(hasFinishedWell);
//    }

//    @Test
//    void processAllGameLoopTest() {
//        boolean hasFinishedWell;
//        try {
//            int status = SystemLambda.catchSystemExit(() -> {
//                gameLoop.processAllGameLoop();
//            });
//            hasFinishedWell = true;
//            assertEquals(0, status);
//        } catch (Exception e) {
//            hasFinishedWell = false;
//        }
//
//        assertTrue(hasFinishedWell);
//    }
}
