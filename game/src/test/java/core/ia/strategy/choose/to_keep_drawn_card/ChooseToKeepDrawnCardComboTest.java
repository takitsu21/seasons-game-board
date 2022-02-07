package core.ia.strategy.choose.to_keep_drawn_card;

import core.board.FacadeIA;
import core.cards.Card;
import core.cards.effects.AmsugLongcoupEffect;
import core.cards.effects.BottesTemporelles;
import core.cards.effects.FireAmuletEffect;
import core.cards.effects.WaterAmuletEffect;
import core.ia.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ChooseToKeepDrawnCardComboTest {
    private ChooseToKeepDrawnCardCombo chooseToKeepDrawnCardCombo;
    private Player player;
    private FacadeIA facadeIA;
    private Card card1, card2, card3, card4;

    @BeforeEach
    void setup() {
        chooseToKeepDrawnCardCombo = new ChooseToKeepDrawnCardCombo();
        player = Mockito.mock(Player.class);
        facadeIA = Mockito.mock(FacadeIA.class);
        card1 = Mockito.mock(BottesTemporelles.class);
        card2 = Mockito.mock(AmsugLongcoupEffect.class);
        card3 = Mockito.mock(WaterAmuletEffect.class);
        card4 = Mockito.mock(FireAmuletEffect.class);
        when(player.getFacadeIA()).thenReturn(facadeIA);
        when(facadeIA.getCardInHand()).thenReturn(new ArrayList<>(List.of(card1, card2)));
    }

    @Test
    void chooseTest() {
        assertTrue(chooseToKeepDrawnCardCombo.choose(player, card1));
        assertTrue(chooseToKeepDrawnCardCombo.choose(player, card2));
        assertFalse(chooseToKeepDrawnCardCombo.choose(player, card3));
        assertFalse(chooseToKeepDrawnCardCombo.choose(player, card4));
    }
}
