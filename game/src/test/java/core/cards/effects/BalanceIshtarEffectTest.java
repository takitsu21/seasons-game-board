package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.Card;
import core.cards.Deck;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.ia.inventory.Inventory;
import core.ia.strategy.choose.similar_energy_to_delete.ChooseSimilarEnergiesToDeleteRandom;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BalanceIshtarEffectTest {
    GameController controller;
    private Player player;
    private Board board;
    private Card ishtar;
    private Card io;
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        Config config = new Config();
        Deck deck = new Deck();
        ishtar = deck.findCard("Balance d'Ishtar");
        io = deck.findCard("Bourse d'Io");
        controller = new GameController();
        Player[] playerList = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        playerList[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = playerList[0];
        player.setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeleteRandom());
        controller.initGame(playerList, config);
        controller.getBoard().initPLayersInventory();
        board = controller.getBoard();
        inventory = board.getInventories().get(player);
        board.initHand(player);

    }

    @Test
    void ishtarEffectWithoutBourseIo() {
        inventory.getHand().addCard(ishtar);

        inventory.getEnergyStock().addEnergy(Energy.FIRE, null);
        inventory.getEnergyStock().addEnergy(Energy.FIRE, null);
        inventory.getEnergyStock().addEnergy(Energy.FIRE, null);
        inventory.getEnergyStock().addEnergy(Energy.FIRE, null);
        assertEquals(0, inventory.getCrystals());
        inventory.addCrystals(2);
        inventory.addInvocationPoints(1);
        assertTrue(inventory.getEnergyStock().hasEnoughEnergy(ishtar.getEnergyCost(), inventory.getInvocation().getCardsOnBoard()));
        assertTrue(inventory.summonCard(ishtar, board));
        board.getInventories().get(player).getCardsInHand().clear();
        ishtar.use(board, player);

        assertEquals(9, inventory.getCrystals());


    }

    @Test
    void ishtarEffectWithBourseIo() {
        inventory.getHand().addCard(ishtar);
        inventory.getHand().addCard(io);

        inventory.addInvocationPoints(2);
        inventory.addCrystals(2);

        inventory.getEnergyStock().addEnergy(Energy.FIRE, null);
        inventory.getEnergyStock().addEnergy(Energy.FIRE, null);
        inventory.getEnergyStock().addEnergy(Energy.FIRE, null);
        inventory.getEnergyStock().addEnergy(Energy.FIRE, null);

        assertFalse(inventory.summonCard(io, controller.getBoard()));
        inventory.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory.getEnergyStock().addEnergy(Energy.FIRE, null);

        assertTrue(inventory.getEnergyStock().hasEnoughEnergy(io.getEnergyCost(), inventory.getInvocation().getCardsOnBoard()));
        assertTrue(inventory.summonCard(io, controller.getBoard()));
        assertTrue(inventory.getEnergyStock().hasEnoughEnergy(ishtar.getEnergyCost(), inventory.getInvocation().getCardsOnBoard()));
        assertTrue(inventory.summonCard(ishtar, controller.getBoard()));
        assertEquals(0, inventory.getCrystals());
        ishtar.use(board, player);
        assertEquals(12, inventory.getCrystals());
    }

    @Test
    void isMagic() {
        assertTrue(ishtar.isMagic());
    }

    @Test
    void isActivableTest() {
        assertTrue(ishtar.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(ishtar.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.NONE, ishtar.getEffectType());
    }
}
