package core.cards.effects;

import core.board.Year;
import core.board.enums.Energy;
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

class CoffretMerveilleuxEffectTest {
    private Card coffret;
    private Player p1;
    private Player p2;
    private Player p3;

    private GameController controller;
    private TurnBasedGameLoop playerTurnLoop1;

    @BeforeEach
    void setUp() {
        Config config = new Config();
        Deck deck = new Deck();

        coffret = deck.findCard(13);
        PlayerFactory playerFactory = new PlayerFactory(config);
        p1 = playerFactory.getPlayer(TypeAIPlayer.CHOOSE_FIRST, "IA1");
        p2 = playerFactory.getPlayer(TypeAIPlayer.CHOOSE_FIRST, "IA2");
        p3 = playerFactory.getPlayer(TypeAIPlayer.CHOOSE_FIRST, "IA3");
        Player[] playerList = new Player[]{p1, p2, p3};
        playerTurnLoop1 = new TurnBasedGameLoop(playerList, config);
        controller = playerTurnLoop1.getController();


        controller.initGame(playerList, config);
        controller.getBoard().initPLayersInventory();

        controller.getBoard().initHand(p1);
        controller.getBoard().initHand(p2);
        controller.getBoard().initHand(p3);

        controller.getBoard().getInventories().get(p1).setCurrentDice(new Dice(0, new Year(config.getNbYears())));
        controller.getBoard().getInventories().get(p2).setCurrentDice(new Dice(0, new Year(config.getNbYears())));
        controller.getBoard().getInventories().get(p3).setCurrentDice(new Dice(0, new Year(config.getNbYears())));
    }

    @Test
    void useTest() {
        Inventory inventory1 = controller.getBoard().getInventories().get(p1);
        Inventory inventory2 = controller.getBoard().getInventories().get(p2);
        Inventory inventory3 = controller.getBoard().getInventories().get(p3);

        inventory1.getHand().addCard(coffret);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.FIRE, null);
        inventory1.getInvocation().setInvocationPoints(2);


        inventory3.getHand().addCard(coffret);
        inventory3.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory3.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory3.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory3.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory3.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory3.getEnergyStock().addEnergy(Energy.FIRE, null);
        inventory3.getInvocation().setInvocationPoints(2);

        assertTrue(inventory1.summonCard(coffret, controller.getBoard()));
        assertTrue(inventory3.summonCard(coffret, controller.getBoard()));

        assertEquals(0, inventory1.getCrystals());
        assertEquals(0, inventory2.getCrystals());
        assertEquals(0, inventory3.getCrystals());

        playerTurnLoop1.useEndOfTurnPermanentCard();

        assertEquals(3, inventory1.getCrystals());
        assertEquals(0, inventory2.getCrystals());
        assertEquals(3, inventory3.getCrystals());

    }

    @Test
    void isMagic() {
        assertTrue(coffret.isMagic());
    }

    @Test
    void isActivableTest() {
        assertFalse(coffret.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(coffret.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.END_TURN, coffret.getEffectType());
    }
}
