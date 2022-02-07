package core.ia.strategy.choose.card_come_back_in_hand;

import core.board.Board;
import core.board.FacadeIA;
import core.cards.Card;
import core.cards.Deck;
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

public class ChooseCardComeBackInHandPrefActivateTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board;
    private FacadeIA facadeIA;
    private ChooseCardComeBackInHandPrefActivate chooseCardComeBackInHandPrefActivate;
    private Card card1, card2, card3;
    private Player player;
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
        controller.getBoard().initHand(player);
        player.setFacadeIA(facadeIA);

        chooseCardComeBackInHandPrefActivate = new ChooseCardComeBackInHandPrefActivate();
        deck = new Deck();
        card1 = deck.findCard(7);
        card2 = deck.findCard(15);
        card3 = deck.findCard(24);

        board.getInventories().get(players[0]).getInvocation().setInvocationPoints(1);
        board.getInventories().get(players[0]).setCrystals(3);

    }

    @Test
    void chooseEqual() {
        ArrayList<Card> list = new ArrayList<>(List.of(card1, card2, card3));

        when(facadeIA.getMagicCardsOnBoard()).thenReturn(list);
        assertEquals(card1, chooseCardComeBackInHandPrefActivate.choose(player));
    }
}
