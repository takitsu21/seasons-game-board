package core.ia.strategy.choose.card_to_summon;

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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ChooseCardToSummonPrefCardPointsTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board;
    private FacadeIA facadeIA;
    private ChooseCardToSummonPrefCardPoints chooseCardToSummonPrefCardPoints;
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

        chooseCardToSummonPrefCardPoints = new ChooseCardToSummonPrefCardPoints();
        player.setChooseCardToSummon(chooseCardToSummonPrefCardPoints);

        card1 = Mockito.mock(Card.class);
        card2 = Mockito.mock(Card.class);
        when(card1.getPrestigePointValue()).thenReturn(1);
        when(card2.getPrestigePointValue()).thenReturn(30);
        when(card1.getCrystalCost()).thenReturn(0);
        when(card2.getCrystalCost()).thenReturn(0);
        when(card1.getEnergyCost()).thenReturn(List.of());
        when(card2.getEnergyCost()).thenReturn(List.of());

        board.getInventories().get(players[0]).getInvocation().setInvocationPoints(1);

        board.initHand(players[0]);

    }

    @Test
    void choose() {
        board.getInventories().get(players[0]).getHand().setCardsInHand(new ArrayList<>(List.of(card1, card2)));
        assertEquals(card2, chooseCardToSummonPrefCardPoints.choose(player));
    }

}
