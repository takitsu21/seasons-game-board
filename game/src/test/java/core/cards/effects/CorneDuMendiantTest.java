package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CorneDuMendiantTest {
    private Card corne;
    private Board board;
    private Player player;


    @BeforeEach
    void setUp() {
        Config config = new Config();
        Deck fullDeck = new Deck();
        corne = fullDeck.findCard(14);
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
        List<Energy> playerEnergyStock = board.getInventories().get(player).getEnergyStock().getEnergyStock();
        int oldSize = playerEnergyStock.size();

        corne.use(board, player);

        assertEquals(oldSize + 1, board.getInventories().get(player).getEnergyStock().getEnergyStock().size());
        assertTrue(board.getInventories().get(player).getEnergyStock().getEnergyStock().size() <= 2);

        for (int i = 0; i < 10; i++) {
            corne.use(board, player);
        }

        assertEquals(2, board.getInventories().get(player).getEnergyStock().getEnergyStock().size());
    }

    @Test
    void isMagic() {
        assertTrue(corne.isMagic());
    }

    @Test
    void effectType() {
        assertEquals(EffectType.END_TURN, corne.getEffectType());
    }


    @Test
    void isActivableTest() {
        assertFalse(corne.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(corne.isActivated());
    }

}
