package core.ia.strategy.choose.to_keep_drawn_card;

import core.cards.effects.*;
import core.ia.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ChooseToKeepCardPrefPermanentTest {
    private ChooseToKeepDrawnCardPrefPermanent chooseToKeepDrawnCardPrefPermanent;
    private Player player;

    @BeforeEach
    void setup() {
        chooseToKeepDrawnCardPrefPermanent = new ChooseToKeepDrawnCardPrefPermanent();
        player = Mockito.mock(Player.class);
    }

    @Test
    void choose() {
        AmsugLongcoupEffect permanentCard = Mockito.mock(AmsugLongcoupEffect.class);
        when(permanentCard.hasPermanentEffect()).thenReturn(true);
        BalanceIshtarEffect nonPermanentCard = Mockito.mock(BalanceIshtarEffect.class);
        when(nonPermanentCard.hasPermanentEffect()).thenReturn(false);
        BatonDuPrintempsEffect permanentCard2 = Mockito.mock(BatonDuPrintempsEffect.class);
        when(permanentCard2.hasPermanentEffect()).thenReturn(true);
        BourseIoEffect nonPermanentCard2 = Mockito.mock(BourseIoEffect.class);
        when(nonPermanentCard2.hasPermanentEffect()).thenReturn(false);

        assertTrue(chooseToKeepDrawnCardPrefPermanent.choose(player, permanentCard));
        assertFalse(chooseToKeepDrawnCardPrefPermanent.choose(player, nonPermanentCard));
        assertTrue(chooseToKeepDrawnCardPrefPermanent.choose(player, permanentCard2));
        assertFalse(chooseToKeepDrawnCardPrefPermanent.choose(player, nonPermanentCard2));
    }
}
