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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class ChooseCardsToActivatePrefInvocTest {
    private FacadeIA facadeIA;
    private ChooseCardsToActivatePrefInvoc chooseCardsToActivate;
    private Player player;


    @BeforeEach
    void setUp() {
        chooseCardsToActivate = new ChooseCardsToActivatePrefInvoc();

        player = Mockito.mock(Player.class);
        facadeIA = Mockito.mock(FacadeIA.class);
        when(player.getFacadeIA()).thenReturn(facadeIA);
    }

    @Test
    void chooseWithCalideDivin() {
        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                Mockito.mock(BottesTemporelles.class),
                Mockito.mock(HeaumeRagfieldEffect.class),
                Mockito.mock(BalanceIshtarEffect.class),
                Mockito.mock(KairnDestructeurEffect.class),
                Mockito.mock(NariaLaProphetesseEffect.class),
                Mockito.mock(CaliceDivinEffect.class)));
        when(facadeIA.getActivableCard()).thenReturn(cardsToChooseFrom);
        assertEquals(CaliceDivinEffect.class, chooseCardsToActivate.choose(player).getClass());
    }

    @Test
    void chooseWithNothing() {
        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                Mockito.mock(BottesTemporelles.class),
                Mockito.mock(HeaumeRagfieldEffect.class),
                Mockito.mock(BalanceIshtarEffect.class),
                Mockito.mock(KairnDestructeurEffect.class),
                Mockito.mock(NariaLaProphetesseEffect.class),
                Mockito.mock(PotionVieEffect.class)));
        when(facadeIA.getActivableCard()).thenReturn(cardsToChooseFrom);
        assertNull(chooseCardsToActivate.choose(player));
    }
}
