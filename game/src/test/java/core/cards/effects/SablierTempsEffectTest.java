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

class SablierTempsEffectTest {
    private Deck deck;
    private Player p1;
    private Inventory inventory1;
    private Card sablierTemps;

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
        sablierTemps = deck.findCard(27);
    }

    @Test
    void useTest() {
        inventory1.getHand().addCard(sablierTemps);
        assertEquals(4, inventory1.getCardsInHand().size());

        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.FIRE, null);
        inventory1.getEnergyStock().addEnergy(Energy.EARTH, null);
        assertEquals(4, inventory1.getEnergyStock().getEnergyStock().size());


        inventory1.getInvocation().setInvocationPoints(2);
        assertTrue(inventory1.summonCard(sablierTemps, controller.getBoard()));
        assertEquals(0, inventory1.getEnergyStock().getEnergyStock().size());

        controller.getBoard().setCurrentCursor(5);
        controller.getBoard().getYear().updateYearAndSeason(controller.getBoard());
        assertEquals(1, inventory1.getEnergyStock().getEnergyStock().size());

    }

    @Test
    void isMagic() {
        assertTrue(sablierTemps.isMagic());
    }

    @Test
    void isActivableTest() {
        assertFalse(sablierTemps.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(sablierTemps.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.SEASON_CHANGE, sablierTemps.getEffectType());
    }
}
