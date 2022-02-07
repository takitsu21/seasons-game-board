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

class ChooseCardsToActivateRandomTest {
    private final Config config = new Config();
    private final GameController controller = new GameController();
    private final Board board = controller.getBoard();
    private FacadeIA facadeIA;
    private ChooseCardsToActivateRandom chooseCardToActivateRandom;
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
        chooseCardToActivateRandom = new ChooseCardsToActivateRandom();

        card1 = Mockito.mock(Card.class);
        card2 = Mockito.mock(Card.class);

        when(facadeIA.getActivableCard()).thenReturn(List.of(card1));
    }

    @Test
    void choose() {
        assertEquals(card1, chooseCardToActivateRandom.choose(player));
    }
}