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

class ChoosePlayerEnergyToCopyFirstTest {
    private final Config config = new Config();
    private final GameController controller = new GameController();
    private FacadeIA facadeIA;
    private ChoosePlayerEnergyToCopyFirst choosePlayerFirst;
    private Player player1;
    private Player player2;
    private Player player;


    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(3, config);
        facadeIA = Mockito.mock(FacadeIA.class);

        choosePlayerFirst = new ChoosePlayerEnergyToCopyFirst();

        player = players[0];
        player.setFacadeIA(facadeIA);
        player1 = Mockito.mock(Player.class);
        player2 = Mockito.mock(Player.class);

        when(player.getFacadeIA().getOtherPLayers()).thenReturn(List.of(player1, player2));
    }

    @Test
    void choose() {
        assertEquals(player1, choosePlayerFirst.choose(player));
    }
}