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

public class ChooseCardBetweenMultipleToGetPrefCrystallizeTest {
    private ChooseCardBetweenMultipleToGetPrefCrystallize chooseCardBetweenMultipleToGetPrefCrystallize;
    private List<Class<?>> crystalCards;
    private Player player;

    @BeforeEach
    void setup() {
        player = Mockito.mock(Player.class);
        crystalCards = CardUtil.getCrystalCards();
        chooseCardBetweenMultipleToGetPrefCrystallize = new ChooseCardBetweenMultipleToGetPrefCrystallize();
        player.setChooseCardBetweenMultipleToGet(chooseCardBetweenMultipleToGetPrefCrystallize);

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
        assertEquals(coffret.getClass(), chooseCardBetweenMultipleToGetPrefCrystallize.choose(player, cardsToChooseFrom).getClass());
    }

    @Test
    void chooseWithMultipleCrystalCards() {
        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                Mockito.mock(AmsugLongcoupEffect.class),
                Mockito.mock(BalanceIshtarEffect.class),
                Mockito.mock(BottesTemporelles.class),
                Mockito.mock(CoffretMerveilleuxEffect.class),
                Mockito.mock(CaliceDivinEffect.class),
                Mockito.mock(CorneDuMendiant.class),
                Mockito.mock(HeaumeRagfieldEffect.class)
        ));
        assertTrue(crystalCards.contains(chooseCardBetweenMultipleToGetPrefCrystallize.choose(player, cardsToChooseFrom).getClass()));
    }

    @Test
    void chooseWithNoCrystalCard() {
        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                Mockito.mock(AmsugLongcoupEffect.class),
                Mockito.mock(BottesTemporelles.class),
                Mockito.mock(CaliceDivinEffect.class),
                Mockito.mock(CorneDuMendiant.class)
        ));
        assertNull(chooseCardBetweenMultipleToGetPrefCrystallize.choose(player, cardsToChooseFrom));
    }

    @Test
    void chooseWithNoCard() {
        assertNull(chooseCardBetweenMultipleToGetPrefCrystallize.choose(player, new ArrayList<>()));
    }
}
