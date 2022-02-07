package core.game;

import core.util.Config;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class AppTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private PrintStream originalOut = System.out;
    private PrintStream originalErr = System.err;


    @Mock
    private Config config;

    @BeforeEach
    void setUp() {
        config = Mockito.mock(Config.class);

        App.setConfig(config);

        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        errContent = new ByteArrayOutputStream();
        originalErr = System.err;
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }


    @Test
    void checkConfigTest() {
        when(config.getNbPlayer()).thenReturn(3);
        when(config.getNbYears()).thenReturn(4);
        when(config.getNbCardsPerYear()).thenReturn(3);
        when(config.getNbGame()).thenReturn(1);
        when(config.getStats()).thenReturn(1);
        when(config.getPrint()).thenReturn(0);

        App.setConfig(config);
        boolean check = App.checkConfiguration();

        assertTrue(check);
    }

    @Test
    void checkConfigDefaultTest() {
        config = new Config();
        App.setConfig(config);
        boolean check = App.checkConfiguration();

        assertTrue(check);
        assertEquals(3, config.getNbYears());
        assertEquals(4, config.getNbPlayer());
        assertEquals(1, config.getNbGame());
        assertEquals(3, config.getNbCardsPerYear());
        assertEquals(0, config.getStats());

    }

    @Test
    void checkConfigBadNbPlayer() {
        App.setConfig(config);

        when(config.getNbYears()).thenReturn(4);
        when(config.getNbCardsPerYear()).thenReturn(3);
        when(config.getNbGame()).thenReturn(1);
        when(config.getStats()).thenReturn(1);
        when(config.getPrint()).thenReturn(0);

        when(config.getNbPlayer()).thenReturn(1);
        boolean check = App.checkConfiguration();
        assertFalse(check);

        when(config.getNbPlayer()).thenReturn(5);
        check = App.checkConfiguration();
        assertFalse(check);
    }

    @Test
    void checkConfigBadNbYear() {
        App.setConfig(config);
        when(config.getNbPlayer()).thenReturn(2);
        when(config.getNbCardsPerYear()).thenReturn(3);
        when(config.getNbGame()).thenReturn(1);
        when(config.getStats()).thenReturn(1);
        when(config.getPrint()).thenReturn(0);

        when(config.getNbYears()).thenReturn(0);
        boolean check = App.checkConfiguration();
        assertFalse(check);

    }

    @Test
    void checkConfigBadNbCardPerYear() {
        App.setConfig(config);
        when(config.getNbPlayer()).thenReturn(2);
        when(config.getNbYears()).thenReturn(3);
        when(config.getNbGame()).thenReturn(1);
        when(config.getStats()).thenReturn(1);
        when(config.getPrint()).thenReturn(0);

        when(config.getNbCardsPerYear()).thenReturn(0);
        boolean check = App.checkConfiguration();
        assertFalse(check);
    }

    @Test
    void checkConfigBadNbGame() {
        App.setConfig(config);
        when(config.getNbPlayer()).thenReturn(2);
        when(config.getNbYears()).thenReturn(0);
        when(config.getNbCardsPerYear()).thenReturn(3);
        when(config.getStats()).thenReturn(1);
        when(config.getPrint()).thenReturn(0);

        when(config.getNbGame()).thenReturn(0);
        boolean check = App.checkConfiguration();
        assertFalse(check);
    }

    @Test
    void checkConfigGetStat() {
        App.setConfig(config);
        when(config.getNbPlayer()).thenReturn(3);
        when(config.getNbYears()).thenReturn(3);
        when(config.getNbCardsPerYear()).thenReturn(3);
        when(config.getNbGame()).thenReturn(1);
        when(config.getPrint()).thenReturn(0);

        when(config.getStats()).thenReturn(0);
        boolean check1 = App.checkConfiguration();
        assertTrue(check1);

        when(config.getStats()).thenReturn(2);
        boolean check2 = App.checkConfiguration();
        assertFalse(check2);
    }

    @Test
    void checkConfigGetPrint() {
        App.setConfig(config);
        when(config.getNbPlayer()).thenReturn(3);
        when(config.getNbYears()).thenReturn(3);
        when(config.getNbCardsPerYear()).thenReturn(3);
        when(config.getNbGame()).thenReturn(1);
        when(config.getStats()).thenReturn(0);


        when(config.getPrint()).thenReturn(1);
        boolean check1 = App.checkConfiguration();
        assertTrue(check1);

        when(config.getPrint()).thenReturn(2);
        boolean check2 = App.checkConfiguration();
        assertFalse(check2);
    }


}
