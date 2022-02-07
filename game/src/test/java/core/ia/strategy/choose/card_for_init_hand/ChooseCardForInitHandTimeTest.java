package core.ia.strategy.choose.card_for_init_hand;

import core.board.Board;
import core.board.FacadeIA;
import core.board.enums.Energy;
import core.cards.Card;
import core.cards.effects.BottesTemporelles;
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
import static org.mockito.Mockito.when;

class ChooseCardForInitHandTimeTest {
    private final Config config = new Config();
    private final GameController controller = new GameController();
    private final Board board = controller.getBoard();
    private FacadeIA facadeIA;
    private ChooseCardForInitHandTime chooseCardForInitHand;
    private ArrayList<Card> listCards;
    private ArrayList<Card> res;
    private Player player;
    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;
    private Card card5;
    private Card card6;
    private Card card7;
    private Card card8;
    private Card card9;


    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = players[0];
        facadeIA = new FacadeIA(board, player, config );
        player.setFacadeIA(facadeIA);

        chooseCardForInitHand = new ChooseCardForInitHandTime();

        card1 = Mockito.mock(Card.class);
        card2 = Mockito.mock(Card.class);
        card3 = Mockito.mock(Card.class);
        card4 = Mockito.mock(BottesTemporelles.class);
        card5 = Mockito.mock(Card.class);
        card6 = Mockito.mock(Card.class);
        card7 = Mockito.mock(Card.class);
        card8 = Mockito.mock(Card.class);
        card9 = Mockito.mock(Card.class);

        when(card1.getCrystalCost()).thenReturn(0);
        when(card1.getEnergyCost()).thenReturn(List.of(Energy.FIRE, Energy.FIRE, Energy.FIRE));
        when(card2.getCrystalCost()).thenReturn(0);
        when(card2.getEnergyCost()).thenReturn(List.of(Energy.FIRE, Energy.FIRE, Energy.FIRE));
        when(card3.getCrystalCost()).thenReturn(0);
        when(card3.getEnergyCost()).thenReturn(List.of(Energy.FIRE, Energy.FIRE, Energy.FIRE));
        when(card4.getCrystalCost()).thenReturn(10);
        when(card4.getEnergyCost()).thenReturn(List.of(Energy.FIRE, Energy.FIRE, Energy.FIRE));
        when(card5.getCrystalCost()).thenReturn(0);
        when(card5.getEnergyCost()).thenReturn(List.of(Energy.FIRE, Energy.FIRE));
        when(card6.getCrystalCost()).thenReturn(0);
        when(card6.getEnergyCost()).thenReturn(List.of(Energy.WATER));
        when(card7.getCrystalCost()).thenReturn(0);
        when(card7.getEnergyCost()).thenReturn(List.of(Energy.FIRE));
        when(card8.getCrystalCost()).thenReturn(10);
        when(card8.getEnergyCost()).thenReturn(new ArrayList<>());
        when(card9.getCrystalCost()).thenReturn(0);
        when(card9.getEnergyCost()).thenReturn(new ArrayList<>());


    }

    @Test
    void choose() {
        listCards = new ArrayList<>(List.of(card1, card2, card3, card4, card5, card6, card7, card8, card9));

        res = new ArrayList<>(List.of(card4, card1, card2, card3, card5, card6, card7, card8, card9));

        Card[][] cards = new Card[config.getNbYears()][config.getNbCardsPerYear()];
        for (int i = 0; i < config.getNbYears(); i++) {
            for (int j = 0; j < config.getNbCardsPerYear(); j++) {
                cards[i][j] = res.get(i * 3 + j);
            }
        }
        assertTrue(equal(cards, chooseCardForInitHand.choose(player, listCards, config)));
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