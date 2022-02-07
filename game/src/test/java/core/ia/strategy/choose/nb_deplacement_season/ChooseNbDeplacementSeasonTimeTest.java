package core.ia.strategy.choose.nb_deplacement_season;

import core.board.Board;
import core.board.FacadeIA;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.ia.inventory.Inventory;
import core.ia.strategy.choose.go_to_the_next_season.ChooseGoToTheNextSeason;
import core.ia.strategy.choose.go_to_the_next_season.ChooseGoToTheNextSeasonFirst;
import core.ia.strategy.choose.go_to_the_next_season.ChooseGoToTheNextSeasonPrefCrystallize;
import core.ia.strategy.choose.go_to_the_next_season.ChooseGoToTheNextSeasonPrefInvoc;
import core.ia.strategy.choose.go_to_the_previous_season.ChooseGoToThePreviousSeason;
import core.ia.strategy.choose.go_to_the_previous_season.ChooseGoToThePreviousSeasonFirst;
import core.ia.strategy.choose.go_to_the_previous_season.ChooseGoToThePreviousSeasonPrefCrystallize;
import core.ia.strategy.choose.go_to_the_previous_season.ChooseGoToThePreviousSeasonPrefInvoc;
import core.ia.strategy.choose.stay_in_the_season.ChooseStayInTheSeason;
import core.ia.strategy.choose.stay_in_the_season.ChooseStayInTheSeasonFirst;
import core.ia.strategy.choose.stay_in_the_season.ChooseStayInTheSeasonPrefCrystallize;
import core.ia.strategy.choose.stay_in_the_season.ChooseStayInTheSeasonPrefInvoc;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ChooseNbDeplacementSeasonTimeTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board;
    private FacadeIA facadeIA;
    private ChooseNbDeplacementSeasonTime chooseNbDeplacementSeason;
    private ChooseStayInTheSeason chooseStayInTheSeason=Mockito.mock(ChooseStayInTheSeason.class);
    private ChooseGoToTheNextSeason chooseGoToTheNextSeason=Mockito.mock(ChooseGoToTheNextSeason.class);
    private ChooseGoToThePreviousSeason chooseGoToThePreviousSeason=Mockito.mock(ChooseGoToThePreviousSeason.class);
    private Player player;
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = players[0];

        controller.initGame(players, config);
        board=controller.getBoard();

        facadeIA = new FacadeIA(board, player, config );
        player.setFacadeIA(facadeIA);

        board.initHand(player);
        inventory=board.getInventories().get(player);

        chooseNbDeplacementSeason = new ChooseNbDeplacementSeasonTime();

        player.setChooseStayInTheSeason(chooseStayInTheSeason);
        player.setChooseGoToTheNextSeason(chooseGoToTheNextSeason);
        player.setChooseGoToThePreviousSeason(chooseGoToThePreviousSeason);

    }

    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "1, 1",
            "2, -1",
            "3, 1",
            "4, 1",
            "5, -1",
            "6, 1",
    })
    void chooseStayInTheSeason(int cursor, int nb) {
        board.setCurrentCursor(cursor);
        board.getYear().updateYearAndSeason(board);
        when(chooseStayInTheSeason.doChoose(player)).thenReturn(true);

        assertEquals(nb, chooseNbDeplacementSeason.choose(player, -3, 3));
    }

    @ParameterizedTest
    @CsvSource({
            "0, 3",
            "1, 2",
            "2, 1",
            "3, 3",
            "4, 2",
            "5, 1",
            "6, 3",
    })
    void chooseGoToTheNextSeasonCursor(int cursor, int nb) {
        board.setCurrentCursor(cursor);
        board.getYear().updateYearAndSeason(board);
        when(chooseStayInTheSeason.doChoose(player)).thenReturn(false);
        when(chooseGoToTheNextSeason.doChoose(player)).thenReturn(true);


        assertEquals(nb, chooseNbDeplacementSeason.choose(player, -3, 3));
    }

    @ParameterizedTest
    @CsvSource({
            "0, -1",
            "1, -2",
            "2, -3",
            "3, -1",
            "4, -2",
            "5, -3",
            "6, -1",
    })
    void chooseGoToThePreviousSeason(int cursor, int nb) {
        board.setCurrentCursor(cursor);
        board.getYear().updateYearAndSeason(board);
        when(chooseStayInTheSeason.doChoose(player)).thenReturn(false);
        when(chooseGoToTheNextSeason.doChoose(player)).thenReturn(false);
        when(chooseGoToThePreviousSeason.doChoose(player)).thenReturn(true);

        assertEquals(nb, chooseNbDeplacementSeason.choose(player, -3, 3));
    }


}