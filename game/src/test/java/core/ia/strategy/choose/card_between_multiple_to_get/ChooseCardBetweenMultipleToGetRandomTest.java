package core.ia.strategy.choose.card_between_multiple_to_get;

import core.board.Board;
import core.board.FacadeIA;
import core.cards.Card;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.ia.strategy.choose.Context;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChooseCardBetweenMultipleToGetRandomTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board = controller.getBoard();
    private FacadeIA facadeIA;
    private ChooseCardBetweenMultipleToGetRandom chooseCardBetweenMultipleToGetRandom;
    private Card card1;
    private Player player;


    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = players[0];
        facadeIA = new FacadeIA(board, player, config);
        player.setFacadeIA(facadeIA);

        chooseCardBetweenMultipleToGetRandom = new ChooseCardBetweenMultipleToGetRandom(Context.everyTime, null);
        card1 = Mockito.mock(Card.class);

    }

    @Test
    void choose() {
        assertEquals(card1, chooseCardBetweenMultipleToGetRandom.choose(player, List.of(card1)));
    }
}