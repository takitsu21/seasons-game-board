package core.ia.strategy.choose.similar_energy_to_delete;

import core.board.Board;
import core.board.FacadeIA;
import core.board.enums.Energy;
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

class ChooseSimilarEnergiesToDeletePrefInvocTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board;
    private FacadeIA facadeIA;
    private ChooseSimilarEnergiesToDeleteTime chooseSimilarEnergiesToDelete;
    private Player player;
    private Card card1;
    private Card card2;

    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        controller.initGame(players, config);
        board = controller.getBoard();
        player = players[0];

        facadeIA=new FacadeIA(board, player, config);
        player.setFacadeIA(facadeIA);

        board.initHand(player);

        chooseSimilarEnergiesToDelete = new ChooseSimilarEnergiesToDeleteTime();

        card1= Mockito.mock(Card.class);
        card2= Mockito.mock(Card.class);

        board.getInventories().get(player).getCardsInHand().clear();
        board.getInventories().get(player).getCardsInHand().addAll(List.of(card1, card2));

    }

    @Test
    void choose() {
        when(card1.getEnergyCost()).thenReturn(new ArrayList<>());
        when(card2.getEnergyCost()).thenReturn(new ArrayList<>());

        board.getInventories().get(player).getEnergyStock().getEnergyStock().add(Energy.FIRE);
        board.getInventories().get(player).getEnergyStock().getEnergyStock().add(Energy.FIRE);
        assertEquals(List.of(Energy.FIRE, Energy.FIRE), chooseSimilarEnergiesToDelete.choose(player, 2));
    }

    @Test
    void chooseEmpty() {
        when(card1.getEnergyCost()).thenReturn(new ArrayList<>(List.of(Energy.FIRE)));
        when(card2.getEnergyCost()).thenReturn(new ArrayList<>());

        board.getInventories().get(player).getEnergyStock().getEnergyStock().add(Energy.FIRE);
        board.getInventories().get(player).getEnergyStock().getEnergyStock().add(Energy.FIRE);

        assertEquals(new ArrayList<>(), chooseSimilarEnergiesToDelete.choose(player, 2));
    }

    @Test
    void chooseEmpty2() {
        assertEquals(new ArrayList<>(),chooseSimilarEnergiesToDelete.choose(player, 2));
    }
}

