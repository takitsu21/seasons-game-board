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

class MainFortuneEffectTest {
    private Card mainFortune;
    private Card io;
    private Player p1;
    private Player p2;
    private Player p3;

    private GameController controller;
    private TurnBasedGameLoop playerTurnLoop1;

    @BeforeEach
    void setUp() {
        Config config = new Config();
        Deck deck = new Deck();
        PlayerFactory playerFactory = new PlayerFactory(config);
        p1 = playerFactory.getPlayer(TypeAIPlayer.CHOOSE_FIRST, "IA1");
        p2 = playerFactory.getPlayer(TypeAIPlayer.CHOOSE_FIRST, "IA2");
        p3 = playerFactory.getPlayer(TypeAIPlayer.CHOOSE_FIRST, "IA3");
        Player[] playerList = new Player[]{p1, p2, p3};
        playerTurnLoop1 = new TurnBasedGameLoop(playerList, config);
        controller = playerTurnLoop1.getController();

        mainFortune = deck.findCard(20);
        io = deck.findCard("bourse d'io");


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

        inventory1.getHand().addCard(mainFortune);
        inventory1.getHand().addCard(io);


        inventory1.getEnergyStock().addEnergy(Energy.FIRE, null);
        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);

        inventory1.getEnergyStock().addEnergy(Energy.FIRE, null);
        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory1.getEnergyStock().addEnergy(Energy.EARTH, null);
        inventory1.getInvocation().setInvocationPoints(2);
        inventory1.setCrystals(3);


        assertTrue(inventory1.summonCard(mainFortune, controller.getBoard()));
        assertEquals(2, inventory1.getEnergyStock().getEnergyStock().size());
        assertTrue(inventory1.summonCard(io, controller.getBoard()));
        assertEquals(1, inventory1.getEnergyStock().getEnergyStock().size());
    }

    @Test
    void isMagic() {
        assertTrue(mainFortune.isMagic());
    }

    @Test
    void isActivableTest() {
        assertFalse(mainFortune.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(mainFortune.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.ON_SUMMON_CARD, mainFortune.getEffectType());
    }

}
