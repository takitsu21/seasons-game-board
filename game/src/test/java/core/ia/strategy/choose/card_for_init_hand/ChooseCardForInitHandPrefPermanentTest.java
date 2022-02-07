package core.ia.strategy.choose.card_for_init_hand;

import core.cards.Card;
import core.cards.effects.*;
import core.ia.Player;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ChooseCardForInitHandPrefPermanentTest {
    private Player player;
    private Config config;
    private ChooseCardForInitHandPrefPermanent chooseCardForInitHandPrefPermanent;

    @BeforeEach
    void setup() {
        player = Mockito.mock(Player.class);
        chooseCardForInitHandPrefPermanent = new ChooseCardForInitHandPrefPermanent();
        config = new Config();
    }

    @Test
    void chooseWithOnePermanentCard() {
        FireAmuletEffect permanentCard = Mockito.mock(FireAmuletEffect.class);
        when(permanentCard.hasPermanentEffect()).thenReturn(true);
        BalanceIshtarEffect nonPermanentCard = Mockito.mock(BalanceIshtarEffect.class);
        when(nonPermanentCard.hasPermanentEffect()).thenReturn(false);
        BatonDuPrintempsEffect nonPermanentCard2 = Mockito.mock(BatonDuPrintempsEffect.class);
        when(nonPermanentCard2.hasPermanentEffect()).thenReturn(false);
        BourseIoEffect nonPermanentCard3 = Mockito.mock(BourseIoEffect.class);
        when(nonPermanentCard3.hasPermanentEffect()).thenReturn(false);
        CoffretMerveilleuxEffect nonPermanentCard4 = Mockito.mock(CoffretMerveilleuxEffect.class);
        when(nonPermanentCard4.hasPermanentEffect()).thenReturn(false);
        DeDeLaMaliceEffect nonPermanentCard5 = Mockito.mock(DeDeLaMaliceEffect.class);
        when(nonPermanentCard5.hasPermanentEffect()).thenReturn(false);
        EarthAmuletEffect nonPermanentCard6 = Mockito.mock(EarthAmuletEffect.class);
        when(nonPermanentCard6.hasPermanentEffect()).thenReturn(false);
        FigrimAvaricieuxEffect nonPermanentCard7 = Mockito.mock(FigrimAvaricieuxEffect.class);
        when(nonPermanentCard7.hasPermanentEffect()).thenReturn(false);
        GrimoireEnsorceleEffect nonPermanentCard8 = Mockito.mock(GrimoireEnsorceleEffect.class);
        when(nonPermanentCard8.hasPermanentEffect()).thenReturn(false);

        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                nonPermanentCard,
                nonPermanentCard2,
                nonPermanentCard3,
                nonPermanentCard4,
                nonPermanentCard5,
                nonPermanentCard6,
                nonPermanentCard7,
                nonPermanentCard8,
                permanentCard
        ));

        Card fireAmulet = null;
        //pas réussi à faire .asList().contains sur ce tableau de Card donc j'ai fait à la main
        for (Card card : chooseCardForInitHandPrefPermanent.choose(player, cardsToChooseFrom, config)[0]) {
            if (card.getClass() == FireAmuletEffect.class) {
                fireAmulet = card;
            }
        }
        assertNotNull(fireAmulet);
    }

    @Test
    void chooseCardAllPermanentCard() {
        AmsugLongcoupEffect permanentCard = Mockito.mock(AmsugLongcoupEffect.class);
        when(permanentCard.hasPermanentEffect()).thenReturn(true);
        BalanceIshtarEffect permanentCard1 = Mockito.mock(BalanceIshtarEffect.class);
        when(permanentCard1.hasPermanentEffect()).thenReturn(true);
        BatonDuPrintempsEffect permanentCard2 = Mockito.mock(BatonDuPrintempsEffect.class);
        when(permanentCard2.hasPermanentEffect()).thenReturn(true);
        BourseIoEffect permanentCard3 = Mockito.mock(BourseIoEffect.class);
        when(permanentCard3.hasPermanentEffect()).thenReturn(true);
        CaliceDivinEffect permanentCard4 = Mockito.mock(CaliceDivinEffect.class);
        when(permanentCard4.hasPermanentEffect()).thenReturn(true);
        CoffretMerveilleuxEffect permanentCard5 = Mockito.mock(CoffretMerveilleuxEffect.class);
        when(permanentCard5.hasPermanentEffect()).thenReturn(true);
        DeDeLaMaliceEffect permanentCard6 = Mockito.mock(DeDeLaMaliceEffect.class);
        when(permanentCard6.hasPermanentEffect()).thenReturn(true);
        EarthAmuletEffect permanentCard7 = Mockito.mock(EarthAmuletEffect.class);
        when(permanentCard7.hasPermanentEffect()).thenReturn(true);
        FigrimAvaricieuxEffect permanentCard8 = Mockito.mock(FigrimAvaricieuxEffect.class);
        when(permanentCard8.hasPermanentEffect()).thenReturn(true);
        GrimoireEnsorceleEffect nonPermanentCard = Mockito.mock(GrimoireEnsorceleEffect.class);
        when(nonPermanentCard.hasPermanentEffect()).thenReturn(false);

        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                permanentCard1,
                permanentCard2,
                permanentCard3,
                permanentCard4,
                permanentCard5,
                permanentCard6,
                nonPermanentCard,
                permanentCard7,
                permanentCard8,
                permanentCard
        ));

        boolean allPerma = true;
        Card[][] choosenHand = chooseCardForInitHandPrefPermanent.choose(player, cardsToChooseFrom, config);
        for (Card[] cards : choosenHand) {
            for (Card card : cards) {
                if (!card.hasPermanentEffect()) {
                    allPerma = false;
                    break;
                }
            }
        }
        assertTrue(allPerma);
    }

    @Test
    void chooseWithNoPermanentCard() {
        AmsugLongcoupEffect nonPermanentCard1 = Mockito.mock(AmsugLongcoupEffect.class);
        when(nonPermanentCard1.hasPermanentEffect()).thenReturn(false);
        BalanceIshtarEffect nonPermanentCard2 = Mockito.mock(BalanceIshtarEffect.class);
        when(nonPermanentCard2.hasPermanentEffect()).thenReturn(false);
        BatonDuPrintempsEffect nonPermanentCard3 = Mockito.mock(BatonDuPrintempsEffect.class);
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
                nonPermanentCard5,
                nonPermanentCard6,
                nonPermanentCard7,
                nonPermanentCard8,
                nonPermanentCard9
        ));

        boolean allPerma = false;
        Card[][] choosenHand = chooseCardForInitHandPrefPermanent.choose(player, cardsToChooseFrom, config);
        for (Card[] cards : choosenHand) {
            for (Card card : cards) {
                if (card.hasPermanentEffect()) {
                    allPerma = true;
                    break;
                }
            }
        }
        assertFalse(allPerma);
    }
}
