package core.ia.strategy.choose.card_for_init_hand;

import core.cards.Card;
import core.cards.effects.*;
import core.ia.Player;
import core.util.CardUtil;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChooseCardForInitHandPrefMalusTest {
    private Player player;
    private Config config;
    private ChooseCardForInitHandPrefMalus chooseCardForInitHandPrefMalus;
    private List<Class<?>> malusCards;

    @BeforeEach
    void setup() {
        player = Mockito.mock(Player.class);
        malusCards = CardUtil.getMalusCards();
        chooseCardForInitHandPrefMalus = new ChooseCardForInitHandPrefMalus();
        config = new Config();
    }

    @Test
    void chooseWithOneMalusCard() {
        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                Mockito.mock(CaliceDivinEffect.class),
                Mockito.mock(GrimoireEnsorceleEffect.class),
                Mockito.mock(MainFortuneEffect.class),
                Mockito.mock(BottesTemporelles.class),
                Mockito.mock(HeaumeRagfieldEffect.class),
                Mockito.mock(BalanceIshtarEffect.class),
                Mockito.mock(KairnDestructeurEffect.class),
                Mockito.mock(NariaLaProphetesseEffect.class),
                Mockito.mock(EarthAmuletEffect.class)));
        Card kairn = null;
        //pas réussi à faire .asList().contains sur ce tableau de Card donc j'ai fait à la main
        for (Card card : chooseCardForInitHandPrefMalus.choose(player, cardsToChooseFrom, config)[0]) {
            if (card.getClass() == KairnDestructeurEffect.class) {
                kairn = card;
            }
        }
        assertNotNull(kairn);
    }

    @Test
    void ChooseWithOnlyMalusCard() {
        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                Mockito.mock(AmsugLongcoupEffect.class),
                Mockito.mock(FigrimAvaricieuxEffect.class),
                Mockito.mock(KairnDestructeurEffect.class),
                Mockito.mock(SyllasLeFideleEffect.class),
                Mockito.mock(FireAmuletEffect.class),
                Mockito.mock(AmsugLongcoupEffect.class),
                Mockito.mock(FigrimAvaricieuxEffect.class),
                Mockito.mock(KairnDestructeurEffect.class),
                Mockito.mock(SyllasLeFideleEffect.class),
                Mockito.mock(FigrimAvaricieuxEffect.class)));
        boolean allMalus = true;
        Card[][] choosenHand = chooseCardForInitHandPrefMalus.choose(player, cardsToChooseFrom, config);
        for (Card[] cards : choosenHand) {
            for (Card card : cards) {
                if (!malusCards.contains(card.getClass())) {
                    allMalus = false;
                    break;
                }
            }
        }
        assertTrue(allMalus);
    }

    @Test
    void chooseWithNoMalusCard() {
        List<Card> cardsToChooseFrom = new ArrayList<>(Arrays.asList(
                Mockito.mock(CaliceDivinEffect.class),
                Mockito.mock(GrimoireEnsorceleEffect.class),
                Mockito.mock(NariaLaProphetesseEffect.class),
                Mockito.mock(MainFortuneEffect.class),
                Mockito.mock(BottesTemporelles.class),
                Mockito.mock(HeaumeRagfieldEffect.class),
                Mockito.mock(BalanceIshtarEffect.class),
                Mockito.mock(NariaLaProphetesseEffect.class),
                Mockito.mock(EarthAmuletEffect.class)));
        boolean allMalus = false;
        Card[][] choosenHand = chooseCardForInitHandPrefMalus.choose(player, cardsToChooseFrom, config);
        for (Card[] cards : choosenHand) {
            for (Card card : cards) {
                if (malusCards.contains(card.getClass())) {
                    allMalus = true;
                    break;
                }
            }
        }
        assertFalse(allMalus);
    }
}