package core.cards.effects;

import core.board.Year;
import core.cards.Card;
import core.cards.Deck;
import core.dice.Dice;
import core.game.GameController;
import core.game.TurnBasedGameLoop;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.ia.inventory.Inventory;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CubeRuniqueEolisEffectTest {
    private Card cubeRunique;
    private Player p1;
    private Player p2;

    private GameController controller;
    private TurnBasedGameLoop playerTurnLoop1;

    @BeforeEach
    void setUp() {
        Config config = new Config();
        Deck deck = new Deck();
        PlayerFactory playerFactory = new PlayerFactory(config);
        p1 = playerFactory.getPlayer(TypeAIPlayer.CHOOSE_FIRST, "IA1");

        Player[] playerList = new Player[]{p1};
        playerTurnLoop1 = new TurnBasedGameLoop(playerList, config);
        controller = playerTurnLoop1.getController();

        cubeRunique = deck.findCard(22);


        controller.initGame(playerList, config);
        controller.getBoard().initPLayersInventory();
        controller.getBoard().getInventories().get(p1).setCurrentDice(new Dice(0, new Year(config.getNbYears())));
        controller.getBoard().initHand(p1);
    }

    @Test
    void useTest() {
        Inventory inventory1 = controller.getBoard().getInventories().get(p1);

        inventory1.getHand().addCard(cubeRunique);

        inventory1.getInvocation().setInvocationPoints(2);
        inventory1.setCrystals(20);

        assertEquals(20, inventory1.getCrystals());

        cubeRunique.use(controller.getBoard(), p1);

        assertTrue(inventory1.summonCard(cubeRunique, controller.getBoard()));
        assertEquals(0, inventory1.getCrystals());
        assertEquals(0, inventory1.getPrestigePoints());
        inventory1.setPrestigePoints(inventory1.getPrestigePoints() + cubeRunique.getPrestigePointValue());
        assertEquals(30, inventory1.getPrestigePoints());

    }

    @Test
    void isMagicTest() {
        assertTrue(cubeRunique.isMagic());
    }

    @Test
    void isActivableTest() {
        assertFalse(cubeRunique.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(cubeRunique.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.END_GAME, cubeRunique.getEffectType());
    }
}
