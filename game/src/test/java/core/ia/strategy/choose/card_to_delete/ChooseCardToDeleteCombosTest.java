package core.ia.strategy.choose.card_to_delete;

import core.board.Board;
import core.board.FacadeIA;
import core.cards.Card;
import core.cards.Deck;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.ia.inventory.Hand;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ChooseCardToDeleteCombosTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board;
    private FacadeIA facadeIA;
    private ChooseCardToDeleteCombos chooseCardToDeleteCombos;
    private Card card1, card2, card3;
    private Player player;
    private Player[] players;
    private Deck deck;
    private List<Card> cardsToChoose;

    @BeforeEach
    void setUp() {
        players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        controller.initGame(players, config);
        board = controller.getBoard();
        player = players[0];
        facadeIA = Mockito.mock(FacadeIA.class);
        player.setFacadeIA(facadeIA);

        chooseCardToDeleteCombos = new ChooseCardToDeleteCombos();
        deck = new Deck();
        card1 = deck.findCard(15);
        card2 = deck.findCard(11);
        card3 = deck.findCard(20);

        board.getInventories().get(players[0]).getInvocation().setInvocationPoints(1);

        cardsToChoose = new ArrayList<>(List.of(card1, card2, card3));

        Card[][] cards = new Card[config.getNbCardsPerYear()][config.getNbYears()];
        cards[0] = cardsToChoose.toArray(new Card[0]);
        board.getInventories().get(players[0]).setHand(new Hand(cards));
        when(facadeIA.getCardOnBoard()).thenReturn(cardsToChoose);
        when(facadeIA.getCardInHand()).thenReturn(cardsToChoose);
    }

    @Test
    void choose() {
        assertEquals(card3, chooseCardToDeleteCombos.choose(player));
    }

}
