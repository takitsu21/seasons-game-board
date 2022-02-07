package core.ia.strategy.choose.to_keep_drawn_card;

import core.cards.Card;
import core.ia.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ChooseToKeepDrawnCardPrefCardPointsTest {
    private ChooseToKeepDrawnCardPrefCardPoints chooseToKeepDrawnCardPrefCardPoints;
    private Player player;

    @BeforeEach
    void setup() {
        chooseToKeepDrawnCardPrefCardPoints = new ChooseToKeepDrawnCardPrefCardPoints();
        player = Mockito.mock(Player.class);
    }

    @Test
    void chooseTest() {
        Card card1 = Mockito.mock(Card.class);
        Card card2 = Mockito.mock(Card.class);
        Card card3 = Mockito.mock(Card.class);
        Card card4 = Mockito.mock(Card.class);
        when(card1.getPrestigePointValue()).thenReturn(10);
        when(card2.getPrestigePointValue()).thenReturn(1);
        when(card3.getPrestigePointValue()).thenReturn(30);
        when(card4.getPrestigePointValue()).thenReturn(0);


        assertTrue(chooseToKeepDrawnCardPrefCardPoints.choose(player, card1));
        assertTrue(chooseToKeepDrawnCardPrefCardPoints.choose(player, card2));
        assertTrue(chooseToKeepDrawnCardPrefCardPoints.choose(player, card3));
        assertFalse(chooseToKeepDrawnCardPrefCardPoints.choose(player, card4));
    }
}
