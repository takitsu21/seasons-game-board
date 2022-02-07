package core.ia.strategy.choose.cards_to_activate;

import core.board.FacadeIA;
import core.cards.Card;
import core.cards.effects.*;
import core.ia.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class ChooseCardsToActivatePrefCrystallizeTest {
    private ChooseCardsToActivatePrefCrystallize chooseCardsToActivatePrefCrystallize;
    private Player player;
    private FacadeIA facade;

    @BeforeEach
    void setup() {
        chooseCardsToActivatePrefCrystallize = new ChooseCardsToActivatePrefCrystallize();
        player = Mockito.mock(Player.class);
        facade = Mockito.mock(FacadeIA.class);
        when(player.getFacadeIA()).thenReturn(facade);
    }

    @Test
    void chooseWithOneActivableCrystalCard() {
        Card DeMalice = Mockito.mock(DeDeLaMaliceEffect.class);
        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                Mockito.mock(BottesTemporelles.class),
                Mockito.mock(HeaumeRagfieldEffect.class),
                Mockito.mock(DeDeLaMaliceEffect.class),
                Mockito.mock(KairnDestructeurEffect.class),
                Mockito.mock(NariaLaProphetesseEffect.class),
                Mockito.mock(EarthAmuletEffect.class)));
        when(facade.getActivableCard()).thenReturn(cardsToChooseFrom);
        assertEquals(DeMalice.getClass(), chooseCardsToActivatePrefCrystallize.choose(player).getClass());
    }

    @Test
    void chooseWithoutActivableCrystalCard() {
        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                Mockito.mock(BottesTemporelles.class),
                Mockito.mock(HeaumeRagfieldEffect.class),
                Mockito.mock(KairnDestructeurEffect.class),
                Mockito.mock(NariaLaProphetesseEffect.class),
                Mockito.mock(EarthAmuletEffect.class)));
        when(facade.getActivableCard()).thenReturn(cardsToChooseFrom);
        assertNull(chooseCardsToActivatePrefCrystallize.choose(player));
    }

    @Test
    void chooseEmptyHand() {
        when(facade.getActivableCard()).thenReturn(new ArrayList<>());
        assertNull(chooseCardsToActivatePrefCrystallize.choose(player));
    }
}
