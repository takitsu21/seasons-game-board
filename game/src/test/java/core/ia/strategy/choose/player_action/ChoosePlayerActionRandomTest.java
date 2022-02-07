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

class ChoosePlayerActionRandomTest {
    private final Config config = new Config();
    private final GameController controller = new GameController();
    private Board board;
    private FacadeIA facadeIA;
    private ChoosePlayerActionRandom choosePlayerActionRandom;
    private Player player;

    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = players[0];
        controller.initGame(players, config);
        board = controller.getBoard();
        board.initHand(player);
        facadeIA = new FacadeIA(board, player, config );


        player.setFacadeIA(facadeIA);
        choosePlayerActionRandom = new ChoosePlayerActionRandom();
    }

    @Test
    void choose() {
        assertEquals(EnumPlayerAction.class, choosePlayerActionRandom.choose(player).getClass());
    }
}