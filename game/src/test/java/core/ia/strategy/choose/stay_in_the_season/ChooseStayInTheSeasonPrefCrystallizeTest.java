package core.ia.strategy.choose.stay_in_the_season;

import core.board.Board;
import core.board.enums.Energy;
import core.board.enums.Seasons;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.ia.inventory.Inventory;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChooseStayInTheSeasonPrefCrystallizeTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board;
    private ChooseStayInTheSeasonPrefCrystallize chooseStayInTheSeason;
    private Player player;
    private Inventory inventory;

    private Energy water = Energy.WATER;
    private Energy wind = Energy.WIND;
    private Energy fire = Energy.FIRE;
    private Energy earth = Energy.EARTH;


    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = players[0];
        chooseStayInTheSeason = new ChooseStayInTheSeasonPrefCrystallize();
        controller.initGame(players, config);
        board=controller.getBoard();
        board.initHand(player);
        inventory=board.getInventories().get(player);

    }

    @Test
    void chooseWinterTrue() {
        board.setCurrentCursor(1);
        board.getYear().updateYearAndSeason(board);
        assertEquals(Seasons.WINTER, board.getYear().getCurrentSeason());
        inventory.getEnergyStock().addEnergy(earth);

        assertTrue(chooseStayInTheSeason.choose(player));
    }

    @Test
    void chooseSpringTrue() {
        board.setCurrentCursor(4);
        board.getYear().updateYearAndSeason(board);
        assertEquals(Seasons.SPRING, board.getYear().getCurrentSeason());
        inventory.getEnergyStock().addEnergy(fire);

        assertTrue(chooseStayInTheSeason.choose(player));
    }

    @Test
    void chooseSummerTrue() {
        board.setCurrentCursor(7);
        board.getYear().updateYearAndSeason(board);
        assertEquals(Seasons.SUMMER, board.getYear().getCurrentSeason());
        inventory.getEnergyStock().addEnergy(wind);

        assertTrue(chooseStayInTheSeason.choose(player));
    }

    @Test
    void chooseAutomnTrue() {
        board.setCurrentCursor(10);
        board.getYear().updateYearAndSeason(board);
        assertEquals(Seasons.AUTUMN, board.getYear().getCurrentSeason());
        inventory.getEnergyStock().addEnergy(water);

        assertTrue(chooseStayInTheSeason.choose(player));
    }

    @Test
    void chooseWinterFalse() {
        board.setCurrentCursor(1);
        board.getYear().updateYearAndSeason(board);
        assertEquals(Seasons.WINTER, board.getYear().getCurrentSeason());

        assertFalse(chooseStayInTheSeason.choose(player));
    }

    @Test
    void chooseSpringFalse() {
        board.setCurrentCursor(4);
        board.getYear().updateYearAndSeason(board);
        assertEquals(Seasons.SPRING, board.getYear().getCurrentSeason());

        assertFalse(chooseStayInTheSeason.choose(player));
    }

    @Test
    void chooseSummerFalse() {
        board.setCurrentCursor(7);
        board.getYear().updateYearAndSeason(board);
        assertEquals(Seasons.SUMMER, board.getYear().getCurrentSeason());

        assertFalse(chooseStayInTheSeason.choose(player));
    }

    @Test
    void chooseAutomnFalse() {
        board.setCurrentCursor(10);
        board.getYear().updateYearAndSeason(board);
        assertEquals(Seasons.AUTUMN, board.getYear().getCurrentSeason());

        assertFalse(chooseStayInTheSeason.choose(player));
    }

}