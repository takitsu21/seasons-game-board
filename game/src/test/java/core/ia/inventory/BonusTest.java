package core.ia.inventory;


import core.board.Board;
import core.board.FacadeIA;
import core.board.enums.Energy;
import core.cards.Card;
import core.cards.CardFactory;
import core.cards.Deck;
import core.cards.EffectFrequency;
import core.exception.BonusException;
import core.game.GameController;
import core.ia.EnumPlayerAction;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BonusTest {
    @Mock
    Deck deck;
    private Bonus bonus;
    private Inventory inventory;
    private Board board;
    private Card card;
    private Player player;
    private FacadeIA facadeIA;
    private GameController controller=new GameController();

    @BeforeEach
    void setUp() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Config config = new Config();
        bonus = new Bonus();
        PlayerFactory playerFactory = new PlayerFactory(config);
        player = playerFactory.getPlayer(TypeAIPlayer.CHOOSE_FIRST, "IA1");
        Player[] players = new Player[1];
        players[0] = player;
        controller.initGame(players, config);
        deck = Mockito.mock(Deck.class);
        board = new Board(players, new Config());
        board.setDeck(deck);
        inventory = board.getInventories().get(player);
        card = CardFactory.getINSTANCE().createCard(CardFactory.getINSTANCE().getCard(1), 0, false, 1, new ArrayList<>(), EffectFrequency.ON_PLAY, "CArd", 2);
        when(deck.drawCard()).thenReturn(card);
        when(deck.isEmpty()).thenReturn(true);
        facadeIA = new FacadeIA(board, player, config);
        player.setFacadeIA(facadeIA);
    }


    @Test
    void useCrystallizeTest() {
        bonus.crystallize(inventory);
        assertEquals(1, inventory.getBonusCrystal());
        assertTrue(inventory.getCanCrystalize());
    }

    @Test
    void useChangeEnergyTest() {
        // Pas d'énergies dans le stock
        bonus.useBonus(BonusType.CHANGE_ENERGY, player, board);
        assertEquals(new ArrayList<Energy>(), inventory.getEnergyStock().theEnergyStock);

        // Possède les energie dans le stock
        inventory.getEnergyStock().addEnergy(Energy.EARTH);
        inventory.getEnergyStock().addEnergy(Energy.EARTH);
        bonus.changeEnergy(inventory, player);
        List<Energy> energyList = new ArrayList<>();
        energyList.add(Energy.WATER);
        energyList.add(Energy.WATER);
        assertEquals(energyList, inventory.getEnergyStock().getEnergyStock());
    }

    @Test
    void useAddInvocationTest() {
        bonus.addIncovation(inventory);
        assertEquals(1, inventory.getInvocation().getInvocationPoints());
    }

    @Test
    void useBonusCrystalize() {
        // Use Cristallize
        bonus.useBonus(BonusType.CRYSTALLIZE, player, board);
        assertTrue(bonus.getInUseCrystallizeBonus());
        assertEquals(1, bonus.getUsedBonus());
        assertTrue(inventory.getCanCrystalize());
        assertEquals(1, inventory.getBonusCrystal());

        bonus.useBonus(BonusType.CRYSTALLIZE, player, board);
        assertTrue(bonus.getInUseCrystallizeBonus());
        assertEquals(2, bonus.getUsedBonus());
        assertTrue(inventory.getCanCrystalize());
        assertEquals(2, inventory.getBonusCrystal());
    }

    @Test
    void useBonusDraw() {
        // Use Draw 2 cards
        board.setDeck(new Deck());
        board.initHand(player);
        assertEquals(3, inventory.getHand().getCardsInHand().size());
        bonus.useBonus(BonusType.DRAW_2_CARDS, player, board);
        assertEquals(4, inventory.getHand().getCardsInHand().size());
    }

    @Test
    void useBonusDrawDeckEmpty() {
        // Use Draw 2 cards
        board.setDeck(new Deck());
        board.initHand(player);
        board.setDeck(deck);
        assertEquals(3, inventory.getHand().getCardsInHand().size());
        bonus.useBonus(BonusType.DRAW_2_CARDS, player, board);
        assertEquals(3, inventory.getHand().getCardsInHand().size());
    }

    @Test
    void useBonusChangeEnergy() {
        // Use Change energy
        bonus.useBonus(BonusType.CHANGE_ENERGY, player, board);
        assertEquals(1, bonus.getUsedBonus());
    }

    @Test
    void useBonusAddInvocation() {
        // Used invocation
        assertEquals(0, inventory.getInvocation().getInvocationPoints());
        bonus.useBonus(BonusType.ADD_INVOCATION, player, board);
        assertEquals(1, inventory.getInvocation().getInvocationPoints());
    }

    @Test
    void useBonusMax() {
        // Used bonus == 3
        bonus.setUsedBonus(3);

        assertEquals(0, inventory.getInvocation().getInvocationPoints());

        bonus.useBonus(BonusType.ADD_INVOCATION, player, board);
        assertEquals(3, bonus.getUsedBonus());

        assertEquals(0, inventory.getInvocation().getInvocationPoints());

    }

    @Test
    void useBonusError() {
        assertThrows(BonusException.class, () -> {
            bonus.useBonus(BonusType.ERROR, player, board);
        });
    }

    @Test
    void possibleBonus() {
        inventory.getEnergyStock().addEnergy(Energy.FIRE);
        inventory.getEnergyStock().addEnergy(Energy.FIRE);
        assertEquals(new ArrayList<>() {{
                         add(BonusType.CHANGE_ENERGY);
                         add(BonusType.CRYSTALLIZE);
                         add(BonusType.ADD_INVOCATION);
                     }},
                inventory.getPossibleBonus());
    }

    @Test
    void possibleBonusNull() {
        inventory.setCanCrystalize(true);
        assertEquals(new ArrayList<>() {{
                         add(BonusType.ADD_INVOCATION);
                     }},
                inventory.getPossibleBonus());
    }

    @Test
    void findPossibleBonus() {

        Card card = Mockito.mock(Card.class);
        when(card.getEffectFrequency()).thenReturn(EffectFrequency.ON_ACTIVATION);
        when(card.isActivable()).thenReturn(true);
        when(card.isActivated()).thenReturn(false);


        inventory.getEnergyStock().addEnergy(Energy.FIRE);
        inventory.setCanCrystalize(true);
        inventory.addInvocationPoints(2);
        Card[][] cards = new Card[3][7];
        cards[0][0] = card;
        inventory.setHand(new Hand(cards));
        inventory.getHand().addCard(card);
        inventory.getInvocation().getCardsOnBoard().add(card);

        assertEquals(new ArrayList<>() {{
                         add(EnumPlayerAction.CRYSTALLISE);
                         add(EnumPlayerAction.SUMMON);
                         add(EnumPlayerAction.BONUS);
                         add(EnumPlayerAction.ACTIVATE_CARD);
                         add(EnumPlayerAction.NOTHING);
                     }},
                inventory.findPossibleAction(board));
    }

    @Test
    void findPossibleBonusNull() {
        Card[][] cards = new Card[1][1];
        cards[0][0] = card;
        inventory.setHand(new Hand(cards));
        inventory.getBonus().setUsedBonus(3);
        assertEquals(new ArrayList<>() {{
                         add(EnumPlayerAction.NOTHING);
                     }},
                inventory.findPossibleAction(board));
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "1, -5",
            "2, -12",
            "3, -20",
    })
    void calculatePrestigePoints1Bonus(int nbBonus, int malus) {
        Card[][] cards = new Card[3][7];
        cards[0][0] = card;
        inventory.setHand(new Hand(cards));
        inventory.getBonus().setUsedBonus(nbBonus);
        inventory.getHand().setCardsInHand(new ArrayList<>());
        inventory.calculatePrestigePoint();
        assertEquals(malus, inventory.getPrestigePoints());
    }

    @Test
    void addUsedBonus() {
        assertEquals(0, bonus.getUsedBonus());
        bonus.addUsedBonus();
        assertEquals(1, bonus.getUsedBonus());
    }

    @Test
    void addUsedBonusMax() {
        bonus.setUsedBonus(3);
        assertEquals(3, bonus.getUsedBonus());
        bonus.addUsedBonus();
        assertEquals(3, bonus.getUsedBonus());
    }

}