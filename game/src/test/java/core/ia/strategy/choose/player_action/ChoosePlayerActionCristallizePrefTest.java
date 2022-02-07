package core.ia.strategy.choose.player_action;

import core.board.FacadeIA;
import core.game.App;
import core.game.GameController;
import core.ia.EnumPlayerAction;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.util.Config;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

class ChoosePlayerActionCristallizePrefTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Player player;
    private List<EnumPlayerAction> possibleAction;
    private FacadeIA facadeIA;


    @BeforeEach
    void setUp() {
        possibleAction = new ArrayList<>();

        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        controller.initGame(players, config);
        player = players[0];

        facadeIA = Mockito.mock(FacadeIA.class);

        player = players[0];
        player.setFacadeIA(facadeIA);
        player.setChoosePlayerAction(new ChoosePlayerActionCristallizePref());

        controller.getBoard().initPLayersInventory();
        controller.getBoard().initHand(player);

        when(facadeIA.getPossibleAction()).thenReturn(possibleAction);
    }


    @Test
    void chooseWithCrystallizePossibleTest() {
        // Adaptive is a bit special
        if (player.getTypeAIPlayer() == TypeAIPlayer.ADAPTATIVE) {
            player.setChoosePlayerAction(new ChoosePlayerActionCristallizePref());
        }
        possibleAction.add(EnumPlayerAction.CRYSTALLISE);
        possibleAction.add(EnumPlayerAction.BONUS);
        assertEquals(EnumPlayerAction.CRYSTALLISE, player.choosePlayerAction());
    }

    @Test
    void chooseWithoutCrystallizePossibleTest() {
        possibleAction.add(EnumPlayerAction.BONUS);
        possibleAction.add(EnumPlayerAction.ACTIVATE_CARD);
        assertNotEquals(EnumPlayerAction.CRYSTALLISE, player.choosePlayerAction());
    }

    @AfterEach
    void after(){
        possibleAction = new ArrayList<>();
    }

}