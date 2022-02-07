package core.ia.strategy.choose.card_come_back_in_hand;

import core.board.Board;
import core.board.FacadeIA;
import core.board.enums.Energy;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChooseCardComeBackInHandPrefInvocTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board = controller.getBoard();
    private FacadeIA facadeIA;
    private ChooseCardComeBackInHandPrefInvoc chooseCardComeBackInHandPrefInvoc;
    private Card card1, card2;
    private Player player;


    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = players[0];
        facadeIA = Mockito.mock(FacadeIA.class);
        player.setFacadeIA(facadeIA);

        chooseCardComeBackInHandPrefInvoc = new ChooseCardComeBackInHandPrefInvoc();
        card1 = Mockito.mock(Card.class);
        card2 = Mockito.mock(Card.class);

    }

    @Test
    void chooseEqual() {
        when(card1.getCrystalCost()).thenReturn(0);
        when(card2.getCrystalCost()).thenReturn(0);
        when(card1.getEnergyCost()).thenReturn(List.of());
        when(card2.getEnergyCost()).thenReturn(List.of());

        ArrayList<Card> list = new ArrayList<>(List.of(card1, card2));
        when(facadeIA.getMagicCardsOnBoard()).thenReturn(List.of(card1, card2));
        assertEquals(card1, chooseCardComeBackInHandPrefInvoc.choose(player));
    }

    @Test
    void chooseCrystalCost() {
        when(card1.getCrystalCost()).thenReturn(0);
        when(card2.getCrystalCost()).thenReturn(30);
        when(card1.getEnergyCost()).thenReturn(List.of());
        when(card2.getEnergyCost()).thenReturn(List.of());

        ArrayList<Card> list = new ArrayList<>(List.of(card1, card2));
        when(facadeIA.getMagicCardsOnBoard()).thenReturn(list);
        assertEquals(card1, chooseCardComeBackInHandPrefInvoc.choose(player));
    }

    @Test
    void chooseEnergyCost() {
        when(card1.getCrystalCost()).thenReturn(0);
        when(card2.getCrystalCost()).thenReturn(0);
        when(card1.getEnergyCost()).thenReturn(List.of());
        when(card2.getEnergyCost()).thenReturn(List.of(Energy.FIRE));

        ArrayList<Card> list = new ArrayList<>(List.of(card1, card2));
        when(facadeIA.getMagicCardsOnBoard()).thenReturn(list);

        assertEquals(card1, chooseCardComeBackInHandPrefInvoc.choose(player));
    }

    @Test
    void chooseCrystalCostAndEnergyCost() {
        when(card1.getCrystalCost()).thenReturn(10);
        when(card2.getCrystalCost()).thenReturn(0);
        when(card1.getEnergyCost()).thenReturn(List.of());
        when(card2.getEnergyCost()).thenReturn(List.of(Energy.FIRE));

        ArrayList<Card> list = new ArrayList<>(List.of(card1, card2));
        when(facadeIA.getMagicCardsOnBoard()).thenReturn(list);

        assertEquals(card1, chooseCardComeBackInHandPrefInvoc.choose(player));
    }
}