package core.dice;

import core.board.Year;
import core.exception.DiceException;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.PlayerTurnLoop;
import core.ia.TypeAIPlayer;
import core.ia.inventory.Inventory;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class DiceTest {
    private Dice dice;
    private Inventory inventory;
    private Player player;
    private PlayerTurnLoop playerTurnLoop;
    private Config config = new Config();


    @BeforeEach
    void setUp() {
        PlayerFactory playerFactory = new PlayerFactory(config);
        player = playerFactory.getPlayer(TypeAIPlayer.RANDOM, "Tester");
        GameController controller = new GameController();
        controller.initGame(new Player[]{player}, config);
        controller.getBoard().initPLayersInventory();
        dice = Mockito.mock(Dice.class);
        inventory = controller.getBoard().getInventories().get(player);
        inventory.setCurrentDice(dice);
        playerTurnLoop = new PlayerTurnLoop(player, controller);
    }

    @Test
    void actDiceInvocationTrue() {
        when(dice.getCurrentFace()).thenReturn(new Face(0, false, false, true, 0));
        inventory.setCurrentDice(dice);

        playerTurnLoop.actionDice();
        assertEquals(1, inventory.getInvocation().getInvocationPoints());

        inventory.getInvocation().setInvocationPoints(15);
        playerTurnLoop.actionDice();
        assertEquals(15, inventory.getInvocation().getInvocationPoints());


    }

    @Test
    void actDiceAddCrystals() {
        when(dice.getCurrentFace()).thenReturn(new Face(3, false, false, false, 0));
        playerTurnLoop.actionDice();
        assertEquals(3, inventory.getCrystals());
    }

    @Test
    void actDiceInvocationFalse() {
        when(dice.getCurrentFace()).thenReturn(new Face(0, false, false, false, 0));
        playerTurnLoop.actionDice();
        assertEquals(0, inventory.getInvocation().getInvocationPoints());
    }

    @Test
    void errorInitDiceFaces() {
        dice = new Dice(0, new Year(config.getNbYears()));
        assertThrows(DiceException.class, () -> {
            dice.initFaces(7, new Year(config.getNbYears()));
        });
    }

    @Test
    void errorInitDice() {
        String expectedMessage = "dé demandé non connu";
        dice = new Dice(6, new Year(config.getNbYears()));
        Exception exception = assertThrows(DiceException.class, () -> {
            dice.initFaces(6, new Year(config.getNbYears()));
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

//    @Test
//    public void actPointTest(){
//        d1.setCurrentFace(0);
//        playerTurnLoop.actionDice();
//        assertEquals(0,player.getPoints());
//        d1.setCurrentFace(4);
//        playerTurnLoop.actionDice();
//        assertEquals(3, player.getPoints());
//    }


}
