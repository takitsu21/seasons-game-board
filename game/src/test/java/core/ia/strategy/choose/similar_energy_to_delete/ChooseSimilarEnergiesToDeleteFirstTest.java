package core.ia.strategy.choose.similar_energy_to_delete;

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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class ChooseSimilarEnergiesToDeleteFirstTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board = controller.getBoard();
    private FacadeIA facadeIA;
    private ChooseSimilarEnergiesToDeleteFirst chooseSimilarEnergiesToDeleteFirst;
    private Player player;

    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        facadeIA = Mockito.mock(FacadeIA.class);
        player = players[0];
        player.setFacadeIA(facadeIA);

        chooseSimilarEnergiesToDeleteFirst = new ChooseSimilarEnergiesToDeleteFirst();


    }

    @Test
    void choose() {
        when(facadeIA.getListOfMultipleEnergy(2)).thenReturn(List.of(List.of(Energy.FIRE, Energy.FIRE), List.of(Energy.WATER, Energy.WATER)));
        assertEquals(List.of(Energy.FIRE, Energy.FIRE), chooseSimilarEnergiesToDeleteFirst.choose(player, 2));
    }

    @Test
    void chooseNull() {
        when(facadeIA.getListOfMultipleEnergy(2)).thenReturn(new ArrayList<>());
        List<Energy> emptyList = new ArrayList<>();
        assertEquals(emptyList,chooseSimilarEnergiesToDeleteFirst.choose(player, 2));
    }
}

