package core.cards.effects;

import core.board.Board;
import core.board.FacadeIA;
import core.cards.Card;
import core.cards.Deck;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.ia.inventory.Inventory;
import core.ia.strategy.choose.nb_deplacement_season.ChooseNbDeplacementSeasonRandom;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class BottesTemporellesEffectTest {
    private Card bottes;
    private Card figrim;
    private Card sablier;
    private Board board;
    private Player player;
    private Player player2;
    private Inventory inventory;
    private FacadeIA facadeIA;
    private GameController gameController;


    @BeforeEach
    void setUp() {
        Config config = new Config();
        Deck deck = new Deck();
        bottes = deck.findCard(7);
        figrim = deck.findCard(11);
        sablier = deck.findCard(27);
        GameController controller = new GameController();
        PlayerFactory playerFactory = new PlayerFactory(config);
        player = playerFactory.getPlayer(TypeAIPlayer.RANDOM, "random");
        player2 = playerFactory.getPlayer(TypeAIPlayer.RANDOM, "random");
        Player[] playerList = new Player[]{player, player2};
        controller.initGame(playerList, config);
        board = controller.getBoard();
        inventory = board.getInventories().get(player);
        gameController = new GameController();
        gameController.initGame(playerList, config);
        facadeIA = player.getFacadeIA();
        board.initHand(player);
        board.initHand(player2);

    }

    @Test
    void basicUseBottes() {
        board.getYear().setNbYear(2);
        assertEquals(2, board.getYear().getNbYear());
        ChooseNbDeplacementSeasonRandom chooseNbDeplacementSeasonRandom = Mockito.mock(ChooseNbDeplacementSeasonRandom.class);
        when(chooseNbDeplacementSeasonRandom.doChoose(player, -3, 3)).thenReturn(-1);
        player.setChooseNbDeplacementSeason(chooseNbDeplacementSeasonRandom);

        bottes.use(board, player);

        assertEquals(1, board.getYear().getNbYear());

    }

    @Test
    void useBottesFigrim() {
        board.setCurrentCursor(12);
        board.getYear().updateYearAndSeason(board);
        inventory.getInvocation().getCardsOnBoard().add(figrim);
        assertEquals(2, board.getYear().getNbYear());

        ChooseNbDeplacementSeasonRandom chooseNbDeplacementSeasonRandom = Mockito.mock(ChooseNbDeplacementSeasonRandom.class);
        when(chooseNbDeplacementSeasonRandom.doChoose(player, -3, 3)).thenReturn(-1);

        player.setChooseNbDeplacementSeason(chooseNbDeplacementSeasonRandom);

        bottes.use(board, player);

        assertEquals(1, board.getYear().getNbYear());
        assertEquals(0, inventory.getCrystals());

        when(chooseNbDeplacementSeasonRandom.doChoose(player, -3, 3)).thenReturn(1);
        player.setChooseNbDeplacementSeason(chooseNbDeplacementSeasonRandom);
        board.getInventories().get(player2).setCrystals(1);

        bottes.use(board, player);

        assertEquals(2, board.getYear().getNbYear());
        assertEquals(1, inventory.getCrystals());
        assertEquals(0, board.getInventories().get(player2).getCrystals());
    }

    @Test
    void useBottesSablier() {
        board.getYear().setNbYear(2);
        inventory.getInvocation().getCardsOnBoard().add(sablier);
        assertEquals(2, board.getYear().getNbYear());
        ChooseNbDeplacementSeasonRandom chooseNbDeplacementSeasonRandom = Mockito.mock(ChooseNbDeplacementSeasonRandom.class);
        when(chooseNbDeplacementSeasonRandom.choose(player, -3, 3)).thenReturn(-1);
        player.setChooseNbDeplacementSeason(chooseNbDeplacementSeasonRandom);
        bottes.use(board, player);

        assertEquals(1, board.getYear().getNbYear());

    }


    @Test
    void isMagic() {
        assertTrue(bottes.isMagic());
    }
}
