package core.ia.strategy.choose.go_to_the_next_season;

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

class ChooseGoToTheNextSeasonPrefCrystallizeTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board;
    private ChooseGoToTheNextSeasonPrefCrystallize chooseGoToTheNextSeason;
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
        chooseGoToTheNextSeason = new ChooseGoToTheNextSeasonPrefCrystallize();
        controller.initGame(players, config);
        board=controller.getBoard();
        board.initHand(player);
        inventory=board.getInventories().get(player);

        inventory.getEnergyStock().addEnergy(water);
        inventory.getEnergyStock().addEnergy(wind);
        inventory.getEnergyStock().addEnergy(fire);
        inventory.getEnergyStock().addEnergy(earth);

    }

    //TODO fix true
    @Test
    void chooseWinterTrue() {
        board.setCurrentCursor(1);
        board.getYear().updateYearAndSeason(board);
        assertEquals(Seasons.WINTER, board.getYear().getCurrentSeason());

        assertTrue(chooseGoToTheNextSeason.choose(player));
    }

    @Test
    void chooseSpringTrue() {
        board.setCurrentCursor(4);
        board.getYear().updateYearAndSeason(board);
        assertEquals(Seasons.SPRING, board.getYear().getCurrentSeason());

        assertTrue(chooseGoToTheNextSeason.choose(player));
    }

    @Test
    void chooseSummerTrue() {
        board.setCurrentCursor(7);
        board.getYear().updateYearAndSeason(board);
        assertEquals(Seasons.SUMMER, board.getYear().getCurrentSeason());

        assertTrue(chooseGoToTheNextSeason.choose(player));
    }

    @Test
    void chooseAutomnTrue() {
        board.setCurrentCursor(10);
        board.getYear().updateYearAndSeason(board);
        assertEquals(Seasons.AUTUMN, board.getYear().getCurrentSeason());

        assertTrue(chooseGoToTheNextSeason.choose(player));
    }

    @Test
    void chooseWinterFalse() {
        board.setCurrentCursor(1);
        board.getYear().updateYearAndSeason(board);
        assertEquals(Seasons.WINTER, board.getYear().getCurrentSeason());

        inventory.getEnergyStock().removeEnergy(List.of(water, wind, fire, earth));

        assertFalse(chooseGoToTheNextSeason.choose(player));
    }

    @Test
    void chooseSpringFalse() {
        board.setCurrentCursor(4);
        board.getYear().updateYearAndSeason(board);
        assertEquals(Seasons.SPRING, board.getYear().getCurrentSeason());

        inventory.getEnergyStock().removeEnergy(List.of(water, wind, fire, earth));

        assertFalse(chooseGoToTheNextSeason.choose(player));
    }

    @Test
    void chooseSummerFalse() {
        board.setCurrentCursor(7);
        board.getYear().updateYearAndSeason(board);
        assertEquals(Seasons.SUMMER, board.getYear().getCurrentSeason());

        inventory.getEnergyStock().removeEnergy(List.of(water, wind, fire, earth));

        assertFalse(chooseGoToTheNextSeason.choose(player));
    }

    @Test
    void chooseAutomnFalse() {
        board.setCurrentCursor(10);
        board.getYear().updateYearAndSeason(board);
        assertEquals(Seasons.AUTUMN, board.getYear().getCurrentSeason());

        inventory.getEnergyStock().removeEnergy(List.of(water, wind, fire, earth));

        assertFalse(chooseGoToTheNextSeason.choose(player));
    }

}