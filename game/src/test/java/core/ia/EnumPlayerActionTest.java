package core.ia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnumPlayerActionTest {

    @BeforeEach
    void setup() {
    }

    @Test
    void getITest() {
        EnumPlayerAction actNothing = EnumPlayerAction.NOTHING;
        assertEquals(0, actNothing.ordinal());
        EnumPlayerAction actCryst = EnumPlayerAction.CRYSTALLISE;
        assertEquals(1, actCryst.ordinal());
        EnumPlayerAction actSum = EnumPlayerAction.SUMMON;
        assertEquals(2, actSum.ordinal());
        EnumPlayerAction actBonus = EnumPlayerAction.BONUS;
        assertEquals(3, actBonus.ordinal());
        EnumPlayerAction actActivate = EnumPlayerAction.ACTIVATE_CARD;
        assertEquals(4, actActivate.ordinal());
    }

    @Test
    void valuesTest() {
        assertEquals(EnumPlayerAction.NOTHING, EnumPlayerAction.values()[0]);
        assertEquals(EnumPlayerAction.CRYSTALLISE, EnumPlayerAction.values()[1]);
        assertEquals(EnumPlayerAction.SUMMON, EnumPlayerAction.values()[2]);
        assertEquals(EnumPlayerAction.BONUS, EnumPlayerAction.values()[3]);
        assertEquals(EnumPlayerAction.ACTIVATE_CARD, EnumPlayerAction.values()[4]);
    }

    @Test
    void valueOfTest() {
        assertEquals(EnumPlayerAction.NOTHING, EnumPlayerAction.valueOf("NOTHING"));
        assertEquals(EnumPlayerAction.CRYSTALLISE, EnumPlayerAction.valueOf("CRYSTALLISE"));
        assertEquals(EnumPlayerAction.SUMMON, EnumPlayerAction.valueOf("SUMMON"));
        assertEquals(EnumPlayerAction.BONUS, EnumPlayerAction.valueOf("BONUS"));
        assertEquals(EnumPlayerAction.ACTIVATE_CARD, EnumPlayerAction.valueOf("ACTIVATE_CARD"));
    }
}