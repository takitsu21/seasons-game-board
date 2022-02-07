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

import static org.junit.jupiter.api.Assertions.*;

class PotionPuissanceEffectTest {
    private Deck deck;
    private Player p1;

    private GameController controller;
    private PlayerTurnLoop playerTurnLoop;

    private Card potionPuissance;

    @BeforeEach
    public void setUp() {
        Config config = new Config();
        controller = new GameController();
        Player[] playerList = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        playerList[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        p1 = playerList[0];


        controller.initGame(playerList, config);
        controller.getBoard().initPLayersInventory();
        controller.getBoard().initHand(p1);
        controller.getBoard().getInventories().get(p1).setCurrentDice(new Dice(0, new Year(config.getNbYears())));
        playerTurnLoop = new PlayerTurnLoop(p1, controller);

        deck = new Deck();
        potionPuissance = deck.findCard(23);

    }

    @Test
    void useTest() {
        Inventory inventory1 = controller.getBoard().getInventories().get(p1);

        inventory1.getHand().addCard(potionPuissance);
        assertEquals(4, inventory1.getCardsInHand().size());

        inventory1.getEnergyStock().addEnergy(Energy.FIRE, null);
        inventory1.getEnergyStock().addEnergy(Energy.FIRE, null);
        inventory1.getInvocation().setInvocationPoints(2);

        assertTrue(inventory1.summonCard(potionPuissance, controller.getBoard()));
        assertEquals(3, inventory1.getCardsInHand().size());
        potionPuissance.use(controller.getBoard(), p1);
        assertEquals(4, inventory1.getCardsInHand().size());

    }

    @Test
    void isMagic() {
        assertTrue(potionPuissance.isMagic());
    }

    @Test
    void isActivable() {
        assertTrue(potionPuissance.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(potionPuissance.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.NONE, potionPuissance.getEffectType());
    }
}
