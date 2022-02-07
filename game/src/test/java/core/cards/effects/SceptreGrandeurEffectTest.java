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
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SceptreGrandeurEffectTest {
    private Deck deck;
    private Player p1;
    private Inventory inventory1;
    private Card sceptreGrandeur;
    private Card magicCard1;
    private Card magicCard2;
    private Card notMagicCard;

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
        sceptreGrandeur = deck.findCard(28);
        magicCard1 = deck.findCard(22);
        magicCard2 = deck.findCard(22);
        notMagicCard = Mockito.mock(Card.class);
        when(notMagicCard.isMagic()).thenReturn(false);

    }

    @Test
    void useTestMaxEnergy() {
        inventory1.getHand().addCard(sceptreGrandeur);
        inventory1.getHand().addCard(magicCard1);
        inventory1.getHand().addCard(magicCard2);
        assertEquals(6, inventory1.getCardsInHand().size());

        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory1.getEnergyStock().addEnergy(Energy.EARTH, null);
        inventory1.getEnergyStock().addEnergy(Energy.FIRE, null);
        inventory1.setCrystals(40);

        inventory1.getInvocation().setInvocationPoints(10);
        assertTrue(inventory1.summonCard(sceptreGrandeur, controller.getBoard()));
        assertTrue(inventory1.summonCard(magicCard1, controller.getBoard()));
        assertTrue(inventory1.summonCard(magicCard2, controller.getBoard()));
        assertEquals(0, inventory1.getCrystals());

        sceptreGrandeur.use(controller.getBoard(), p1);
        assertEquals(6, inventory1.getCrystals());

    }

    @Test
    void useTestMaxEnergyNotMagicCard() {
        inventory1.getHand().addCard(sceptreGrandeur);
        inventory1.getHand().addCard(notMagicCard);

        inventory1.getEnergyStock().addEnergy(Energy.WATER, null);
        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory1.getEnergyStock().addEnergy(Energy.EARTH, null);
        inventory1.getEnergyStock().addEnergy(Energy.FIRE, null);


        inventory1.getInvocation().setInvocationPoints(1);

        assertTrue(inventory1.summonCard(sceptreGrandeur, controller.getBoard()));
        inventory1.getInvocation().getCardsOnBoard().add(notMagicCard);

        sceptreGrandeur.use(controller.getBoard(), p1);
        assertEquals(0, inventory1.getCrystals());

    }

    @Test
    void isMagic() {
        assertTrue(sceptreGrandeur.isMagic());
    }

    @Test
    void isActivableTest() {
        assertFalse(sceptreGrandeur.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(sceptreGrandeur.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.NONE, sceptreGrandeur.getEffectType());
    }
}
