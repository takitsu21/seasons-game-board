package core.ia.strategy.choose.go_to_the_previous_season;

import core.board.Board;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChooseGoToThePreviousSeasonFirstTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board = controller.getBoard();
    private ChooseGoToThePreviousSeasonFirst chooseGoToThePreviousSeason;
    private Player player;


    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = players[0];
        chooseGoToThePreviousSeason = new ChooseGoToThePreviousSeasonFirst();

    }

    @Test
    void choose() {
        assertTrue(chooseGoToThePreviousSeason.choose(player));
    }
}