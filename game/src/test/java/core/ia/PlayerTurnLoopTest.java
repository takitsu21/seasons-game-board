package core.ia;

import core.board.Board;
import core.board.FacadeIA;
import core.board.enums.Energy;
import core.cards.Card;
import core.dice.Dice;
import core.dice.Face;
import core.game.GameController;
import core.game.states.Move;
import core.ia.inventory.Inventory;
import core.ia.strategy.choose.player_action.StrategyChoosePlayerAction;
import core.ia.strategy.choose.use_bonus_card.StrategyChooseUseBonusCard;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PlayerTurnLoopTest {

    private PlayerTurnLoop playerTurnLoop;
    private GameController controller;
    private Inventory inventory;
    private Card card;
    private Player player;
    private FacadeIA facadeIA;
    private Dice dice;
    private Face face;

    @BeforeEach
    public void setUp() {
        Config config = new Config();
        controller = new GameController();
        PlayerFactory playerFactory = new PlayerFactory(config);
        player = playerFactory.getPlayer(TypeAIPlayer.CHOOSE_FIRST, "first");
        Player[] playerList = new Player[]{player};
        controller.initGame(playerList, config);
        Board board = controller.getBoard();
        inventory = board.getInventories().get(player);
        board.initHand(player);
        inventory.getHand().getCardsInHand().clear();
        playerTurnLoop = new PlayerTurnLoop(player, controller);
        facadeIA = new FacadeIA(board, player, config);
        player.setFacadeIA(facadeIA);

        card = Mockito.mock(Card.class);
        when(card.getCrystalCost()).thenReturn(0);
        when(card.getEnergyCost()).thenReturn(new ArrayList<>());

        dice = Mockito.mock(Dice.class);
        face = Mockito.mock(Face.class);
        when(dice.getCurrentFace()).thenReturn(face);
        inventory.setCurrentDice(dice);
    }

    @Test
    void summon() {
        inventory.getHand().getCardsInHand().clear();
        inventory.getHand().getCardsInHand().add(card);
        inventory.addInvocationPoints(1);
        inventory.getEnergyStock().addEnergy(Energy.EARTH);
        assertEquals(0, inventory.getInvocation().getCardsOnBoard().size());
        playerTurnLoop.summon();
        assertEquals(1, inventory.getInvocation().getCardsOnBoard().size());
    }

    @Test
    void actionDiceEnergyCrystalizeTakeCard() {
        StrategyChooseUseBonusCard strategyChooseUseBonusCard = Mockito.mock(StrategyChooseUseBonusCard.class);

        when(face.isCrystalize()).thenReturn(true);
        when(face.getEnergy()).thenReturn(new Energy[]{Energy.FIRE});
        when(face.isTakeCard()).thenReturn(true);
        when(strategyChooseUseBonusCard.choose(player)).thenReturn(false);

        player.setChooseUseBonusCard(strategyChooseUseBonusCard);

        assertEquals(0, inventory.getEnergyStock().getEnergyStock().size());
        assertFalse(inventory.getCanCrystalize());
        assertEquals(0, inventory.getCardsInHand().size());


        playerTurnLoop.actionDice();

        assertEquals(1, inventory.getEnergyStock().getEnergyStock().size());
        assertEquals(Energy.FIRE, inventory.getEnergyStock().getEnergyStock().get(0));
        assertTrue(inventory.getCanCrystalize());
        assertEquals(1, inventory.getCardsInHand().size());
    }

    @Test
    void actionDiceBonus() {
        when(face.isTakeCard()).thenReturn(true);

        assertEquals(0, inventory.getBonus().getUsedBonus());
        playerTurnLoop.actionDice();
        assertEquals(1, inventory.getBonus().getUsedBonus());
    }

    @Test
    void processPlayerTurnLoopActionNothing() {
        StrategyChoosePlayerAction strategyChoosePlayerAction = Mockito.mock(StrategyChoosePlayerAction.class);

        inventory.setCanCrystalize(true);

        player.setFacadeIA(facadeIA);
        when(strategyChoosePlayerAction.doChoose(player)).thenReturn(EnumPlayerAction.NOTHING);
        player.setChoosePlayerAction(strategyChoosePlayerAction);

        playerTurnLoop.processPlayerTurnLoop();
        assertEquals(0, inventory.getEnergyStock().getEnergyStock().size());
        assertEquals(0, inventory.getCrystals());
        assertEquals(0, inventory.getInvocation().getInvocationPoints());
    }

    @Test
    void makeActionCrystalize() {
        inventory.setCanCrystalize(true);

        inventory.getEnergyStock().addEnergy(Energy.FIRE);
        assertEquals(1, inventory.getEnergyStock().getEnergyStock().size());
        assertTrue(playerTurnLoop.makeAction(EnumPlayerAction.CRYSTALLISE, new Move()));
        assertEquals(0, inventory.getEnergyStock().getEnergyStock().size());

    }

    @Test
    void makeActionBonus() {
        assertEquals(0, inventory.getBonus().getUsedBonus());
        assertTrue(playerTurnLoop.makeAction(EnumPlayerAction.BONUS, new Move()));
        assertEquals(1, inventory.getBonus().getUsedBonus());
    }

    @Test
    void makeActionSummon() {
        inventory.getHand().getCardsInHand().clear();
        inventory.getHand().getCardsInHand().add(card);
        inventory.addInvocationPoints(1);
        inventory.getEnergyStock().addEnergy(Energy.EARTH);

        assertEquals(0, inventory.getInvocation().getCardsOnBoard().size());
        assertTrue(playerTurnLoop.makeAction(EnumPlayerAction.SUMMON, new Move()));
        assertEquals(1, inventory.getInvocation().getCardsOnBoard().size());
    }

    @Test
    void makeActionSummonEmpty() {
        inventory.getHand().getCardsInHand().clear();
        inventory.addInvocationPoints(1);
        assertEquals(0, inventory.getInvocation().getCardsOnBoard().size());
        assertTrue(playerTurnLoop.makeAction(EnumPlayerAction.SUMMON, new Move()));
        assertEquals(0, inventory.getInvocation().getCardsOnBoard().size());
    }

    @Test
    void makeActionSummonNoInvacationPoints() {
        inventory.getHand().getCardsInHand().clear();
        inventory.getHand().getCardsInHand().add(card);
        assertEquals(0, inventory.getInvocation().getCardsOnBoard().size());
        assertTrue(playerTurnLoop.makeAction(EnumPlayerAction.SUMMON, new Move()));
        assertEquals(0, inventory.getInvocation().getCardsOnBoard().size());
    }
}