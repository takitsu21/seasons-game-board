package core.ia.strategy.choose.dice;

import core.board.Board;
import core.board.FacadeIA;
import core.board.enums.Energy;
import core.cards.Card;
import core.cards.Deck;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ChooseDIceComboTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board;
    private FacadeIA facadeIA;
    private ChooseDiceCombos chooseDiceCombos;
    private Player player;
    private Dice[] setOfDice;
    private Dice d1;
    private Dice d2;
    private Dice d3;
    private Dice d4;
    private Dice d5;
    private Deck deck;


    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        controller.initGame(players, config);
        board = controller.getBoard();
        player = players[0];
        facadeIA = Mockito.mock(FacadeIA.class);
        board.initPLayersInventory();
        board.initHand(player);


        deck = new Deck();
        Card card1 = deck.findCard(18);
        Card card2 = deck.findCard(13);
        Card card3 = deck.findCard(15);
        Card card4 = deck.findCard(7);
        Card card5 = deck.findCard(17);

        when(facadeIA.getCardInHand()).thenReturn(new ArrayList<>(List.of(
                card1, card2, card3, card4, card5
        )));
        player.setFacadeIA(facadeIA);
        chooseDiceCombos = new ChooseDiceCombos();

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


        when(face1.getEnergy()).thenReturn(null);
        when(face2.getEnergy()).thenReturn(new Energy[]{Energy.WATER, Energy.FIRE});
        when(face3.getEnergy()).thenReturn(null);
        when(face4.getEnergy()).thenReturn(new Energy[]{
                Energy.WIND
        });
        when(face5.getEnergy()).thenReturn(null);


        when(d1.getCurrentFace()).thenReturn(face1);
        when(d2.getCurrentFace()).thenReturn(face2);
        when(d3.getCurrentFace()).thenReturn(face3);
        when(d4.getCurrentFace()).thenReturn(face4);
        when(d5.getCurrentFace()).thenReturn(face5);

        setOfDice = new Dice[]{d2, d1, d3, d4, d5};

        when(facadeIA.getDiceSet()).thenReturn(setOfDice);
    }


    @Test
    void choose() {
        assertEquals(d2, chooseDiceCombos.choose(player));
    }
}
