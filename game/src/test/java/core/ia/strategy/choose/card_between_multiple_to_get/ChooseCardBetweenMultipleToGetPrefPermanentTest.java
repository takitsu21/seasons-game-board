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

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ChooseCardBetweenMultipleToGetPrefPermanentTest {
    private ChooseCardBetweenMultipleToGetPrefPermanent chooseCardBetweenMultipleToGetPrefPermanent;
    private Player player;

    @BeforeEach
    void setup() {
        player = Mockito.mock(Player.class);
        chooseCardBetweenMultipleToGetPrefPermanent = new ChooseCardBetweenMultipleToGetPrefPermanent();
        player.setChooseCardBetweenMultipleToGet(chooseCardBetweenMultipleToGetPrefPermanent);
    }

    @Test
    void chooseWithOnePermanentCard() {
        FireAmuletEffect permanentCard = Mockito.mock(FireAmuletEffect.class);
        when(permanentCard.hasPermanentEffect()).thenReturn(true);
        BalanceIshtarEffect nonPermanentCard1 = Mockito.mock(BalanceIshtarEffect.class);
        when(nonPermanentCard1.hasPermanentEffect()).thenReturn(false);
        BatonDuPrintempsEffect nonPermanentCard2 = Mockito.mock(BatonDuPrintempsEffect.class);
        when(nonPermanentCard2.hasPermanentEffect()).thenReturn(false);
        BourseIoEffect nonPermanentCard3 = Mockito.mock(BourseIoEffect.class);
        when(nonPermanentCard3.hasPermanentEffect()).thenReturn(false);
        BourseIoEffect nonPermanentCard4 = Mockito.mock(BourseIoEffect.class);
        when(nonPermanentCard4.hasPermanentEffect()).thenReturn(false);
        CoffretMerveilleuxEffect nonPermanentCard5 = Mockito.mock(CoffretMerveilleuxEffect.class);
        when(nonPermanentCard5.hasPermanentEffect()).thenReturn(false);
        DeDeLaMaliceEffect nonPermanentCard6 = Mockito.mock(DeDeLaMaliceEffect.class);
        when(nonPermanentCard6.hasPermanentEffect()).thenReturn(false);
        EarthAmuletEffect nonPermanentCard7 = Mockito.mock(EarthAmuletEffect.class);
        when(nonPermanentCard7.hasPermanentEffect()).thenReturn(false);
        FigrimAvaricieuxEffect nonPermanentCard8 = Mockito.mock(FigrimAvaricieuxEffect.class);
        when(nonPermanentCard8.hasPermanentEffect()).thenReturn(false);
        GrimoireEnsorceleEffect nonPermanentCard9 = Mockito.mock(GrimoireEnsorceleEffect.class);
        when(nonPermanentCard9.hasPermanentEffect()).thenReturn(false);

        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                nonPermanentCard1,
                nonPermanentCard2,
                nonPermanentCard3,
                nonPermanentCard4,
                permanentCard,
                nonPermanentCard5,
                nonPermanentCard6,
                nonPermanentCard7,
                nonPermanentCard8,
                nonPermanentCard9
        ));
        assertTrue(chooseCardBetweenMultipleToGetPrefPermanent.choose(player, cardsToChooseFrom).hasPermanentEffect());
    }

    @Test
    void chooseWithMultiplePermanentCards() {
        FireAmuletEffect permanentCard = Mockito.mock(FireAmuletEffect.class);
        when(permanentCard.hasPermanentEffect()).thenReturn(true);
        BalanceIshtarEffect nonPermanentCard1 = Mockito.mock(BalanceIshtarEffect.class);
        when(nonPermanentCard1.hasPermanentEffect()).thenReturn(false);
        BatonDuPrintempsEffect nonPermanentCard2 = Mockito.mock(BatonDuPrintempsEffect.class);
        when(nonPermanentCard2.hasPermanentEffect()).thenReturn(false);
        BourseIoEffect permanentCard2 = Mockito.mock(BourseIoEffect.class);
        when(permanentCard2.hasPermanentEffect()).thenReturn(true);
        BourseIoEffect nonPermanentCard4 = Mockito.mock(BourseIoEffect.class);
        when(nonPermanentCard4.hasPermanentEffect()).thenReturn(false);
        CoffretMerveilleuxEffect nonPermanentCard5 = Mockito.mock(CoffretMerveilleuxEffect.class);
        when(nonPermanentCard5.hasPermanentEffect()).thenReturn(false);
        DeDeLaMaliceEffect permanentCard3 = Mockito.mock(DeDeLaMaliceEffect.class);
        when(permanentCard3.hasPermanentEffect()).thenReturn(true);
        EarthAmuletEffect nonPermanentCard7 = Mockito.mock(EarthAmuletEffect.class);
        when(nonPermanentCard7.hasPermanentEffect()).thenReturn(false);
        FigrimAvaricieuxEffect nonPermanentCard8 = Mockito.mock(FigrimAvaricieuxEffect.class);
        when(nonPermanentCard8.hasPermanentEffect()).thenReturn(false);
        GrimoireEnsorceleEffect nonPermanentCard9 = Mockito.mock(GrimoireEnsorceleEffect.class);
        when(nonPermanentCard9.hasPermanentEffect()).thenReturn(false);

        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                nonPermanentCard1,
                nonPermanentCard2,
                permanentCard2,
                nonPermanentCard4,
                permanentCard,
                nonPermanentCard5,
                permanentCard3,
                nonPermanentCard7,
                nonPermanentCard8,
                nonPermanentCard9
        ));
        assertTrue(chooseCardBetweenMultipleToGetPrefPermanent.choose(player, cardsToChooseFrom).hasPermanentEffect());
    }

    @Test
    void chooseWithNoPermanentCard() {
        FireAmuletEffect nonPermanentCard = Mockito.mock(FireAmuletEffect.class);
        when(nonPermanentCard.hasPermanentEffect()).thenReturn(false);
        BalanceIshtarEffect nonPermanentCard1 = Mockito.mock(BalanceIshtarEffect.class);
        when(nonPermanentCard1.hasPermanentEffect()).thenReturn(false);
        BatonDuPrintempsEffect nonPermanentCard2 = Mockito.mock(BatonDuPrintempsEffect.class);
        when(nonPermanentCard2.hasPermanentEffect()).thenReturn(false);
        BourseIoEffect nonPermanentCard3 = Mockito.mock(BourseIoEffect.class);
        when(nonPermanentCard3.hasPermanentEffect()).thenReturn(false);
        BourseIoEffect nonPermanentCard4 = Mockito.mock(BourseIoEffect.class);
        when(nonPermanentCard4.hasPermanentEffect()).thenReturn(false);
        CoffretMerveilleuxEffect nonPermanentCard5 = Mockito.mock(CoffretMerveilleuxEffect.class);
        when(nonPermanentCard5.hasPermanentEffect()).thenReturn(false);
        DeDeLaMaliceEffect nonPermanentCard6 = Mockito.mock(DeDeLaMaliceEffect.class);
        when(nonPermanentCard6.hasPermanentEffect()).thenReturn(false);
        EarthAmuletEffect nonPermanentCard7 = Mockito.mock(EarthAmuletEffect.class);
        when(nonPermanentCard7.hasPermanentEffect()).thenReturn(false);
        FigrimAvaricieuxEffect nonPermanentCard8 = Mockito.mock(FigrimAvaricieuxEffect.class);
        when(nonPermanentCard8.hasPermanentEffect()).thenReturn(false);
        GrimoireEnsorceleEffect nonPermanentCard9 = Mockito.mock(GrimoireEnsorceleEffect.class);
        when(nonPermanentCard9.hasPermanentEffect()).thenReturn(false);

        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                nonPermanentCard,
                nonPermanentCard1,
                nonPermanentCard2,
                nonPermanentCard3,
                nonPermanentCard4,
                nonPermanentCard5,
                nonPermanentCard6,
                nonPermanentCard7,
                nonPermanentCard8,
                nonPermanentCard9
        ));
        assertNull(chooseCardBetweenMultipleToGetPrefPermanent.choose(player, cardsToChooseFrom));
    }

    @Test
    void chooseWithNoCard() {
        List<Card> cardsToChooseFrom = new ArrayList<>();
        assertNull(chooseCardBetweenMultipleToGetPrefPermanent.choose(player, cardsToChooseFrom));
    }
}
