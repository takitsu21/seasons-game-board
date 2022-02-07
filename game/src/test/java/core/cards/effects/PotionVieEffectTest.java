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

class PotionVieEffectTest {
    private Deck deck;
    private Player p1;
    private Inventory inventory1;
    private Card potionVie;

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
        potionVie = deck.findCard(26);
    }

    @Test
    void useTestMaxEnergy() {
        inventory1.getHand().addCard(potionVie);
        assertEquals(4, inventory1.getCardsInHand().size());

        inventory1.getEnergyStock().addEnergy(Energy.EARTH, null);
        inventory1.getEnergyStock().addEnergy(Energy.EARTH, null);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);

        inventory1.getInvocation().setInvocationPoints(2);

        assertTrue(inventory1.summonCard(potionVie, controller.getBoard()));
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        assertEquals(0, inventory1.getCrystals());
        potionVie.use(controller.getBoard(), p1);
        assertEquals(28, inventory1.getCrystals());

    }

    @Test
    void isMagic() {
        assertTrue(potionVie.isMagic());
    }

    @Test
    void isActivable() {
        assertTrue(potionVie.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(potionVie.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.NONE, potionVie.getEffectType());
    }

}
