package core.ia.strategy.choose.go_to_the_previous_season;

import core.board.Board;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.ia.inventory.Inventory;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ChooseGoToThePreviousSeasonPrefInvocTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board;
    private ChooseGoToThePreviousSeasonPrefInvoc chooseGoToThePreviousSeason;
    private Player player;
    private Inventory inventory;


    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = players[0];
        chooseGoToThePreviousSeason = new ChooseGoToThePreviousSeasonPrefInvoc();
        controller.initGame(players, config);
        board=controller.getBoard();
        board.initHand(player);
        inventory=board.getInventories().get(player);
    }

    @Test
    void chooseCardEmpty() {
        inventory.getHand().setCardsInHand(new ArrayList<>());
        assertFalse(chooseGoToThePreviousSeason.choose(player));
    }

    @Test
    void chooseNotCardEmpty() {
        assertTrue(chooseGoToThePreviousSeason.choose(player));
    }

}