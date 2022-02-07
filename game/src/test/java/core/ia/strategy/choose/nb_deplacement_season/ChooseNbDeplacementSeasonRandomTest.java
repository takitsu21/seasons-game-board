package core.ia.strategy.choose.nb_deplacement_season;

import core.board.Board;
import core.board.FacadeIA;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChooseNbDeplacementSeasonRandomTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board = controller.getBoard();
    private FacadeIA facadeIA;
    private ChooseNbDeplacementSeasonRandom chooseNbDeplacementSeasonRandom;
    private Player player;

    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = players[0];

        facadeIA = new FacadeIA(board, player, config );
        player.setFacadeIA(facadeIA);

        chooseNbDeplacementSeasonRandom = new ChooseNbDeplacementSeasonRandom();

    }

    @Test
    void choose() {
        assertEquals(1, chooseNbDeplacementSeasonRandom.choose(player, 0, 1));
    }
}