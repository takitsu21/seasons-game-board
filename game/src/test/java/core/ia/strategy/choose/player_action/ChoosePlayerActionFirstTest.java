package core.ia.strategy.choose.player_action;

import core.board.Board;
import core.board.FacadeIA;
import core.game.App;
import core.game.GameController;
import core.ia.EnumPlayerAction;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChoosePlayerActionFirstTest {
    private final Config config = new Config();
    private final GameController controller = new GameController();
    private final Board board = controller.getBoard();
    private FacadeIA facadeIA;
    private ChoosePlayerActionFirst choosePlayerActionFirst;
    private Player player;

    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = players[0];

        facadeIA = new FacadeIA(board, player, config);
        choosePlayerActionFirst = new ChoosePlayerActionFirst();

    }

    @Test
    void choose() {
        assertEquals(EnumPlayerAction.NOTHING, choosePlayerActionFirst.choose(player));
    }

}