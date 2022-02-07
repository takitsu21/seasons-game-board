package core.ia.strategy.choose.similar_energy_to_delete;

import core.board.Board;
import core.board.FacadeIA;
import core.board.enums.Energy;
import core.cards.Card;
import core.cards.Deck;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.ia.strategy.choose.card_to_summon.ChooseCardToSummonPrefInvoc;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ChooseSimilarEnergiesToDeleteTimeTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board;
    private FacadeIA facadeIA;
    private ChooseSimilarEnergiesToDeleteTime chooseSimilarEnergiesToDelete;
    private Player player;

    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.CHOOSE_FIRST, "IA");
        controller.initGame(players, config);
        board = controller.getBoard();
        player = players[0];

        facadeIA = new FacadeIA(board, player, config);
        player.setFacadeIA(facadeIA);

        board.initHand(player);
        board.setDeck(new Deck());

        chooseSimilarEnergiesToDelete = new ChooseSimilarEnergiesToDeleteTime();

        board.getInventories().get(player).getCardsInHand().clear();
        board.getInventories().get(player).getCardsInHand().add(board.getDeck().findCard(7));

    }

    @Test
    void choose() {
        board.getInventories().get(player).getEnergyStock().getEnergyStock().add(Energy.FIRE);
        board.getInventories().get(player).getEnergyStock().getEnergyStock().add(Energy.FIRE);
        assertEquals(List.of(Energy.FIRE, Energy.FIRE), chooseSimilarEnergiesToDelete.choose(player, 2));
    }

    @Test
    void chooseNull() {
        List<Energy> emptyList = new ArrayList<>();
        assertEquals(emptyList,chooseSimilarEnergiesToDelete.choose(player, 2));
    }
}

