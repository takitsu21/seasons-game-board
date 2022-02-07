package core.ia.strategy.choose.card_to_summon;

import core.board.Board;
import core.board.FacadeIA;
import core.cards.Card;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.ia.strategy.choose.card_to_summon_for_free.ChooseCardToSummonForFreeRandom;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ChooseCardToSummonRandomTest {
    private final Config config = new Config();
    private final GameController controller = new GameController();
    Player player;
    private Board board;
    private FacadeIA facadeIA;
    private ChooseCardToSummonRandom chooseCardToSummonRandom;
    private Card card1;
    private Card card2;
    private Player[] players;


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
        board.initHand(player);

        chooseCardToSummonRandom = new ChooseCardToSummonRandom();
        //player.setChooseCardToSummon(chooseCardToSummonRandom);
        card1 = Mockito.mock(Card.class);
        card2 = Mockito.mock(Card.class);

//        board.initHand(players[0]);
        when(facadeIA.getSummonableCards()).thenReturn(List.of(card1));
    }

    @Test
    void choose() {
        board.getInventories().get(player).getHand().setCardsInHand(new ArrayList<>(List.of(card1)));
        assertEquals(card1, chooseCardToSummonRandom.choose(player));
    }
}