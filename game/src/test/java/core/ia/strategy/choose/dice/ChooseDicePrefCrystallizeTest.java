package core.ia.strategy.choose.dice;

import core.board.Board;
import core.dice.Dice;
import core.dice.DiceSet;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import util.Util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;

class ChooseDicePrefCrystallizeTest {
    Dice fstDice;
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board;
    private Player player;

    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(4, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = players[0];
        player.setChooseDice(new ChooseDicePrefCrystallize());
        controller.initGame(players, config);
        board = controller.getBoard();
        board.initDiceSet();
        fstDice = board.getDices().getSetOfDices()[0];
    }

    @Test
    void chooseDiceWithCrystallizeTest() {
        for (int i = 0; i < 6; i++) {
            fstDice.setCurrentFace(i);
            if (fstDice.getCurrentFace().isCrystalize()) {
                board.getDices().getSetOfDices()[0].setCurrentFace(i);
                break;
            }
        }

        Dice choosenDice = player.chooseDice();
        assertTrue(choosenDice.getCurrentFace().isCrystalize());
    }

    @Test
    void chooseDiceWithoutCrsytallize() {
        DiceSet diceSet = board.getDices();
        for (Dice dice : diceSet.getSetOfDices()) {
            int i = 0;
            while (dice.getCurrentFace().isCrystalize()) {
                dice.setCurrentFace(i);
            }
        }

        MockedStatic<Util> utilMockedStatic = mockStatic(Util.class);
        utilMockedStatic.when(() -> Util.getNextInt(diceSet.getSetOfDices().length)).thenReturn(0);

        assertEquals(board.getDices().getDice(0), player.chooseDice());
    }
}