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

class LewisGrisemineEffectTest {
    private Card lewisGrisemine;
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
        p2 = playerFactory.getPlayer(TypeAIPlayer.CHOOSE_FIRST, "IA2");

        Player[] playerList = new Player[]{p1, p2};
        playerTurnLoop1 = new TurnBasedGameLoop(playerList, config);
        controller = playerTurnLoop1.getController();

        lewisGrisemine = deck.findCard(21);


        controller.initGame(playerList, config);
        controller.getBoard().initPLayersInventory();

        controller.getBoard().initHand(p1);
        controller.getBoard().initHand(p2);

        controller.getBoard().getInventories().get(p1).setCurrentDice(new Dice(0, new Year(config.getNbYears())));
        controller.getBoard().getInventories().get(p2).setCurrentDice(new Dice(0, new Year(config.getNbYears())));
    }

    @Test
    void useTest() {
        Inventory inventory1 = controller.getBoard().getInventories().get(p1);
        Inventory inventory2 = controller.getBoard().getInventories().get(p2);

        inventory1.getHand().addCard(lewisGrisemine);

        inventory1.getEnergyStock().addEnergy(Energy.FIRE, null);
        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory1.getInvocation().setInvocationPoints(2);

        inventory2.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory2.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory2.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory2.getEnergyStock().addEnergy(Energy.WIND, null);


        assertTrue(inventory1.summonCard(lewisGrisemine, controller.getBoard()));

        assertTrue(inventory1.getEnergyStock().getEnergyStock().isEmpty());

        lewisGrisemine.use(controller.getBoard(), p1);
        assertEquals(inventory1.getEnergyStock().getEnergyStock(), inventory2.getEnergyStock().getEnergyStock());

    }

    @Test
    void isMagic() {
        assertFalse(lewisGrisemine.isMagic());
    }

    @Test
    void isActivableTest() {
        assertFalse(lewisGrisemine.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(lewisGrisemine.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.NONE, lewisGrisemine.getEffectType());
    }
}
