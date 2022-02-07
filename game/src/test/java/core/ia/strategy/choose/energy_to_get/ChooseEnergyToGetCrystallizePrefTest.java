package core.ia.strategy.choose.energy_to_get;

import core.board.Board;
import core.board.FacadeIA;
import core.board.enums.Energy;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChooseEnergyToGetCrystallizePrefTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Player player;
    private FacadeIA facadeIA;
    private Board board;


    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = players[0];
        controller.initGame(players, config);

        board = controller.getBoard();

        board.initPLayersInventory();
        facadeIA = new FacadeIA(board, player, config );
        player.setFacadeIA(facadeIA);


        player = players[0];
        player.setChooseEnergyToGet(new ChooseEnergyToGetCrystallizePref());
    }

    @Test
    void chooseWinter() {
        board.setCurrentCursor(1);
        board.getYear().updateYearAndSeason(board);
        assertEquals(Energy.EARTH, player.chooseEnergyToGet());
    }

    @Test
    void chooseSpring() {
        board.setCurrentCursor(4);
        board.getYear().updateYearAndSeason(board);
        assertEquals(Energy.FIRE, player.chooseEnergyToGet());
    }

    @Test
    void chooseSummer() {
        board.setCurrentCursor(7);
        board.getYear().updateYearAndSeason(board);
        assertEquals(Energy.WIND, player.chooseEnergyToGet());
    }

    @Test
    void chooseAutomn() {
        board.setCurrentCursor(10);
        board.getYear().updateYearAndSeason(board);
        assertEquals(Energy.WATER, player.chooseEnergyToGet());
    }
}