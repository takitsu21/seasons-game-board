package core.ia.strategy.choose.cards_to_activate;

import core.board.FacadeIA;
import core.cards.Card;
import core.cards.effects.*;
import core.ia.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ChooseCardsToActivateMalusTest {
    private ChooseCardsToActivateMalus chooseCardsToActivateMalus;
    private Player player;
    private FacadeIA facade;

    @BeforeEach
    void setup() {
        chooseCardsToActivateMalus = new ChooseCardsToActivateMalus();
        player = Mockito.mock(Player.class);
        facade = Mockito.mock(FacadeIA.class);
        when(player.getFacadeIA()).thenReturn(facade);
    }

    @Test
    void chooseWithKairn() {
        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                Mockito.mock(BottesTemporelles.class),
                Mockito.mock(HeaumeRagfieldEffect.class),
                Mockito.mock(BalanceIshtarEffect.class),
                Mockito.mock(KairnDestructeurEffect.class),
                Mockito.mock(NariaLaProphetesseEffect.class),
                Mockito.mock(EarthAmuletEffect.class)));
        when(facade.getActivableCard()).thenReturn(cardsToChooseFrom);
        assertEquals(KairnDestructeurEffect.class, chooseCardsToActivateMalus.choose(player).getClass());
    }

    @Test
    void chooseWithoutKairn() {
        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                Mockito.mock(BottesTemporelles.class),
                Mockito.mock(HeaumeRagfieldEffect.class),
                Mockito.mock(BalanceIshtarEffect.class),
                Mockito.mock(NariaLaProphetesseEffect.class),
                Mockito.mock(EarthAmuletEffect.class)));
        when(facade.getActivableCard()).thenReturn(cardsToChooseFrom);
        assertNull(chooseCardsToActivateMalus.choose(player));
    }

    @Test
    void chooseEmptyHand() {
        when(facade.getActivableCard()).thenReturn(new ArrayList<>());
        assertNull(chooseCardsToActivateMalus.choose(player));
    }
}