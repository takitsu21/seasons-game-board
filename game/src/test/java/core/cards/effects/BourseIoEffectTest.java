package core.cards.effects;

import core.board.enums.Energy;
import core.cards.Card;
import core.cards.Deck;
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

import static org.junit.jupiter.api.Assertions.*;


class BourseIoEffectTest {
    private Card io;
    private Player p1;

    private GameController controller;
    private PlayerTurnLoop playerTurnLoop1;

    @BeforeEach
    void setUp() {
        Config config = new Config();
        Deck deck = new Deck();
        io = deck.findCard("Bourse d'Io");
        controller = new GameController();
        Player[] playerList = App.initPlayer(2, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        playerList[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        p1 = playerList[0];

        controller.initGame(playerList, config);
        controller.getBoard().initPLayersInventory();
        controller.getBoard().initHand(p1);


        playerTurnLoop1 = new PlayerTurnLoop(p1, controller);

    }

    @Test
    void ioEffect1() {
        Inventory inventory1 = controller.getBoard().getInventories().get(p1);
        inventory1.getHand().addCard(io);

        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);


        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory1.getEnergyStock().addEnergy(Energy.FIRE, null);

        inventory1.getInvocation().setInvocationPoints(2);

        io.use(controller.getBoard(), p1);

        assertTrue(inventory1.summonCard(io, controller.getBoard()));
        assertEquals(4, inventory1.getEnergyStock().getEnergyStock().size());
        assertEquals(0, inventory1.getCrystals());
        int oldSize1 = inventory1.getEnergyStock().getEnergyStock().size();
        for (int i = 0; i < oldSize1; i++) {
            playerTurnLoop1.crystalize(p1.chooseEnergyToCrystalize());
        }
        assertEquals(8, inventory1.getCrystals());



    }

    @Test
    void ioEffect2(){
        Inventory inventory1 = controller.getBoard().getInventories().get(p1);

        inventory1.getEnergyStock().addEnergy(Energy.EARTH, null);
        inventory1.getEnergyStock().addEnergy(Energy.EARTH, null);
        inventory1.getEnergyStock().addEnergy(Energy.EARTH, null);

        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory1.getEnergyStock().addEnergy(Energy.FIRE, null);
        inventory1.getHand().addCard(io);
        inventory1.getInvocation().setInvocationPoints(2);

        assertTrue(inventory1.summonCard(io, controller.getBoard()));
        int oldSize2 = inventory1.getEnergyStock().getEnergyStock().size();

        assertEquals(0, inventory1.getCrystals());
        for (int i = 0; i < oldSize2; i++) {
            playerTurnLoop1.crystalize(p1.chooseEnergyToCrystalize());
        }
        assertEquals(12, inventory1.getCrystals());
    }

    @Test
    void isMagic() {
        assertTrue(io.isMagic());
    }

    @Test
    void isActivableTest() {
        assertFalse(io.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(io.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.CRYSTAL, io.getEffectType());
    }
}

