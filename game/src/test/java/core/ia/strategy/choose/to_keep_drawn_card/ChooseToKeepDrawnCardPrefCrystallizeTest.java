package core.ia.strategy.choose.to_keep_drawn_card;

import core.cards.effects.*;
import core.ia.Player;
import core.util.CardUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChooseToKeepDrawnCardPrefCrystallizeTest {
    private ChooseToKeepDrawnCardPrefCrystallize chooseToKeepDrawnCardPrefCrystallize;
    private Player player;

    @BeforeEach
    void setup() {
        chooseToKeepDrawnCardPrefCrystallize = new ChooseToKeepDrawnCardPrefCrystallize();
        player = Mockito.mock(Player.class);
    }

    @Test
    void chooseCrystalCard() {
        assertTrue(chooseToKeepDrawnCardPrefCrystallize.choose(player, Mockito.mock(BalanceIshtarEffect.class)));
        assertTrue(chooseToKeepDrawnCardPrefCrystallize.choose(player, Mockito.mock(BatonDuPrintempsEffect.class)));
        assertTrue(chooseToKeepDrawnCardPrefCrystallize.choose(player, Mockito.mock(BourseIoEffect.class)));
        assertTrue(chooseToKeepDrawnCardPrefCrystallize.choose(player, Mockito.mock(CoffretMerveilleuxEffect.class)));
        assertTrue(chooseToKeepDrawnCardPrefCrystallize.choose(player, Mockito.mock(DeDeLaMaliceEffect.class)));
        assertTrue(chooseToKeepDrawnCardPrefCrystallize.choose(player, Mockito.mock(EarthAmuletEffect.class)));
        assertTrue(chooseToKeepDrawnCardPrefCrystallize.choose(player, Mockito.mock(FigrimAvaricieuxEffect.class)));
        assertTrue(chooseToKeepDrawnCardPrefCrystallize.choose(player, Mockito.mock(HeaumeRagfieldEffect.class)));
        assertTrue(chooseToKeepDrawnCardPrefCrystallize.choose(player, Mockito.mock(PotionVieEffect.class)));
        assertTrue(chooseToKeepDrawnCardPrefCrystallize.choose(player, Mockito.mock(SceptreGrandeurEffect.class)));
        assertTrue(chooseToKeepDrawnCardPrefCrystallize.choose(player, Mockito.mock(StatueBenieOlafEffect.class)));
    }
}
