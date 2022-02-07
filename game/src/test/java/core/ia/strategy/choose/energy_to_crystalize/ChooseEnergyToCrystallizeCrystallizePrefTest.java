package core.ia.strategy.choose.energy_to_crystalize;

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

class ChooseEnergyToCrystallizeCrystallizePrefTest {
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
        player.setChooseEnergyToCrystallize(new ChooseEnergyToCrystallizeCrystallizePref());

    }

    @Test
    void chooseTest() {
        controller.getBoard().getInventories().get(player).getEnergyStock().addEnergy(Energy.EARTH);
        controller.getBoard().getInventories().get(player).getEnergyStock().addEnergy(Energy.WATER);
        controller.getBoard().getInventories().get(player).getEnergyStock().addEnergy(Energy.WIND);
        controller.getBoard().getInventories().get(player).getEnergyStock().addEnergy(Energy.FIRE);
        Energy energy = player.chooseEnergyToCrystalize();
        assertEquals(Energy.EARTH, energy);
    }
}