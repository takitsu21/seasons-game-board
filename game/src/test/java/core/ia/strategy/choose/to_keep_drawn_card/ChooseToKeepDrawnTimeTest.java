package core.ia.strategy.choose.to_keep_drawn_card;

import core.board.Board;
import core.board.FacadeIA;
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChooseToKeepDrawnTimeTest {
    private final Config config = new Config();
    private final GameController controller = new GameController();
    private final Board board = controller.getBoard();
    Player player;
    private FacadeIA facadeIA;
    private ChooseToKeepDrawnCardTime chooseToKeepDrawnCard;
    private Card card1;

    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = players[0];

        facadeIA = new FacadeIA(board, player, config);
        chooseToKeepDrawnCard = new ChooseToKeepDrawnCardTime();

    }

    @Test
    void chooseTrue() {
        card1 = Mockito.mock(BottesTemporelles.class);

        assertTrue(chooseToKeepDrawnCard.choose(player, card1));
    }

    @Test
    void chooseFalse() {
        card1 = Mockito.mock(Card.class);

        assertFalse(chooseToKeepDrawnCard.choose(player, card1));
    }
}