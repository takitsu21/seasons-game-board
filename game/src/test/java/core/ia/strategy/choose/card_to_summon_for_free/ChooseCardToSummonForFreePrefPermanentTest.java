package core.ia.strategy.choose.card_to_summon_for_free;

import core.board.FacadeIA;
import core.cards.Card;
import core.cards.effects.*;
import core.ia.Player;
import core.ia.strategy.choose.card_to_summon.ChooseCardToSummonPrefPermanent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ChooseCardToSummonForFreePrefPermanentTest {
    private Player player;
    private FacadeIA facade;
    private ChooseCardToSummonForFreePrefPermanent chooseCardToSummonForFreePrefPermanent;

    @BeforeEach
    void setup() {
        chooseCardToSummonForFreePrefPermanent = new ChooseCardToSummonForFreePrefPermanent();
        player = Mockito.mock(Player.class);
        facade = Mockito.mock(FacadeIA.class);
        when(player.getFacadeIA()).thenReturn(facade);
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
        when(facade.getCardInHand()).thenReturn(new ArrayList<>(cardsToChooseFrom));
        assertTrue(chooseCardToSummonForFreePrefPermanent.choose(player).hasPermanentEffect());
    }

    @Test
    void chooseCardNoPermanentCard() {
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
        when(facade.getSummonableCards()).thenReturn(new ArrayList<>(cardsToChooseFrom));
        assertNull(chooseCardToSummonForFreePrefPermanent.choose(player));
    }

    @Test
    void chooseWithNoCard() {
        when(facade.getSummonableCards()).thenReturn(new ArrayList<>());
        assertNull(chooseCardToSummonForFreePrefPermanent.choose(player));
    }
}
