package core.cards.effects;

import core.board.Board;
import core.cards.Card;
import core.cards.Deck;
import core.cards.EffectFrequency;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CaliceDivinEffectTest {
    private Card cardToPlay;
    private Deck deck;
    private Board board;
    private Player player;
    private GameController controller;


    @BeforeEach
    void setUp() {
        Config config = new Config();
        deck = new Deck();
        cardToPlay = deck.findCard("calice divin");
        controller = new GameController();
        Player[] playerList = App.initPlayer(2, config);
        player = playerList[0];
        controller.initGame(playerList, config);
        board = controller.getBoard();
        for (int i=0; i<playerList.length; i++){
            board.initHand(playerList[i]);
        }
    }

    @Test
    void useTest() {
        Deck d = Mockito.mock(Deck.class);
        Card c = Mockito.mock(Card.class);
        when(d.sampleCard(4)).thenReturn(new ArrayList<>() {{
            add(c);
        }});
        board.setDeck(d);
        Inventory inventory = board.getInventories().get(player);
        inventory.getInvocation().setInvocationPoints(15);
        inventory.getHand().addCard(cardToPlay);
        assertTrue(inventory.summonForFree(cardToPlay, board));
        cardToPlay.use(board, player);
        // on invoque calice divin et on l'utilise puis calice divin
        // nous permet d'invoquer une carte gratuitement
        assertEquals(2, inventory.getInvocation().getCardsOnBoard().size());
    }

    @Test
    void useSummonedCardTest() {
//        Deck d = Mockito.mock(Deck.class);
//        Card c = Mockito.mock(Card.class);
//        when(c.getEffectFrequency()).thenReturn(EffectFrequency.ON_PLAY);
//        when(d.sampleCard(4)).thenReturn(new ArrayList<>() {{
//            add(c);
//        }});
//        board.setDeck(d);
//        Inventory inventory = board.getInventories().get(player);
//        inventory.getInvocation().setInvocationPoints(15);
//        inventory.getHand().addCard(cardToPlay);
//        cardToPlay.use(board, player);
//        verify(c, times(1)).use(board, player);
    }

    @Test
    void removeFromHandIfNotSummoned() {
        Deck d = Mockito.mock(Deck.class);
        Card c = Mockito.mock(Card.class);
        when(d.sampleCard(4)).thenReturn(new ArrayList<>() {{
            add(c);
        }});
        board.setDeck(d);
        Inventory inventory = board.getInventories().get(player);
        inventory.getInvocation().setInvocationPoints(1); //le joueur ne peut invoquer que CaliceDivin
        inventory.getHand().addCard(cardToPlay);
        assertTrue(inventory.summonForFree(cardToPlay, board));
        int preUseHand = inventory.getHand().getCardsInHand().size();
        cardToPlay.use(board, player);
        assertEquals(preUseHand, inventory.getHand().getCardsInHand().size());
    }


    @Test
    void useTestCardNull() {
        Inventory inventory = board.getInventories().get(player);

        deck.getDeck().clear();
        cardToPlay.use(board, player);
        assertEquals(0, inventory.getInvocation().getCardsOnBoard().size());

    }

    @Test
    void isMagic() {
        assertTrue(cardToPlay.isMagic());
    }

    @Test
    void isActivableTest() {
        assertFalse(cardToPlay.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(cardToPlay.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.NONE, cardToPlay.getEffectType());
    }
}
