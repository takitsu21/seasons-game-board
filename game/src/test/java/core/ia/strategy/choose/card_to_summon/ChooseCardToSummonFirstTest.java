package core.ia.strategy.choose.card_to_summon;

import core.board.Board;
import core.board.FacadeIA;
import core.cards.Card;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.ia.strategy.choose.card_to_summon_for_free.ChooseCardToSummonForFreeFirst;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ChooseCardToSummonFirstTest {
    private final Config config = new Config();
    private final GameController controller = new GameController();
    private Board board;
    private FacadeIA facadeIA;
    private ChooseCardToSummonFirst chooseCardToSummonFirst;
    private Card card1;
    private Card card2;
    private Player[] players;
    private Player player;


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
        chooseCardToSummonFirst = new ChooseCardToSummonFirst();
        //player.setChooseCardToSummon(chooseCardToSummonFirst);
        card1 = Mockito.mock(Card.class);
        card2 = Mockito.mock(Card.class);

        board.initHand(players[0]);
        when(facadeIA.getSummonableCards()).thenReturn(List.of(card1,card2));
    }

    @Test
    void choose() {
        board.getInventories().get(players[0]).getHand().setCardsInHand(new ArrayList<>(List.of(card1, card2)));
        assertEquals(card1, chooseCardToSummonFirst.choose(player));
    }
}