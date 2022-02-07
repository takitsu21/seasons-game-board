package core.ia.strategy.choose.card_for_init_hand;

import core.board.Board;
import core.board.FacadeIA;
import core.cards.Card;
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

import static org.junit.jupiter.api.Assertions.assertTrue;

class ChooseCardForInitHandFirstTest {
    private final Config config = new Config();
    private final GameController controller = new GameController();
    private final Board board = controller.getBoard();
    private FacadeIA facadeIA;
    private ChooseCardForInitHandFirst chooseCardForInitHandFirst;
    private ArrayList<Card> listCards;
    private Player player;

    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = players[0];
        facadeIA = new FacadeIA(board, player, config );
        player.setFacadeIA(facadeIA);

        chooseCardForInitHandFirst = new ChooseCardForInitHandFirst();

        Card card1 = Mockito.mock(Card.class);
        Card card2 = Mockito.mock(Card.class);
        Card card3 = Mockito.mock(Card.class);
        Card card4 = Mockito.mock(Card.class);
        Card card5 = Mockito.mock(Card.class);
        Card card6 = Mockito.mock(Card.class);
        Card card7 = Mockito.mock(Card.class);
        Card card8 = Mockito.mock(Card.class);
        Card card9 = Mockito.mock(Card.class);

        listCards = new ArrayList<>(List.of(card1, card2, card3, card4, card5, card6, card7, card8, card9));

    }

    @Test
    void choose() {
        Card[][] cards = new Card[config.getNbYears()][config.getNbCardsPerYear()];
        for (int i = 0; i < config.getNbYears(); i++) {
            for (int j = 0; j < config.getNbCardsPerYear(); j++) {
                cards[i][j] = listCards.get(i * 3 + j);
            }
        }
        assertTrue(equal(cards, chooseCardForInitHandFirst.choose(player, listCards, config)));
    }

    private boolean equal(Card[][] cards, Card[][] choose) {
        for (int i = 0; i < config.getNbYears(); i++) {
            for (int j = 0; j < config.getNbCardsPerYear(); j++) {
                if (cards[i][j] != choose[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}