package core.ia.strategy.choose.to_keep_drawn_card;

import core.cards.effects.*;
import core.ia.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ChooseToKeepDrawnCardMalusTest {
    private ChooseToKeepDrawnCardMalus chooseToKeepDrawnCardMalus;
    private Player player;

    @BeforeEach
    void setup() {
        chooseToKeepDrawnCardMalus = new ChooseToKeepDrawnCardMalus();
        player = Mockito.mock(Player.class);
    }

    @Test
    void chooseMalus() {
        assertTrue(chooseToKeepDrawnCardMalus.choose(player, Mockito.mock(AmsugLongcoupEffect.class)));
        assertTrue(chooseToKeepDrawnCardMalus.choose(player, Mockito.mock(FigrimAvaricieuxEffect.class)));
        assertTrue(chooseToKeepDrawnCardMalus.choose(player, Mockito.mock(KairnDestructeurEffect.class)));
        assertTrue(chooseToKeepDrawnCardMalus.choose(player, Mockito.mock(SyllasLeFideleEffect.class)));
    }
}