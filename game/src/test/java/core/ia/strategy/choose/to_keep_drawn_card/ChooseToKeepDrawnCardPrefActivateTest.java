package core.ia.strategy.choose.to_keep_drawn_card;

import core.cards.Card;
import core.ia.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ChooseToKeepDrawnCardPrefActivateTest {
    private ChooseToKeepDrawnCardPrefActivate chooseToKeepDrawnCardPrefActivate;
    private Player player;

    @BeforeEach
    void setup() {
        chooseToKeepDrawnCardPrefActivate = new ChooseToKeepDrawnCardPrefActivate();
        player = Mockito.mock(Player.class);
    }

    @Test
    void chooseTest() {
        Card card1 = Mockito.mock(Card.class);
        Card card2 = Mockito.mock(Card.class);
        Card card3 = Mockito.mock(Card.class);
        Card card4 = Mockito.mock(Card.class);
        when(card1.isActivable()).thenReturn(true);
        when(card2.isActivable()).thenReturn(true);
        when(card3.isActivable()).thenReturn(true);
        when(card4.isActivable()).thenReturn(false);


        assertTrue(chooseToKeepDrawnCardPrefActivate.choose(player, card1));
        assertTrue(chooseToKeepDrawnCardPrefActivate.choose(player, card2));
        assertTrue(chooseToKeepDrawnCardPrefActivate.choose(player, card3));
        assertFalse(chooseToKeepDrawnCardPrefActivate.choose(player, card4));
    }
}
