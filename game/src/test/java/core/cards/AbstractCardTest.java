package core.cards;

import core.board.Board;
import core.board.enums.Energy;
import core.ia.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractCardTest {
    private AbstractCard card;

    @BeforeEach
    void setUp() {
        card = new AbstractCard(1,
                false,
                2,
                List.of(Energy.WATER),
                EffectFrequency.ON_PLAY,
                "carte abstraite",
                10) {
            @Override
            public void use(Board board, Player player) {

            }
        };
    }

    @Test
    void isPlayersTest() {
        assertFalse(card.isPlayers());
    }

    @Test
    void isPlayersSetTest() {
        card.setPlayers(true);
        assertTrue(card.isPlayers());
    }

    @Test
    void IdTest() {
        card.setId(2);
        assertEquals(2, card.getId());
    }

    @Test
    void hasPermanentEffect() {
        assertFalse(card.hasPermanentEffect());
    }

    @Test
    void toStringTest() {
        assertTrue(card.toString().startsWith("AbstractCard"));
    }
}
