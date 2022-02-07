package core.ia.strategy.choose.player_action;

import core.board.Board;
import core.board.FacadeIA;
import core.cards.Card;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ChoosePlayerActionPrefInvocTest {

    private final Config config = new Config();
    private final GameController controller = new GameController();
    private Board board;
    private FacadeIA facadeIA;
    private ChoosePlayerActionPrefInvoc choosePlayerActionPrefInvoc;
    private Player player;
    private List<EnumPlayerAction> possibleAction;


    @BeforeEach
    void setUp() {
        board = controller.getBoard();
        possibleAction = new ArrayList<>();
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        facadeIA = Mockito.mock(FacadeIA.class);
        player = players[0];
        choosePlayerActionPrefInvoc = new ChoosePlayerActionPrefInvoc();

        when(facadeIA.getPossibleAction()).thenReturn(possibleAction);

        when(facadeIA.hasInvocationsLeft()).thenReturn(false);
        when(facadeIA.anyCardCostIsEnough()).thenReturn(true);
        when(facadeIA.getSummonableCards()).thenReturn(new ArrayList<>(List.of(Mockito.mock(Card.class))));

        player.setFacadeIA(facadeIA);
    }


    @Test
    void chooseWithSummonPossibleTest() {
        possibleAction.add(EnumPlayerAction.SUMMON);
        possibleAction.add(EnumPlayerAction.BONUS);
        assertEquals(EnumPlayerAction.SUMMON, choosePlayerActionPrefInvoc.choose(player));
    }

    @Test
    void chooseWithoutCrystallizePossibleTest() {
        possibleAction.add(EnumPlayerAction.BONUS);
        possibleAction.add(EnumPlayerAction.ACTIVATE_CARD);
        assertEquals(EnumPlayerAction.BONUS, choosePlayerActionPrefInvoc.choose(player));
    }

    @AfterEach
    void after(){
        possibleAction = new ArrayList<>();
    }
}