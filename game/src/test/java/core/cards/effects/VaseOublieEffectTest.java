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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VaseOublieEffectTest {
    private Deck deck;
    private Player p1;
    private Inventory inventory1;
    private Card vaseOublie;
    private Card magicCard1;
    private Card magicCard2;

    private GameController controller;

    @BeforeEach
    void setUp() {
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
        vaseOublie = deck.findCard(30);
        magicCard1 = deck.findCard(22);
        magicCard2 = deck.findCard(22);
    }

    @Test
    void useTestMaxEnergy() {
        inventory1.getHand().addCard(vaseOublie);
        inventory1.getHand().addCard(magicCard1);
        inventory1.getHand().addCard(magicCard2);
        assertEquals(6, inventory1.getCardsInHand().size());
        inventory1.setCrystals(40);

        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.EARTH, null);
        inventory1.getEnergyStock().addEnergy(Energy.FIRE, null);


        inventory1.getInvocation().setInvocationPoints(10);
        assertTrue(inventory1.summonCard(vaseOublie, controller.getBoard()));
        assertEquals(0, inventory1.getEnergyStock().size());


        assertTrue(inventory1.summonCard(magicCard1, controller.getBoard()));
        inventory1.triggerVaseAndBaton(controller.getBoard(), p1);
        assertTrue(inventory1.summonCard(magicCard2, controller.getBoard()));
        inventory1.triggerVaseAndBaton(controller.getBoard(), p1);

        assertEquals(2, inventory1.getEnergyStock().size());
    }

    @Test
    void useTestThrowEnergy() {
        inventory1.getHand().addCard(vaseOublie);
        inventory1.getHand().addCard(magicCard1);
        inventory1.getHand().addCard(magicCard2);
        assertEquals(6, inventory1.getCardsInHand().size());
        inventory1.setCrystals(40);

        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.EARTH, null);
        inventory1.getEnergyStock().addEnergy(Energy.FIRE, null);


        inventory1.getInvocation().setInvocationPoints(10);
        assertTrue(inventory1.summonCard(vaseOublie, controller.getBoard()));
        assertEquals(0, inventory1.getEnergyStock().size());

        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.EARTH, null);
        inventory1.getEnergyStock().addEnergy(Energy.FIRE, null);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.EARTH, null);
        inventory1.getEnergyStock().addEnergy(Energy.FIRE, null);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);

        assertTrue(inventory1.summonCard(magicCard1, controller.getBoard()));
        inventory1.triggerVaseAndBaton(controller.getBoard(), p1);
        assertTrue(inventory1.summonCard(magicCard2, controller.getBoard()));
        inventory1.triggerVaseAndBaton(controller.getBoard(), p1);


        assertEquals(7, inventory1.getEnergyStock().size());
    }

    @Test
    void isMagic() {
        assertTrue(vaseOublie.isMagic());
    }

    @Test
    void effectType() {
        assertEquals(EffectType.ON_SUMMON_CARD, vaseOublie.getEffectType());
    }
}
