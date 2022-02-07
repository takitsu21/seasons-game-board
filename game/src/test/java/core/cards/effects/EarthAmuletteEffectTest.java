package core.cards.effects;

import core.board.Board;
import core.cards.Card;
import core.cards.Deck;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EarthAmuletteEffectTest {
    private Card card;
    private Board board;
    private Player player;
    private Inventory inventory;


    @BeforeEach
    void setUp() {
        Config config = new Config();
        Deck fullDeck = new Deck();
        card = fullDeck.findCard("amulette de terre");
        GameController controller = new GameController();
        Player[] playerList = App.initPlayer(1, config);
        player = playerList[0];
        controller.initGame(playerList, config);
        controller.getBoard().initPLayersInventory();
        board = controller.getBoard();
        inventory = board.getInventories().get(player);
    }

    @Test
    void useTest() {
        int oldCrystalAmount = inventory.getCrystals();
        card.use(board, player);

        assertEquals(oldCrystalAmount + 9, inventory.getCrystals());

    }

    @Test
    void isMagic() {
        assertTrue(card.isMagic());
    }

    @Test
    void isActivableTest() {
        assertFalse(card.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(card.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.NONE, card.getEffectType());
    }
}
