package core.cards.effects;

import core.board.Board;
import core.cards.Card;
import core.cards.Deck;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaterAmuletteEffectTest {
    private Card cardToPlay;
    private Board board;
    private Player player;


    @BeforeEach
    void setUp() {
        Config config = new Config();
        Deck deck = new Deck();
        cardToPlay = deck.findCard("amulette d'eau");
        GameController controller = new GameController();
        Player[] playerList = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        playerList[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = playerList[0];
        controller.initGame(playerList, config);
        controller.getBoard().initPLayersInventory();
        controller.getBoard().initHand(player);
        board = controller.getBoard();
        board.initHand(player);
    }

    @Test
    void useTest() {
        cardToPlay.use(board, player);

        assertEquals(4, cardToPlay.getEnergyStock().getEnergyStock().size());
    }

    @Test
    void isMagicTest() {
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
