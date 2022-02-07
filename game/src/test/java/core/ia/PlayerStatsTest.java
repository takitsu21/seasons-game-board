package core.ia;

import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stats.Stats;

import static org.junit.jupiter.api.Assertions.*;

class PlayerStatsTest {
    private PlayerStats ps;
    private Player p1;

    @BeforeEach
    void setUp() {
        Config config = new Config();
        PlayerFactory playerFactory = new PlayerFactory(config);
        p1 = playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA1");
        ps = new PlayerStats(p1);
    }

    @Test
    void statsTest() {
        Stats exportedStat = ps.exportStats();
        assertInstanceOf(Stats.class, exportedStat);
    }

    @Test
    void isWinnerTest() {
        assertFalse(ps.isWinner());
    }

    @Test
    void setWinnerTest() {
        ps.setWinner(true);
        assertTrue(ps.isWinner());
    }

    @Test
    void setPointsCrystalTest() {
        ps.setPointsCrystal(10);
        assertEquals(10, ps.getPointsCristal());
    }

    @Test
    void setCardDrawnTest() {
        ps.setCardDrawn(5);
        assertEquals(5, ps.getCardDrawn());
    }

    @Test
    void setFinalInvocationGauge() {
        ps.setFinalInvocationGauge(7);
        assertEquals(7, ps.getFinalInvocationGauge());
    }

    @Test
    void toStringTest() {
        assertTrue(ps.toString().startsWith("PlayerStats"));
    }

}
