package core.cards.effects;

import core.board.Board;
import core.cards.Card;
import core.cards.Deck;
import core.exception.CardException;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.ia.inventory.Inventory;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FigrimAvaricieuxEffectTest {
    private Card figrim;
    private Player p1;
    private Player p2;
    private Player p3;
    private Player p4;
    private Board board;
    private GameController controller = new GameController();

    @BeforeEach
    void setUp() {
        Config config = new Config();
        PlayerFactory playerFactory = new PlayerFactory(config);
        p1 = playerFactory.getPlayer(TypeAIPlayer.CHOOSE_FIRST, "IA1");
        p2 = playerFactory.getPlayer(TypeAIPlayer.CHOOSE_FIRST, "IA2");
        p3 = playerFactory.getPlayer(TypeAIPlayer.CHOOSE_FIRST, "IA3");
        p4 = playerFactory.getPlayer(TypeAIPlayer.CHOOSE_FIRST, "IA4");
        Player[] playerList = new Player[]{p1, p2, p3, p4};
        board = new Board(playerList, config);
        Deck deck = new Deck();
        figrim = deck.findCard("Figrim l'Avaricieux");

        controller.initGame(playerList, config);

        board.initHand(p1);
        board.initHand(p2);
        board.initHand(p3);
        board.initHand(p4);

    }

    @Test
    void figrimEffect() {
        Inventory inventory1 = board.getInventories().get(p1);
        Inventory inventory2 = board.getInventories().get(p2);
        Inventory inventory3 = board.getInventories().get(p3);
        Inventory inventory4 = board.getInventories().get(p4);
        inventory1.getInvocation().setInvocationPoints(10);


        inventory1.getHand().addCard(figrim);
        inventory1.setCrystals(10);
        inventory2.setCrystals(3);
        inventory3.setCrystals(4);
        inventory4.setCrystals(5);

        assertTrue(inventory1.summonCard(figrim, board));

        // on force le changement de saison
        board.setCurrentCursor(5);
        board.getYear().updateYearAndSeason(board); //figrim.use() est appelée là-dedans

        assertEquals(4, inventory1.getCrystals());
        assertEquals(2, inventory2.getCrystals());
        assertEquals(3, inventory3.getCrystals());
        assertEquals(4, inventory4.getCrystals());

    }

    @Test
    void figrimTest2() {
        Inventory inventory1 = board.getInventories().get(p1);
        Inventory inventory2 = board.getInventories().get(p2);
        Inventory inventory3 = board.getInventories().get(p3);
        Inventory inventory4 = board.getInventories().get(p4);
        inventory1.getInvocation().setInvocationPoints(10);


        inventory1.getHand().addCard(figrim);
        inventory1.setCrystals(10);
        inventory2.setCrystals(3);
        inventory3.setCrystals(0);
        inventory4.setCrystals(5);

        assertTrue(inventory1.summonCard(figrim, board));

        // on force le changement de saison
        board.setCurrentCursor(5);
        board.getYear().updateYearAndSeason(board); //figrim.use() est appelée là-dedans

        assertEquals(3, inventory1.getCrystals());
        assertEquals(2, inventory2.getCrystals());
        assertEquals(0, inventory3.getCrystals());
        assertEquals(4, inventory4.getCrystals());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4})
    void preparePlayer(int arg) {
        Board board = Mockito.mock(Board.class);
        when(board.getPlayers()).thenReturn(new Player[arg]);
        figrim.prepare(board);
        assertEquals((arg - 1) * 3, figrim.getCrystalCost());
    }


    @Test
    void prepareError() {
        Board board = Mockito.mock(Board.class);
        when(board.getPlayers()).thenReturn(new Player[1]);
        assertThrows(CardException.class, () -> {
            figrim.prepare(board);
        });
    }

    @Test
    void isMagicTest() {
        assertFalse(figrim.isMagic());
    }

    @Test
    void isActivableTest() {
        assertFalse(figrim.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(figrim.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.SEASON_CHANGE, figrim.getEffectType());
    }

}
