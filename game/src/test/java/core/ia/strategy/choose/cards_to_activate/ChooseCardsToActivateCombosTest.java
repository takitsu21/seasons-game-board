package core.ia.strategy.choose.cards_to_activate;

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
import static org.junit.jupiter.api.Assertions.assertNull;

public class ChooseCardsToActivateCombosTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board;
    private FacadeIA facadeIA;
    private ChooseCardsToActivateCombos chooseCardsToActivateCombos;
    private Card card1, card2, card3, card4;
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

        chooseCardsToActivateCombos = new ChooseCardsToActivateCombos();
        deck = new Deck();
        card1 = deck.findCard(13);
        card2 = deck.findCard(11);
        card3 = deck.findCard(16);
        card4 = deck.findCard(15);
        board.initHand(players[0]);
    }

    @Test
    void choose() {
        board.getInventories().get(players[0]).getHand().setCardsInHand(new ArrayList<>(List.of(card1, card2)));
        board.getInventories().get(players[0]).getInvocation().getCardsOnBoard().add(card4);
        board.getInventories().get(players[0]).getInvocation().getCardsOnBoard().add(card3);

        Card card = chooseCardsToActivateCombos.choose(player);
        assertNull(card);
    }
}
