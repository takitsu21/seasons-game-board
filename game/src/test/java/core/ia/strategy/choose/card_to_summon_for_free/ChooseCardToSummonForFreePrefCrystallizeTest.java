package core.ia.strategy.choose.card_to_summon_for_free;

import core.board.FacadeIA;
import core.cards.Card;
import core.cards.effects.AmsugLongcoupEffect;
import core.cards.effects.BottesTemporelles;
import core.cards.effects.CaliceDivinEffect;
import core.cards.effects.CoffretMerveilleuxEffect;
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

public class ChooseCardToSummonForFreePrefCrystallizeTest {
    private ChooseCardToSummonForFreePrefCrystallize chooseCardToSummonForFreePrefCrystallize;
    private Player player;
    private FacadeIA facade;

    @BeforeEach
    void setup() {
        chooseCardToSummonForFreePrefCrystallize = new ChooseCardToSummonForFreePrefCrystallize();
        player = Mockito.mock(Player.class);
        facade = Mockito.mock(FacadeIA.class);
        when(player.getFacadeIA()).thenReturn(facade);
    }

    @Test
    void chooseWithOneCrystalCard() {
        Card coffret = Mockito.mock(CoffretMerveilleuxEffect.class);
        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                Mockito.mock(AmsugLongcoupEffect.class),
                Mockito.mock(BottesTemporelles.class),
                Mockito.mock(CoffretMerveilleuxEffect.class),
                Mockito.mock(CaliceDivinEffect.class)
        ));
        when(facade.getCardInHand()).thenReturn(new ArrayList<>(cardsToChooseFrom));
        assertEquals(coffret.getClass(), chooseCardToSummonForFreePrefCrystallize.choose(player).getClass());
    }

    @Test
    void chooseWithoutCrystalCard() {
        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                Mockito.mock(AmsugLongcoupEffect.class),
                Mockito.mock(BottesTemporelles.class),
                Mockito.mock(CaliceDivinEffect.class)
        ));
        when(facade.getCardInHand()).thenReturn(new ArrayList<>(cardsToChooseFrom));
        assertNull(chooseCardToSummonForFreePrefCrystallize.choose(player));
    }

    @Test
    void chooseWithEmptyHand() {
        when(facade.getCardInHand()).thenReturn(new ArrayList<>());
        assertNull(chooseCardToSummonForFreePrefCrystallize.choose(player));
    }
}
