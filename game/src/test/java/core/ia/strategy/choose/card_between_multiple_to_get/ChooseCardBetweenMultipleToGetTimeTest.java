package core.ia.strategy.choose.card_between_multiple_to_get;

import core.board.Board;
import core.board.FacadeIA;
import core.board.enums.Energy;
import core.cards.Card;
import core.cards.effects.*;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.util.CardUtil;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ChooseCardBetweenMultipleToGetTimeTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board;
    private FacadeIA facadeIA;
    private ChooseCardBetweenMultipleToGetTime chooseCardBetweenMultipleToGet;
    private Card card1, card2;
    private Player player;

    @BeforeEach
    void setup() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = players[0];
        board=controller.getBoard();
        facadeIA = new FacadeIA(board, player, config );
        player.setFacadeIA(facadeIA);

        chooseCardBetweenMultipleToGet = new ChooseCardBetweenMultipleToGetTime();

        card1 = Mockito.mock(BottesTemporelles.class);
        card2 = Mockito.mock(Card.class);
    }

    @Test
    void choose1() {
        when(card2.getCrystalCost()).thenReturn(0);
        when(card2.getEnergyCost()).thenReturn(List.of());

        List<Card> list = new ArrayList<>(List.of(card1, card2));

        assertEquals(card1, chooseCardBetweenMultipleToGet.choose(player, list));
    }

    @Test
    void chooseNull() {
        card1 = Mockito.mock(Card.class);

        when(card2.getCrystalCost()).thenReturn(30);
        when(card2.getEnergyCost()).thenReturn(List.of());

        List<Card> list = new ArrayList<>(List.of(card1, card2));

        assertNull(chooseCardBetweenMultipleToGet.choose(player, list));
    }

}