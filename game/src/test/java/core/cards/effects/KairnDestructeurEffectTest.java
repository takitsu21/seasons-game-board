package core.cards.effects;

import core.board.Year;
import core.board.enums.Energy;
import core.cards.Card;
import core.cards.Deck;
import core.dice.Dice;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.PlayerTurnLoop;
import core.ia.TypeAIPlayer;
import core.ia.inventory.Inventory;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class KairnDestructeurEffectTest {
    private Deck deck;
    private Player p1;
    private Player p2;
    private Player p3;
    private Card kairn;
    private GameController controller;
    private PlayerTurnLoop playerTurnLoop;

    @BeforeEach
    void setUp() {
        Config config = new Config();
        controller = new GameController();
        Player[] playerList = App.initPlayer(3, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        playerList[0]=playerFactory.getPlayer(TypeAIPlayer.CHOOSE_FIRST, "FIRST");
        p1 = playerList[0];
        p2 = playerList[1];
        p3 = playerList[2];

        controller.initGame(playerList, config);
        controller.getBoard().initPLayersInventory();
        controller.getBoard().getInventories().get(p1).setCurrentDice(new Dice(0, new Year(config.getNbYears())));
        controller.getBoard().getInventories().get(p2).setCurrentDice(new Dice(0, new Year(config.getNbYears())));
        controller.getBoard().getInventories().get(p3).setCurrentDice(new Dice(0, new Year(config.getNbYears())));
        playerTurnLoop = new PlayerTurnLoop(p1, controller);

        controller.getBoard().initHand(p1);
        controller.getBoard().initHand(p2);
        controller.getBoard().initHand(p3);

        deck = new Deck();
        kairn = deck.findCard(16);
    }

    @Test
    void kairnEffect() {
        Inventory inventory1 = controller.getBoard().getInventories().get(p1);
        Inventory inventory2 = controller.getBoard().getInventories().get(p2);
        Inventory inventory3 = controller.getBoard().getInventories().get(p3);
        inventory2.setCrystals(30);
        inventory3.setCrystals(30);

        inventory1.getHand().addCard(kairn);

        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.FIRE, null);

        inventory1.getInvocation().setInvocationPoints(1);


        assertTrue(inventory1.summonCard(kairn, controller.getBoard()));

        for (Player p : controller.getBoard().getPlayers()) {
            if (!p.equals(p1)) {
                assertEquals(30, controller.getBoard().getInventories().get(p).getCrystals());
            }
        }
        playerTurnLoop.activateCard();
        for (Player p : controller.getBoard().getPlayers()) {
            if (!p.equals(p1)) {
                assertEquals(26, controller.getBoard().getInventories().get(p).getCrystals());
            }
        }
    }

    @Test
    void kairnEffectWithEnergyStockEmpty() {
        Inventory inventory1 = controller.getBoard().getInventories().get(p1);

        Inventory inventory2 = controller.getBoard().getInventories().get(p2);
        Inventory inventory3 = controller.getBoard().getInventories().get(p3);
        inventory2.setCrystals(30);
        inventory3.setCrystals(30);

        inventory1.getHand().addCard(kairn);

        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);

        inventory1.getInvocation().setInvocationPoints(2);

        assertTrue(inventory1.summonCard(kairn, controller.getBoard()));

        playerTurnLoop.activateCard();

        assertEquals(new ArrayList<>(), inventory1.getEnergyStock().getEnergyStock());

        for (Player p : controller.getBoard().getPlayers()) {
            if (!p.equals(p1)) {
                assertEquals(30, controller.getBoard().getInventories().get(p).getCrystals());
            }
        }
    }

    @Test
    void isMagic() {
        assertFalse(kairn.isMagic());
    }

    @Test
    void isActivableTest() {
        assertTrue(kairn.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(kairn.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.NONE, kairn.getEffectType());
    }
}
