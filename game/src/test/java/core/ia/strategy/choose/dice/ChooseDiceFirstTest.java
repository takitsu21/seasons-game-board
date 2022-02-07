package core.ia.strategy.choose.dice;

import core.board.Board;
import core.board.FacadeIA;
import core.board.Year;
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
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ChooseDiceFirstTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board = controller.getBoard();
    private FacadeIA facadeIA;
    private ChooseDiceFirst chooseDiceFirst;
    private Player player;

    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        facadeIA = Mockito.mock(FacadeIA.class);
        player = players[0];
        player.setFacadeIA(facadeIA);
        chooseDiceFirst = new ChooseDiceFirst();

        DiceSet diceSet = new DiceSet(0, new Year(3));
        Dice[] dices = diceSet.getSetOfDices();
        when(facadeIA.getDiceSet()).thenReturn(dices);
    }

    @Test
    void choose() {
        assertEquals(player.getFacadeIA().getDiceSet()[0], chooseDiceFirst.choose(player));
    }
}
