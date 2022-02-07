package core.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RendererTest {
    private Config config;
    private core.util.Renderer renderer;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private PrintStream originalOut = System.out;
    private PrintStream originalErr = System.err;

    @BeforeEach
    void setUp() {
        config = new Config();
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void errorInitRenderer() {
        assertThrows(IllegalStateException.class, () -> {
            renderer = new Renderer();
        });
    }

    @Test
    void clearTest() {
        Renderer.clear();
        Renderer.add("Bonjour monsieur");
        Renderer.add("Bonjour madame");
        Renderer.clear();
        assertEquals(new StringBuilder().toString(), Renderer.getRender().toString());
    }

    @Test
    void getRendererTest() {
        Renderer.clear();
        assertEquals(new StringBuilder().toString(), Renderer.getRender().toString());
        Renderer.add("Bonjour");
        StringBuilder rendererTest = new StringBuilder();
        rendererTest.append("Bonjour" + System.lineSeparator());
        assertEquals(rendererTest.toString(), Renderer.getRender().toString());
    }

    @Test
    void dontPrintTheGamesTest() {
        Renderer.clear();
        Renderer.add("Bonjour");
        String expResult = System.lineSeparator() +
                System.lineSeparator() +
                "1 / 1 partie(s) de Seasons ont ete jouees."
                + System.lineSeparator()
                + System.lineSeparator()
                + System.lineSeparator();
        Renderer.print(0, 1, 1);
        assertEquals(expResult, outContent.toString());

    }

    @Test
    void printTheGameTest() {
        Renderer.clear();
        Renderer.add("Bonjour");
        String expResult =
                "Bonjour" +
                        System.lineSeparator() +
                        System.lineSeparator() + System.lineSeparator() + System.lineSeparator() +
                        "1 / 1 partie(s) de Seasons ont ete jouees." +
                        System.lineSeparator()
                        + System.lineSeparator()
                        + System.lineSeparator();
        Renderer.print(1, 1, 1);
        assertEquals(expResult, outContent.toString());
    }

}
