package core.ia.strategy.choose.card_to_summon_for_free;

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
import core.ia.strategy.choose.card_to_summon.ChooseCardToSummonTime;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ChooseCardToSummonForFreeTimeTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board;
    private FacadeIA facadeIA;
    private ChooseCardToSummonForFreeTime chooseCardToSummon;
    private Card card1, card2;
    private Player player;
    private Player[] players;


    @BeforeEach
    void setUp() {
        players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        controller.initGame(players, config);
        board = controller.getBoard();
        player = players[0];

        facadeIA = new FacadeIA(board, player, config );
        player.setFacadeIA(facadeIA);

        chooseCardToSummon = new ChooseCardToSummonForFreeTime();
        player.setChooseCardToSummonForFree(chooseCardToSummon);


        board.getInventories().get(players[0]).getInvocation().setInvocationPoints(1);

        board.initHand(players[0]);

        board.getInventories().get(players[0]).getEnergyStock().addEnergy(Energy.FIRE);
        board.getInventories().get(players[0]).addCrystals(100);

    }

    @Test
    void chooseEqual() {
        card1 = Mockito.mock(BottesTemporelles.class);
        card2 = Mockito.mock(BottesTemporelles.class);

        board.getInventories().get(players[0]).getHand().setCardsInHand(new ArrayList<>(List.of(card1, card2)));

        assertEquals(card2, chooseCardToSummon.choose(player));
    }

    @Test
    void choose1() {
        card1 = Mockito.mock(BottesTemporelles.class);
        card2 = Mockito.mock(Card.class);

        board.getInventories().get(players[0]).getHand().setCardsInHand(new ArrayList<>(List.of(card1, card2)));

        assertEquals(card1, chooseCardToSummon.choose(player));
    }

    @Test
    void choose2() {
        card1 = Mockito.mock(Card.class);
        card2 = Mockito.mock(BottesTemporelles.class);

        board.getInventories().get(players[0]).getHand().setCardsInHand(new ArrayList<>(List.of(card1, card2)));

        assertEquals(card2, chooseCardToSummon.choose(player));
    }

    @Test
    void chooseNull() {
        card1 = Mockito.mock(Card.class);
        card2 = Mockito.mock(Card.class);

        board.getInventories().get(players[0]).getHand().setCardsInHand(new ArrayList<>(List.of(card1, card2)));

        assertNull(chooseCardToSummon.choose(player));
    }

}