package core.cards;

import core.board.Board;
import core.board.enums.Energy;
import core.exception.CardNotFoundException;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.ia.inventory.Inventory;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    private Deck deck;
    private Board board;
    private Player player;

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        Config config = new Config();
        GameController controller = new GameController();
        Player[] playerList = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        playerList[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = playerList[0];
        controller.initGame(playerList, config);
        board = controller.getBoard();
        deck = new Deck();
        inventory = board.getInventories().get(player);
        board.initHand(player);
    }

    @Test
    void findCartByIdTest() {
        Deck fullDeck = new Deck();
        Card id22 = fullDeck.findCard(22);
        assertEquals(20, id22.getCrystalCost());
        assertEquals(30, id22.getPrestigePointValue());
        assertTrue(id22.getEnergyCost().isEmpty());
        assertTrue(id22.hasPermanentEffect());
    }

    @Test
    void findCartByNameTest() {
        Deck fullDeck = new Deck();
        Card potionDeReves = fullDeck.findCard(24);
        assertTrue(potionDeReves.getName().equalsIgnoreCase("potion de reves"));
        assertEquals(0, potionDeReves.getCrystalCost());
        assertEquals(0, potionDeReves.getPrestigePointValue());
        assertEquals(List.of(Energy.WIND, Energy.WIND), potionDeReves.getEnergyCost());
        assertEquals(EffectFrequency.ON_ACTIVATION, potionDeReves.getEffectFrequency());
    }

    @Test
    void findCartThrowIdTest() {
        assertThrows(CardNotFoundException.class, () -> {
            Card notFound = deck.findCard(31);
        });
    }

    @Test
    void findCardThrowNameTest() {
        assertThrows(CardNotFoundException.class, () -> {
            Card notFound = deck.findCard("carte introuvable");
        });
    }

    @Test
    void recycleDiscardTest() {
        deck = new Deck();
        assertTrue(deck.getDiscardPool().isEmpty());
        deck.getDiscardPool().addAll(deck.getDeck());
        deck.getDeck().removeAll(deck.getDeck());
        assertTrue(deck.isEmpty());
        assertEquals(60, deck.getDiscardPool().size());
        assertEquals(0, deck.getDeck().size());
        deck.recycle();
        assertEquals(0, deck.getDiscardPool().size());
        assertEquals(60, deck.size());
    }

    @Test
    void drawCardInventoryTest() {
        assertTrue(deck.drawCard(inventory));
        deck.getDeck().clear();
        assertFalse(deck.drawCard(inventory));
    }

    @Test
    void drawCardTest() {
        assertInstanceOf(Card.class, deck.drawCard());
        deck.getDeck().clear();
        assertNull(deck.drawCard());
    }

    @Test
    void shuffleTest() {
        List<Card> oldDeck = List.copyOf(deck.getDeck());
        assertEquals(oldDeck, deck.getDeck());
        deck.shuffle();
        assertNotEquals(oldDeck, deck.getDeck());
    }

    @Test
    void isEmptyTest() {
        assertFalse(deck.isEmpty());
        deck.getDeck().clear();
        assertTrue(deck.isEmpty());
    }

    @Test
    void tostring() {
        deck.getDeck().clear();
        assertEquals("Deck{deck=[]}", deck.toString());
    }
}
