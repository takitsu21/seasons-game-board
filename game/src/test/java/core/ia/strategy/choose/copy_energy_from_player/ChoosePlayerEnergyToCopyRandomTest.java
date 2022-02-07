package core.ia.strategy.choose.copy_energy_from_player;

import core.board.Board;
import core.board.FacadeIA;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ChoosePlayerEnergyToCopyRandomTest {
    private final Config config = new Config();
    private final GameController controller = new GameController();
    private final Board board = controller.getBoard();
    private FacadeIA facadeIA;
    private ChoosePlayerEnergyToCopyRandom choosePlayerRandom;
    private Player player1;
    private Player player;


    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(2, config);
        facadeIA = Mockito.mock(FacadeIA.class);

        choosePlayerRandom = new ChoosePlayerEnergyToCopyRandom();

        player = players[0];
        player1 = Mockito.mock(Player.class);
        player.setFacadeIA(facadeIA);

        when(facadeIA.getOtherPLayers()).thenReturn(List.of(player1));
    }

    @Test
    void choose() {
        assertEquals(player1, choosePlayerRandom.choose(player));
    }
}