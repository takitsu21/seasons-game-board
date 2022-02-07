package core.ia.strategy.choose.card_for_init_hand;

import core.cards.Card;
import core.cards.effects.*;
import core.ia.Player;
import core.util.CardUtil;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChooseCardForInitHandPrefCrystallizeTest {
    private Player player;
    private Config config;
    private ChooseCardForInitHandPrefCrystallize chooseCardForInitHandPrefCrystallize;
    private List<Class<?>> crystalCards;

    @BeforeEach
    void setup() {
        player = Mockito.mock(Player.class);
        crystalCards = CardUtil.getCrystalCards();
        chooseCardForInitHandPrefCrystallize = new ChooseCardForInitHandPrefCrystallize();
        config = new Config();
    }

    @Test
    void chooseWithOneCrystalCard() {
        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                Mockito.mock(AmsugLongcoupEffect.class),
                Mockito.mock(BottesTemporelles.class),
                Mockito.mock(CoffretMerveilleuxEffect.class),
                Mockito.mock(CaliceDivinEffect.class)
        ));
        Card coffret = null;
        for (Card card : chooseCardForInitHandPrefCrystallize.choose(player, cardsToChooseFrom, config)[0]) {
            if (card.getClass() == CoffretMerveilleuxEffect.class) {
                coffret = card;
            }
        }
        assertNotNull(coffret);
    }

    @Test
    void chooseCardAllCrystalCard() {
        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                Mockito.mock(BalanceIshtarEffect.class),
                Mockito.mock(BatonDuPrintempsEffect.class),
                Mockito.mock(BourseIoEffect.class),
                Mockito.mock(CoffretMerveilleuxEffect.class),
                Mockito.mock(DeDeLaMaliceEffect.class),
                Mockito.mock(EarthAmuletEffect.class),
                Mockito.mock(FigrimAvaricieuxEffect.class),
                Mockito.mock(HeaumeRagfieldEffect.class),
                Mockito.mock(PotionVieEffect.class),
                Mockito.mock(SceptreGrandeurEffect.class)
        ));
        boolean allCrystal = true;
        Card[][] choosenHand = chooseCardForInitHandPrefCrystallize.choose(player, cardsToChooseFrom, config);
        for (Card[] cards : choosenHand) {
            for (Card card : cards) {
                if (!crystalCards.contains(card.getClass())) {
                    allCrystal = false;
                    break;
                }
            }
        }
        assertTrue(allCrystal);
    }

    @Test
    void chooseWithNoCrystalCard() {
        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                Mockito.mock(AmsugLongcoupEffect.class),
                Mockito.mock(BottesTemporelles.class),
                Mockito.mock(CaliceDivinEffect.class),
                Mockito.mock(CorneDuMendiant.class),
                Mockito.mock(AmsugLongcoupEffect.class),
                Mockito.mock(BottesTemporelles.class),
                Mockito.mock(CaliceDivinEffect.class),
                Mockito.mock(CorneDuMendiant.class),
                Mockito.mock(AmsugLongcoupEffect.class)
        ));
        boolean allCrystal = false;
        Card[][] choosenHand = chooseCardForInitHandPrefCrystallize.choose(player, cardsToChooseFrom, config);
        for (Card[] cards : choosenHand) {
            for (Card card : cards) {
                if (crystalCards.contains(card.getClass())) {
                    allCrystal = true;
                    break;
                }
            }
        }
        assertFalse(allCrystal);
    }
}
