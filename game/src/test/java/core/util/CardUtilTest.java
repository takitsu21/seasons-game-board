package core.util;

import core.cards.Card;
import core.cards.EffectFrequency;
import core.cards.effects.*;
import core.ia.strategy.choose.ChooseFromPrefList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardUtilTest {
    private ArrayList<Class<?>> malusCards;

    @BeforeEach
    void setup() {
        malusCards = CardUtil.getMalusCards();
    }

    @Test
    void strFrequencyTest() {
        EffectFrequency effectFrequency = CardUtil.strFrequencyToEffectFrequency("card");
        assertEquals(EffectFrequency.ON_PLAY, effectFrequency);
    }

    @Test
    void strFrequencyNullTest() {
        EffectFrequency effectFrequency = CardUtil.strFrequencyToEffectFrequency(null);
        assertEquals(EffectFrequency.ON_PLAY, effectFrequency);
    }

    @Test
    void cardUtilThrowTest() {
        assertThrows(IllegalStateException.class, () -> {
            CardUtil cu = new CardUtil();
        });
    }

    @Test
    void testChooseCardToRemoveFromBoardWithRandomCards() {
        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                Mockito.mock(CaliceDivinEffect.class),
                Mockito.mock(GrimoireEnsorceleEffect.class),
                Mockito.mock(AmsugLongcoupEffect.class),
                Mockito.mock(BalanceIshtarEffect.class),
                Mockito.mock(KairnDestructeurEffect.class),
                Mockito.mock(EarthAmuletEffect.class)));
        assertFalse(malusCards.contains(ChooseFromPrefList.getInstance().chooseCardToRemoveFromBoard(cardsToChooseFrom, malusCards).getClass()));
    }

    @Test
    void testChooseCardToRemoveFromBoardWithOnlyMalus() {
        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                Mockito.mock(AmsugLongcoupEffect.class),
                Mockito.mock(KairnDestructeurEffect.class)));
        assertNull(ChooseFromPrefList.getInstance().chooseCardToRemoveFromBoard(cardsToChooseFrom, malusCards));
    }

    @Test
    void testChooseCardToRemoveFromBoardEmpty() {
        assertNull(ChooseFromPrefList.getInstance().chooseCardToRemoveFromBoard(new ArrayList<>(), malusCards));
    }
}