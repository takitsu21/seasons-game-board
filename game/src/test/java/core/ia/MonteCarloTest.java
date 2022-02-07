package core.ia;

import core.board.Board;
import core.board.FacadeIA;
import core.board.Year;
import core.board.enums.Energy;
import core.cards.Card;
import core.cards.Deck;
import core.dice.Dice;
import core.dice.Face;
import core.game.App;
import core.game.GameController;
import core.ia.inventory.Inventory;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.bonus.ChooseBonusFirst;
import core.ia.strategy.choose.bonus.ChooseBonusPrefCrystallize;
import core.ia.strategy.choose.bonus.ChooseBonusRandom;
import core.ia.strategy.choose.bonus.StrategyChooseBonus;
import core.util.Config;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MonteCarloTest {

    private Config config;
    private GameController controller;
    private PlayerFactory playerFactory;
    private Player player;
    private FacadeIA facadeIA;
    private Board board;
    private Board nextBoard;


    @BeforeEach
    void setUP() throws CloneNotSupportedException {
        config = new Config();
        controller = new GameController();
        playerFactory = new PlayerFactory(config);
        player = playerFactory.getPlayer(TypeAIPlayer.CHOOSE_FIRST, "IA");
        Player[] players = new Player[1];
        players[0] = player;
        controller.initGame(players, config);
        board = controller.getBoard();
        board.initPLayersInventory();
        facadeIA = new FacadeIA(board, player, config );
        player.setFacadeIA(facadeIA);
        board.initHand(player);
        board.initDiceSet();
        nextBoard= (Board) board.clone();
    }

    @Test
    void cloneBoardCurrentCursor() {
        assertEquals(nextBoard.getCurrentCursor(), 0);
        assertEquals(board.getCurrentCursor(), 0);
        nextBoard.setCurrentCursor(1);
        assertEquals(nextBoard.getCurrentCursor(), 1);
        assertEquals(board.getCurrentCursor(), 0);
    }

    @Test
    void cloneBoardInventoryCrystal() {
        Inventory inventory=board.getInventories().get(player);
        assertNotNull(inventory);

        Map.Entry<Player,Inventory> entry = nextBoard.getInventories().entrySet().iterator().next();
        Inventory inventoryClone=entry.getValue();
        assertNotNull(inventoryClone);

        assertEquals(inventoryClone.getCrystals(), 0);
        assertEquals(inventory.getCrystals(), 0);
        inventoryClone.addCrystals(1);
        assertEquals(inventoryClone.getCrystals(), 1);
        assertEquals(inventory.getCrystals(), 0);
    }

    @Test
    void cloneBoardFacadeIACardInHand() {
        FacadeIA facadeIA=player.getFacadeIA();
        assertNotNull(facadeIA);

        Map.Entry<Player,Inventory> entry = nextBoard.getInventories().entrySet().iterator().next();
        FacadeIA cloneFacadeIA=entry.getKey().getFacadeIA();
        assertNotNull(cloneFacadeIA);

        assertEquals(facadeIA.getCardInHand(), cloneFacadeIA.getCardInHand());
        board.getInventories().get(player).addCard(Mockito.mock(Card.class));
        assertNotEquals(facadeIA.getCardInHand(), cloneFacadeIA.getCardInHand());
    }

    @Test
    void cloneDice() throws CloneNotSupportedException {
        Dice dice=new Dice(0, new Year(1, 3));
        Dice cloneDice= (Dice) dice.clone();

        assertEquals(dice.getCurrentFace().toString(), cloneDice.getCurrentFace().toString());
        Face oldFace=dice.getCurrentFace();
        do {
            dice.reRollDice();
        }while(oldFace.equals(dice.getCurrentFace()));
        assertNotEquals(dice.getCurrentFace().toString(), cloneDice.getCurrentFace().toString());

    }

    @Test
    void cloneBordDiceSet() throws CloneNotSupportedException {
        Board nextB = (Board) nextBoard.clone();
        assertEquals(board.getDices().toString(), nextBoard.getDices().toString());
        assertEquals(nextBoard.getDices().toString(), nextB.getDices().toString());
        board.getDices().removeDice(board.getDices().getDice(0));
        assertNotEquals(board.getDices().toString(), nextBoard.getDices().toString());
    }

}
