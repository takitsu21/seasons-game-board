package core.cards.effects;

import core.board.Board;
import core.cards.Card;
import core.cards.Deck;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.ia.inventory.Inventory;
import core.ia.inventory.PlayerEnergyStock;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrimoireEnsorceleEffectTest {
    //TODO voir pourquoi controller est null
    private Card cardToPlay;
    private PlayerEnergyStock playerEnergyStock;
    private Board board;
    private Player player;

    @BeforeEach
    void setUp() {
        Config config = new Config();
        GameController controller = new GameController();
        Deck deck = new Deck();
        cardToPlay = deck.findCard(18);
        Player[] playerList = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        playerList[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        controller.initGame(playerList, config);
        board = controller.getBoard();
        player = playerList[0];
        Inventory inventory = board.getInventories().get(player);
        playerEnergyStock = inventory.getEnergyStock();
        board.initHand(player);
    }

    @Test
    void useTest() {
        int stockMaxSize = playerEnergyStock.getNbMaxEnergy();
        assertEquals(7, stockMaxSize);

        cardToPlay.use(board, player);

        stockMaxSize = playerEnergyStock.getNbMaxEnergy();
        assertEquals(10, stockMaxSize);

        cardToPlay.use(board, player);

        stockMaxSize = playerEnergyStock.getNbMaxEnergy();
        assertEquals(10, stockMaxSize);
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
