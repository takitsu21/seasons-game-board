package core.ia.strategy.choose.dice;

import core.board.Board;
import core.board.FacadeIA;
import core.dice.Dice;
import core.dice.DiceSet;
import core.dice.Face;
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

class ChooseDicePrefInvocTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board = controller.getBoard();
    private FacadeIA facadeIA;
    private ChooseDicePrefInvoc chooseDicePrefInvoc;
    private Player player;
    private Dice[] setOfDice;
    private Dice d1;
    private Dice d2;
    private Dice d3;
    private Dice d4;
    private Dice d5;


    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        facadeIA = Mockito.mock(FacadeIA.class);
        player = players[0];
        player.setFacadeIA(facadeIA);
        chooseDicePrefInvoc = new ChooseDicePrefInvoc();

        DiceSet diceSet = Mockito.mock(DiceSet.class);

        d1 = Mockito.mock(Dice.class);
        d2 = Mockito.mock(Dice.class);
        d3 = Mockito.mock(Dice.class);
        d4 = Mockito.mock(Dice.class);
        d5 = Mockito.mock(Dice.class);

        Face face1 = Mockito.mock(Face.class);
        Face face2 = Mockito.mock(Face.class);
        Face face3 = Mockito.mock(Face.class);
        Face face4 = Mockito.mock(Face.class);
        Face face5 = Mockito.mock(Face.class);


        when(d1.getCurrentFace()).thenReturn(face1);
        when(d2.getCurrentFace()).thenReturn(face2);
        when(d3.getCurrentFace()).thenReturn(face3);
        when(d4.getCurrentFace()).thenReturn(face4);
        when(d5.getCurrentFace()).thenReturn(face5);

        when(face1.isInvocationSup()).thenReturn(true);
        when(face2.isInvocationSup()).thenReturn(false);
        when(face3.isInvocationSup()).thenReturn(false);
        when(face4.isInvocationSup()).thenReturn(false);
        when(face5.isInvocationSup()).thenReturn(false);

        setOfDice = new Dice[]{d2, d1, d3, d4, d5};

        when(facadeIA.getDiceSet()).thenReturn(setOfDice);
    }

    @Test
    void choose() {
        assertEquals(d1, chooseDicePrefInvoc.choose(player));
    }


}