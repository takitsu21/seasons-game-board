package core.cards.effects;

import core.board.Board;
import core.board.Year;
import core.cards.Card;
import core.cards.Deck;
import core.dice.Dice;
import core.dice.Face;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeDeLaMaliceTest {
    private Card deMalice;
    private Board board;
    private Player player;
    private Config config = new Config();


    @BeforeEach
    void setUp() {
        Deck fullDeck = new Deck();
        deMalice = fullDeck.findCard(15);
        GameController controller = new GameController();
        Player[] playerList = App.initPlayer(1, config);
        player = playerList[0];
        controller.initGame(playerList, config);
        controller.getBoard().initPLayersInventory();
        board = controller.getBoard();
    }

    @Test
    void useTest() {
        Inventory inventory = board.getInventories().get(player);
        inventory.setCurrentDice(new Dice(0, new Year(config.getNbYears())));
        int oldCrystalCount = inventory.getCrystals();
        //Face oldFace = inventory.getCurrentDice().getCurrentFace();

        deMalice.use(board, player);

        //assertNotEquals(oldFace, inventory.getCurrentDice().getCurrentFace()); //peut foirer si la face renvoyée est la même
        assertEquals(oldCrystalCount + 2, inventory.getCrystals());

        int secondCrystalCount = inventory.getCrystals();
        Face secondFace = inventory.getCurrentDice().getCurrentFace();

        deMalice.use(board, player);

        assertEquals(secondFace, inventory.getCurrentDice().getCurrentFace());
        assertEquals(secondCrystalCount, inventory.getCrystals());
    }

    @Test
    void isMagic() {
        assertTrue(deMalice.isMagic());
    }

    @Test
    void isActivable() {
        assertTrue(deMalice.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(deMalice.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.NONE, deMalice.getEffectType());
    }
}
