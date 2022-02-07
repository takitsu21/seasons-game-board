package core.dice;

import core.board.enums.Energy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class FaceTest {
    private final int NBFACE = 6;

    @Mock
    private Dice d;
    private Face f1;
    private Face f2;
    private Face f3;
    private Face f4;

    @BeforeEach
    void setUp() {
        d = Mockito.mock(Dice.class);
        f1 = Mockito.mock(Face.class);
        f2 = Mockito.mock(Face.class);
        f3 = Mockito.mock(Face.class);

        when(d.getCurrentFace()).thenReturn(new Face(0, false, false, true, 1, new Energy[]{Energy.WATER}));
        when(f1.getNbAdvance()).thenReturn(3);
        when(f2.getNbAdvance()).thenReturn(2);
        when(f3.getNbAdvance()).thenReturn(1);


        f4 = d.getCurrentFace();
    }

    @Test
    void diceSetSize() {
        assertEquals(3, f1.getNbAdvance());
        assertEquals(2, f2.getNbAdvance());
        assertEquals(1, f3.getNbAdvance());
        assertTrue(f1.getNbAdvance() <= 3);
        assertTrue(f2.getNbAdvance() > 0 && f2.getNbAdvance() < 4);
        assertFalse(f3.getNbAdvance() > 3);
    }


    @Test
    void diceFaces() {
        assertEquals(f4.toString(), new Face(0, false, false, true, 1, new Energy[]{Energy.WATER}).toString());
    }


}
