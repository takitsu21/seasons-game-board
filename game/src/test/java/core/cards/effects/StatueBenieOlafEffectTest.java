package core.cards.effects;

import core.board.Year;
import core.board.enums.Energy;
import core.cards.Card;
import core.cards.Deck;
import core.dice.Dice;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.ia.inventory.Inventory;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatueBenieOlafEffectTest {
    private Deck deck;
    private Player p1;
    private Inventory inventory1;
    private Card statueOlaf;

    private GameController controller;

    @BeforeEach
    public void setUp() {
        Config config = new Config();
        controller = new GameController();
        PlayerFactory playerFactory = new PlayerFactory(config);
        p1 = playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA1");

        Player[] playerList = new Player[]{p1};

        controller.initGame(playerList, config);
        controller.getBoard().initPLayersInventory();
        controller.getBoard().initHand(p1);
        controller.getBoard().getInventories().get(p1).setCurrentDice(new Dice(0, new Year(config.getNbYears())));
        inventory1 = controller.getBoard().getInventories().get(p1);

        deck = new Deck();
        statueOlaf = deck.findCard(29);
    }

    @Test
    void useTestMaxEnergy() {
        inventory1.getHand().addCard(statueOlaf);
        assertEquals(4, inventory1.getCardsInHand().size());

        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);

        inventory1.getInvocation().setInvocationPoints(2);
        assertEquals(0, inventory1.getCrystals());

        assertTrue(inventory1.summonCard(statueOlaf, controller.getBoard()));
        statueOlaf.use(controller.getBoard(), p1);
        assertEquals(20, inventory1.getCrystals());

    }

    @Test
    void isMagic() {
        assertTrue(statueOlaf.isMagic());
    }

    @Test
    void isActivableTest() {
        assertFalse(statueOlaf.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(statueOlaf.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.NONE, statueOlaf.getEffectType());
    }

}
