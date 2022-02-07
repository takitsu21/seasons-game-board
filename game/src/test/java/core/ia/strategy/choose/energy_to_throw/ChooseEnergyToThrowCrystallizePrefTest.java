package core.ia.strategy.choose.energy_to_throw;

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

import static org.junit.jupiter.api.Assertions.assertTrue;

class ChooseEnergyToThrowCrystallizePrefTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Player player;
    private FacadeIA facadeIA;


    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = players[0];
        controller.initGame(players, config);
        controller.getBoard().initPLayersInventory();
        facadeIA = new FacadeIA(controller.getBoard(), player, config);
        player.setFacadeIA(facadeIA);


        player = players[0];
        player.setChooseEnergyToThrow(new ChooseEnergyToThrowCrystallizePref());
        controller.getBoard().getInventories().get(player).getEnergyStock().addEnergy(Energy.FIRE);
        controller.getBoard().getInventories().get(player).getEnergyStock().addEnergy(Energy.EARTH);
        controller.getBoard().getInventories().get(player).getEnergyStock().addEnergy(Energy.WIND);

    }

    @Test
    void chooseTest() {
        Energy energy = player.chooseEnergyToThrow();
        assertTrue(energy == Energy.WIND || energy == Energy.WATER);
    }


}