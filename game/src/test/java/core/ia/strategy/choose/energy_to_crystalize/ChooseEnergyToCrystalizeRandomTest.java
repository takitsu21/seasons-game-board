package core.ia.strategy.choose.energy_to_crystalize;

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
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ChooseEnergyToCrystalizeRandomTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board = controller.getBoard();
    private FacadeIA facadeIA;
    private ChooseEnergyToCrystalizeRandom chooseEnergyToCrystalizeRandom;
    private Player player;

    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        facadeIA = Mockito.mock(FacadeIA.class);
        player = players[0];
        player.setFacadeIA(facadeIA);
        chooseEnergyToCrystalizeRandom = new ChooseEnergyToCrystalizeRandom();

    }

    @Test
    void choose() {
        when(facadeIA.getEnergyStock(player)).thenReturn(new ArrayList<>() {{
            add(Energy.FIRE);
        }});
        assertEquals(Energy.FIRE, chooseEnergyToCrystalizeRandom.choose(player));
    }
}