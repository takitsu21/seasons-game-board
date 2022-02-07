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

class HeaumeRagfieldEffectTest {
    private Card heaumeRagfield;
    private Card cardAdvantage;
    private Player p1;
    private Player p2;
    private Player p3;

    private GameController controller;
    private TurnBasedGameLoop playerTurnLoop1;
    private Config config;

    @BeforeEach
    void setUp() {
        config = new Config();
        Deck deck = new Deck();
        PlayerFactory playerFactory = new PlayerFactory(config);
        p1 = playerFactory.getPlayer(TypeAIPlayer.CHOOSE_FIRST, "IA1");
        p2 = playerFactory.getPlayer(TypeAIPlayer.CHOOSE_FIRST, "IA2");
        p3 = playerFactory.getPlayer(TypeAIPlayer.CHOOSE_FIRST, "IA3");
        Player[] playerList = new Player[]{p1, p2, p3};
        playerTurnLoop1 = new TurnBasedGameLoop(playerList, config);
        controller = playerTurnLoop1.getController();

        heaumeRagfield = deck.findCard(19);
        cardAdvantage = deck.findCard(10);


        controller.initGame(playerList, config);
        controller.getBoard().initPLayersInventory();
        controller.getBoard().getInventories().get(p1).setCurrentDice(new Dice(0, new Year(config.getNbYears())));
        controller.getBoard().getInventories().get(p2).setCurrentDice(new Dice(0, new Year(config.getNbYears())));
        controller.getBoard().getInventories().get(p3).setCurrentDice(new Dice(0, new Year(config.getNbYears())));

        controller.getBoard().initHand(p1);
        controller.getBoard().initHand(p2);
        controller.getBoard().initHand(p3);
    }

    @Test
    void useTest() {
        Inventory inventory1 = controller.getBoard().getInventories().get(p1);

        inventory1.getHand().addCard(heaumeRagfield);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory1.getInvocation().setInvocationPoints(2);
        inventory1.getInvocation().getCardsOnBoard().add(cardAdvantage);
        inventory1.getInvocation().incrementCurrentInvocations();

        assertTrue(inventory1.summonCard(heaumeRagfield, controller.getBoard()));

        assertEquals(0, inventory1.getCrystals());

        playerTurnLoop1.checkEndGamePermanentCard();

        Inventory inventory2 = controller.getBoard().getInventories().get(p2);

        assertEquals(20, inventory1.getCrystals());

    }

    @Test
    void useTestLower() {
        Inventory inventory1 = controller.getBoard().getInventories().get(p1);
        Inventory inventory2 = controller.getBoard().getInventories().get(p2);

        inventory1.getHand().addCard(heaumeRagfield);
        inventory2.getHand().addCard(cardAdvantage);
        inventory2.getHand().addCard(cardAdvantage);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory1.getInvocation().setInvocationPoints(2);


        assertTrue(inventory1.summonCard(heaumeRagfield, controller.getBoard()));

        assertEquals(0, inventory1.getCrystals());

        playerTurnLoop1.checkEndGamePermanentCard();

        assertEquals(0, inventory1.getCrystals());
    }

    @Test
    void isMagic() {
        assertTrue(heaumeRagfield.isMagic());
    }

    @Test
    void isActivableTest() {
        assertFalse(heaumeRagfield.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(heaumeRagfield.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.END_GAME, heaumeRagfield.getEffectType());
    }
}
