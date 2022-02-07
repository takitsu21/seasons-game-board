package core.ia.inventory;

import core.board.Year;
import core.cards.Card;
import core.cards.Deck;
import core.dice.Dice;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvocationTest {
    private Deck deck;
    private Player p1;
    private Inventory inventory;
    private GameController controller;
    private Card kairn;

    @BeforeEach
    void setUp() {
        Config config = new Config();
        controller = new GameController();
        Player[] playerList = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        playerList[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        p1 = playerList[0];

        controller.initGame(playerList, config);
        controller.getBoard().initPLayersInventory();
        controller.getBoard().getInventories().get(p1).setCurrentDice(new Dice(0, new Year(config.getNbYears())));
        controller.getBoard().initHand(p1);

        deck = new Deck();
        inventory = controller.getBoard().getInventories().get(p1);
        kairn = deck.findCard(16);
        inventory.getHand().getCardsInHand().clear();
        inventory.getHand().addCard(kairn);
        inventory.getInvocation().setInvocationPoints(2);
    }

    @Test
    void getActivableCardTest() {
        inventory.summonForFree(kairn, controller.getBoard());
        assertEquals(List.of(kairn), inventory.getInvocation().getActivableCard());
    }

    @Test
    void toStringCardOnBoardTest() {
        inventory.summonForFree(kairn, controller.getBoard());
        assertEquals("[ " + kairn.getName() + " ]", inventory.getInvocation().toStringCardOnBoard());
    }

}
