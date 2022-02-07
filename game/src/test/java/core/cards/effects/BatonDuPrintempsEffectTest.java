package core.cards.effects;

import core.board.Board;
import core.board.Year;
import core.cards.Card;
import core.cards.Deck;
import core.dice.Dice;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.ia.inventory.Inventory;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BatonDuPrintempsEffectTest {
    private Deck deck;
    private Player p1;
    private Inventory inventory1;
    private Card baton;

    private Board board;

    @BeforeEach
    public void setUp() {
        Config config = new Config();
        GameController controller = new GameController();
        PlayerFactory playerFactory = new PlayerFactory(config);
        p1 = playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA1");

        Player[] playerList = new Player[]{p1};

        controller.initGame(playerList, config);
        board = controller.getBoard();
        board.initPLayersInventory();
        board.getInventories().get(p1).setCurrentDice(new Dice(0, new Year(config.getNbYears())));
        inventory1 = board.getInventories().get(p1);

        deck = new Deck();
        baton = deck.findCard(6);
    }

    @Test
    void useTest() {
        int oldCrystalAmount = inventory1.getCrystals();
        inventory1.triggerVaseAndBaton(board, p1);
        assertEquals(oldCrystalAmount, inventory1.getCrystals());

        inventory1.getInvocation().getCardsOnBoard().add(baton);
        inventory1.triggerVaseAndBaton(board, p1);
        assertEquals(oldCrystalAmount + 3, inventory1.getCrystals());
    }

    @Test
    void isMagicTest() {
        assertTrue(baton.isMagic());
    }

    @Test
    void isActivableTest() {
        assertFalse(baton.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(baton.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.ON_SUMMON_CARD, baton.getEffectType());
    }
}