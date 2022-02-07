package core.ia.strategy.choose.to_keep_drawn_card;

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

import static org.junit.jupiter.api.Assertions.assertTrue;

class ChooseToKeepDrawnCardFirstTest {
    private final Config config = new Config();
    private final GameController controller = new GameController();
    private final Board board = controller.getBoard();
    Player player;
    private FacadeIA facadeIA;
    private ChooseToKeepDrawnCardFirst chooseToKeepDrawnCardFirst;
    private Card card1;

    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = players[0];

        facadeIA = new FacadeIA(board, player, config);
        chooseToKeepDrawnCardFirst = new ChooseToKeepDrawnCardFirst();

        card1 = Mockito.mock(Card.class);

    }

    @Test
    void choose() {
        assertTrue(chooseToKeepDrawnCardFirst.choose(player, card1));
    }
}