package core.ia.strategy.choose.cards_to_activate;

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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ChooseCardsToActivateFirstTest {
    private final Config config = new Config();
    private final GameController controller = new GameController();
    private final Board board = controller.getBoard();
    private FacadeIA facadeIA;
    private ChooseCardsToActivateFirst chooseCardToActivateFirst;
    private Card card1;
    private Card card2;
    private Player player;


    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        facadeIA = Mockito.mock(FacadeIA.class);
        player = players[0];
        player.setFacadeIA(facadeIA);

        chooseCardToActivateFirst = new ChooseCardsToActivateFirst();

        card1 = Mockito.mock(Card.class);
        card2 = Mockito.mock(Card.class);

    }

    @Test
    void choose() {
        when(facadeIA.getActivableCard()).thenReturn(List.of(card1, card2));
        assertEquals(card1, chooseCardToActivateFirst.choose(player));
    }
}