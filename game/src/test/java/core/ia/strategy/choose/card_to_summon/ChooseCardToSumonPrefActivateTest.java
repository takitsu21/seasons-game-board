package core.ia.strategy.choose.card_to_summon;

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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChooseCardToSumonPrefActivateTest {

    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board;
    private FacadeIA facadeIA;
    private ChooseCardToSummonPrefActivate chooseCardToSummonPrefActivate;
    private Card card1, card2, card3;
    private Player player;
    private Player[] players;
    private Deck deck;


    @BeforeEach
    void setUp() {
        players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        controller.initGame(players, config);
        board = controller.getBoard();
        player = players[0];

        facadeIA = new FacadeIA(board, player, config);
        player.setFacadeIA(facadeIA);

        chooseCardToSummonPrefActivate = new ChooseCardToSummonPrefActivate();
        deck = new Deck();
        card1 = deck.findCard(5);
        card2 = deck.findCard(17);
        card3 = deck.findCard(20);

        board.getInventories().get(players[0]).getInvocation().setInvocationPoints(1);
        board.getInventories().get(players[0]).setCrystals(3);

        board.initHand(players[0]);

    }

    @Test
    void choose() {
        board.getInventories().get(players[0]).getHand().setCardsInHand(new ArrayList<>(List.of(card1, card2, card3)));
        Card card = chooseCardToSummonPrefActivate.choose(player);
        assertEquals(card1, card);
    }

}
