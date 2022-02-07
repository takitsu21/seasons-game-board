package core.ia.strategy.choose.card_between_multiple_to_get;

import core.cards.Card;
import core.cards.effects.*;
import core.ia.Player;
import core.util.CardUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChooseCardBetweenMultipleToGetMalusTest {
    private ChooseCardBetweenMultipleToGetMalus chooseCardBetweenMultipleToGetMalus;
    private List<Class<?>> malusCards;
    private Player player;

    @BeforeEach
    void setup() {
        player = Mockito.mock(Player.class);
        malusCards = CardUtil.getMalusCards();
        chooseCardBetweenMultipleToGetMalus = new ChooseCardBetweenMultipleToGetMalus();
        player.setChooseCardBetweenMultipleToGet(chooseCardBetweenMultipleToGetMalus);
    }

    @Test
    void chooseWithOneMalusCards() {
        Card kairn = Mockito.mock(KairnDestructeurEffect.class);
        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                Mockito.mock(CaliceDivinEffect.class),
                Mockito.mock(BalanceIshtarEffect.class),
                Mockito.mock(KairnDestructeurEffect.class),
                Mockito.mock(EarthAmuletEffect.class)));
        assertEquals(kairn.getClass(), chooseCardBetweenMultipleToGetMalus.choose(player, cardsToChooseFrom).getClass());
    }

    @Test
    void chooseWithSeveralMalusCards() {
        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                Mockito.mock(CaliceDivinEffect.class),
                Mockito.mock(GrimoireEnsorceleEffect.class),
                Mockito.mock(AmsugLongcoupEffect.class),
                Mockito.mock(BalanceIshtarEffect.class),
                Mockito.mock(KairnDestructeurEffect.class),
                Mockito.mock(EarthAmuletEffect.class)));
        assertTrue(malusCards.contains(chooseCardBetweenMultipleToGetMalus.choose(player, cardsToChooseFrom).getClass()));
    }

    @Test
    void chooseWithNoMalusCard() {
        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                Mockito.mock(CaliceDivinEffect.class),
                Mockito.mock(GrimoireEnsorceleEffect.class),
                Mockito.mock(BalanceIshtarEffect.class),
                Mockito.mock(EarthAmuletEffect.class)));
        assertNull(chooseCardBetweenMultipleToGetMalus.choose(player, cardsToChooseFrom));
    }

    @Test
    void chooseWithNoCard() {
        assertNull(chooseCardBetweenMultipleToGetMalus.choose(player, new ArrayList<>()));
    }

}