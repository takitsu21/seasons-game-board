package core.ia.inventory;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.Card;
import core.cards.Deck;
import core.game.App;
import core.game.GameController;
import core.ia.EnumPlayerAction;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.ia.inventory.Hand;
import core.ia.inventory.Inventory;
import core.ia.inventory.Invocation;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class InventoryTest {
    private Inventory inventory;
    private GameController controller;
    private Board board;
    private Card deMalice;
    private Deck newDeck;
    private Player player;

    @BeforeEach
    public void setUp() {
        Deck deck = new Deck();
        deMalice = deck.findCard(15); //De de la malice, gratuite à invoquer
        deMalice.setEnergyCost(new ArrayList<>(List.of(Energy.FIRE))); //deMalice remplace BasicCard qui coûtait 1 de feu de base
        Config config = new Config();
        controller = new GameController();
        Player[] playerList = App.initPlayer(2, config);
        controller.initGame(playerList, config);
        board = controller.getBoard();
        inventory = board.getInventories().get(playerList[0]);
        board.initHand(playerList[0]);
        board.initHand(playerList[1]);
        inventory.getHand().getCardsInHand().clear();
        newDeck = new Deck();
        player=playerList[0];
    }

    @Test
    void summonCardTest() {

        //test si les invocationPoints sont bien "consommés" et empêche une invocation si besoin
        inventory.addCard(deMalice);
        inventory.getEnergyStock().addEnergy(Energy.FIRE, null);
        inventory.getInvocation().setInvocationPoints(1);
        assertTrue(inventory.summonCard(deMalice, controller.getBoard()));

        inventory.addCard(deMalice);
        inventory.getEnergyStock().addEnergy(Energy.FIRE, null);
        assertFalse(inventory.summonCard(deMalice, controller.getBoard()));
        inventory.getHand().getCardsInHand().remove(deMalice);


        //test si summonCard modifie bien les listes des Card du joueur
        inventory.addCard(deMalice);
        inventory.getInvocation().setInvocationPoints(15);
        assertTrue(inventory.summonCard(deMalice, controller.getBoard()));
        assertFalse(inventory.getCardsInHand().contains(deMalice));
        assertTrue(inventory.getInvocation().getCardsOnBoard().contains(deMalice));

        //test si les energies sont bien consommées
        assertFalse(inventory.getEnergyStock().getEnergyStock().contains(Energy.FIRE));

        //test si les crystaux sont bien consommés
        inventory.setCrystals(5);
        inventory.getEnergyStock().addEnergy(Energy.FIRE, null);
        deMalice.setCrystalCost(3);
        inventory.addCard(deMalice);
        assertTrue(inventory.summonCard(deMalice, controller.getBoard()));
        assertEquals(2, inventory.getCrystals());

        //test si la carte ne peut être invoquée si elle n'est pas dans la main du joueur
        assertFalse(inventory.summonCard(deMalice, controller.getBoard()));

    }

    @Test
    void retireCardFromBoardTest() {
        Invocation invocation = inventory.getInvocation();
        Hand hand = inventory.getHand();
        invocation.setInvocationPoints(5);
        invocation.getCardsOnBoard().add(deMalice);
        invocation.incrementCurrentInvocations();

        int oldBoardSize = invocation.getCardsOnBoard().size();
        int oldCurrentInvocations = invocation.getCurrentInvocations();
        int oldHandSize = hand.getCardsInHand().size();

        inventory.retireCardFromBoard(deMalice);
        assertEquals(oldCurrentInvocations - 1, invocation.getCurrentInvocations());
        assertEquals(oldBoardSize - 1, invocation.getCardsOnBoard().size());
        assertEquals(oldHandSize + 1, hand.getCardsInHand().size());
    }

    @Test
    void hasEnoughCrystalsTest() {

        deMalice.setCrystalCost(5);
        assertFalse(inventory.hasEnoughCrystals(deMalice));

        inventory.setCrystals(5);
        assertTrue(inventory.hasEnoughCrystals(deMalice));
    }

    @Test
    void hasEnoughEnergyTest() {
        Card amulette = newDeck.findCard(4);
        assertFalse(inventory.getEnergyStock().hasEnoughEnergy(deMalice.getEnergyCost(), inventory.getInvocation().getCardsOnBoard())); //joueur n'a pas d'énergies

        inventory.getEnergyStock().addEnergy(Energy.FIRE, null);
        assertTrue(inventory.getEnergyStock().hasEnoughEnergy(deMalice.getEnergyCost(), inventory.getInvocation().getCardsOnBoard())); //joueur a la (les) bonne(s) énergie(s)

        deMalice.setEnergyCost(new ArrayList<>() {{
            add(Energy.WIND);
            add(Energy.WATER);
        }});
        assertFalse(inventory.getEnergyStock().hasEnoughEnergy(deMalice.getEnergyCost(), inventory.getInvocation().getCardsOnBoard())); //joueur n'a pas les bonnes énergies

        amulette.getEnergyStock().addEnergy(Energy.WIND);
        amulette.getEnergyStock().addEnergy(Energy.WATER);
        inventory.getInvocation().addInvocationPoints(1);
        inventory.getInvocation().getCardsOnBoard().add(amulette);
        inventory.addCard(deMalice);

        assertTrue(inventory.summonCard(deMalice, controller.getBoard()));
        assertTrue(amulette.getEnergyStock().getEnergyStock().isEmpty());
    }

    @Test
    void addCardTest() {

        List<Card> cardInHand = new ArrayList<>();
        assertEquals(cardInHand, inventory.getHand().getCardsInHand());

        inventory.getHand().addCard(deMalice);
        cardInHand.add(deMalice);
        assertEquals(cardInHand, inventory.getHand().getCardsInHand());
    }

    @Test
    void setInvocationPointsTest() {
        // donner un nombre valable
        inventory.getInvocation().setInvocationPoints(10);
        assertEquals(10, inventory.getInvocation().getInvocationPoints());

        // donner un nombre non valable
        inventory.getInvocation().setInvocationPoints(-1);
        assertEquals(10, inventory.getInvocation().getInvocationPoints());
        inventory.getInvocation().setInvocationPoints(17);
        assertEquals(10, inventory.getInvocation().getInvocationPoints());
    }

    @Test
    void calculatePrestigePointsCrystal() {
        inventory.addCrystals(5);
        inventory.calculatePrestigePoint();
        assertEquals(5, inventory.getPrestigePoints());
    }

    @Test
    void calculatePrestigePointsUsedOneCard() {
        inventory.getInvocation().setInvocationPoints(1);
        Card card = newDeck.findCard(1);
        inventory.addCard(card);
        inventory.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory.getEnergyStock().addEnergy(Energy.WIND, null);

        assertTrue(inventory.summonCard(card, board));
        inventory.calculatePrestigePoint();
        assertEquals(6, inventory.getPrestigePoints());
    }

    @Test
    void calculatePrestigePointsNonUsedCard() {
        Card card = newDeck.findCard(1);
        inventory.addCard(card);

        inventory.calculatePrestigePoint();
        assertEquals(-5, inventory.getPrestigePoints());
    }

    @Test
    void calculatePrestigePointsUseBonus() {
        inventory.getBonus().setUsedBonus(1);
        inventory.calculatePrestigePoint();
        assertEquals(-5, inventory.getPrestigePoints());

        inventory.setPrestigePoints(0);
        inventory.getBonus().setUsedBonus(2);
        inventory.calculatePrestigePoint();
        assertEquals(-12, inventory.getPrestigePoints());

        inventory.setPrestigePoints(0);
        inventory.getBonus().setUsedBonus(3);
        inventory.calculatePrestigePoint();
        assertEquals(-20, inventory.getPrestigePoints());
    }


    @Test
    void activableCard() {
        Card card1 = Mockito.mock(Card.class);
        Card card2 = Mockito.mock(Card.class);
        Card card3 = Mockito.mock(Card.class);

        when(card1.isActivable()).thenReturn(true);
        when(card2.isActivable()).thenReturn(false);
        when(card3.isActivable()).thenReturn(true);

        assertEquals(List.of(), inventory.getInvocation().getActivableCard());

        inventory.getInvocation().getCardsOnBoard().add(card1);
        inventory.getInvocation().getCardsOnBoard().add(card2);
        inventory.getInvocation().getCardsOnBoard().add(card3);

        assertEquals(List.of(card1, card3), inventory.getInvocation().getActivableCard());
    }

    @Test
    void findPossibleActionCantCrystallize1() {
        inventory.setCanCrystalize(false);
        assertTrue(!inventory.findPossibleAction(board).contains(EnumPlayerAction.CRYSTALLISE));
    }

    @Test
    void findPossibleActionCantCrystallize2() {
        // EnergyStock is empty so the player can't crystallize
        assertTrue(!inventory.findPossibleAction(board).contains(EnumPlayerAction.CRYSTALLISE));
    }

    @Test
    void findPossibleActionCantCrystallize3() {
        inventory.setCanCrystalize(false);
        // EnergyStock is empty so the player can't crystallize
        assertTrue(!inventory.findPossibleAction(board).contains(EnumPlayerAction.CRYSTALLISE));
    }

    @Test
    void hasInvocationLeft() {
        assertFalse(inventory.hasInvocationsLeft());
        inventory.getInvocation().setInvocationPoints(1);
        assertTrue(inventory.hasInvocationsLeft());
    }


    @Test
    void tostringInvocation() {
        assertEquals("Invocation{MAX_INVOCATION_POINTS=15, invocationPoints=0, cardsOnBoard=[]}",
                inventory.getInvocation().toString());
    }


}
