package core.ia.strategy.choose.stay_in_the_season;

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
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ChooseStayInTheSeasonFirstTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board = controller.getBoard();
    private ChooseStayInTheSeasonFirst chooseStayInTheSeasonFirst;
    private Player player;


    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = players[0];
        chooseStayInTheSeasonFirst = new ChooseStayInTheSeasonFirst();

    }

    @Test
    void choose() {
        assertTrue(chooseStayInTheSeasonFirst.choose(player));
    }


}