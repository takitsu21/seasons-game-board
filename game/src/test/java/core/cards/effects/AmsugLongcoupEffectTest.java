package core.cards.effects;

import core.board.Board;
import core.board.FacadeIA;
import core.cards.Card;
import core.cards.Deck;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.ia.inventory.Invocation;
import core.ia.strategy.choose.card_come_back_in_hand.StrategyChooseCardComeBackInHand;
import core.ia.strategy.choose.card_come_back_in_hand.ChooseCardComeBackInHandFirst;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Every player move one of his OBJECT card on board to his hand
 * If a player doesn't have an object card he is not affected
 */
class AmsugLongcoupEffectTest {
    private Card amsug;
    private Card grimoire;
    private Card grimoire2;
    private Board board;
    private Player player;
    private FacadeIA facadeIA;
    private Player[] playerList;
    private Card notMagicCard;
    private Card magicCard;
    private StrategyChooseCardComeBackInHand chooseCardComeBackInHand;


    @BeforeEach
    void setUp() {
        Config config = new Config();
        Deck deck = new Deck();
        amsug = deck.findCard(17);
        grimoire = deck.findCard(18);
        grimoire2 = deck.findCard(18);
        GameController controller = new GameController();
        playerList = App.initPlayer(4, config);
        facadeIA = Mockito.mock(FacadeIA.class);
        player = playerList[0];
        player.setFacadeIA(facadeIA);
        controller.initGame(playerList, config);
        board = controller.getBoard();

        board.getDeck().getDeck().remove(amsug);
        for (int i=0; i<playerList.length; i++){
            board.initHand(playerList[i]);
        }
        board.getDeck().getDeck().add(amsug);
        notMagicCard = Mockito.mock(Card.class);
        when(notMagicCard.isMagic()).thenReturn(false);

        magicCard = Mockito.mock(Card.class);
        when(magicCard.isMagic()).thenReturn(true);
        when(magicCard.getCrystalCost()).thenReturn(0);
        when(magicCard.getEnergyCost()).thenReturn(List.of());
        when(magicCard.getName()).thenReturn("test");

        when(facadeIA.getMagicCardsOnBoard()).thenReturn(List.of(magicCard));
    }


    @Test
    void useTest() {

        //pour chaque joueur, on ajoute un grimoire sur leur board et on l'utilise
        for (Player currentPlayer : playerList) {
            Inventory inventory = board.getInventories().get(currentPlayer);
            Invocation invocation = inventory.getInvocation();

            invocation.setInvocationPoints(5);
            invocation.getCardsOnBoard().add(grimoire);
            grimoire.use(board, currentPlayer);
            invocation.incrementCurrentInvocations();

            assertEquals(1, invocation.getCardsOnBoard().size());
            assertEquals(1, invocation.getCurrentInvocations());
            assertEquals(10, inventory.getEnergyStock().getNbMaxEnergy());
        }

        amsug.use(board, player);

        for (Player currentPlayer : playerList) {
            Inventory inventory = board.getInventories().get(currentPlayer);
            assertEquals(0, inventory.getInvocation().getCardsOnBoard().size());
            assertEquals(0, inventory.getInvocation().getCurrentInvocations());
            assertEquals(7, inventory.getEnergyStock().getNbMaxEnergy());

            //préparation du test avec 2 grimoires
            Invocation invocation = inventory.getInvocation();
            invocation.getCardsOnBoard().add(grimoire);
            invocation.incrementCurrentInvocations();
            grimoire.use(board, currentPlayer);
            invocation.getCardsOnBoard().add(grimoire2);
            invocation.incrementCurrentInvocations();
            grimoire2.use(board, currentPlayer);

            assertEquals(10, inventory.getEnergyStock().getNbMaxEnergy());
        }

        amsug.use(board, player);

        for (Player currentPlayer : playerList) {
            Inventory inventory = board.getInventories().get(currentPlayer);

            //s'il y avait deux grimoires sur le plateau, NbMaxEnergy doit rester à 10 malgré le retrait d'un grimoire
            assertEquals(10, inventory.getEnergyStock().getNbMaxEnergy());
            assertEquals(1, inventory.getInvocation().getCardsOnBoard().size()); //et ces deux tests sont là au cas où
            assertEquals(1, inventory.getInvocation().getCurrentInvocations());
        }
    }

    @Test
    void useTestChoosenCardNull() {
        //player.setChooseCardComeBackInHand(new ChooseCardComeBackInHandFirst());

        board.getInventories().get(playerList[0]).getInvocation().getCardsOnBoard().add(notMagicCard);
        assertEquals(1, board.getInventories().get(playerList[0]).getInvocation().getCardsOnBoard().size());
        assertEquals(0, board.getInventories().get(playerList[0]).getInvocation().getCurrentInvocations());
        assertEquals(7, board.getInventories().get(playerList[0]).getEnergyStock().getNbMaxEnergy());

        amsug.use(board, player);

        assertEquals(1, board.getInventories().get(playerList[0]).getInvocation().getCardsOnBoard().size());
        assertEquals(0, board.getInventories().get(playerList[0]).getInvocation().getCurrentInvocations());
        assertEquals(7, board.getInventories().get(playerList[0]).getEnergyStock().getNbMaxEnergy());
    }

    @Test
    void useTestChoosenCardNotGrimoire() {
        chooseCardComeBackInHand =Mockito.mock(StrategyChooseCardComeBackInHand.class);

        when(chooseCardComeBackInHand.doChoose(player)).thenReturn(magicCard);
        player.setChooseCardComeBackInHand(chooseCardComeBackInHand);

        board.getInventories().get(playerList[0]).getInvocation().getCardsOnBoard().clear();
        board.getInventories().get(playerList[0]).getInvocation().addInvocationPoints(1);
        board.getInventories().get(playerList[0]).addCard(magicCard);
        //board.getInventories().get(playerList[0]).getInvocation().getCardsOnBoard().add(magicCard);
        assertTrue(board.getInventories().get(playerList[0]).summonCard(magicCard, board));
        assertEquals(1, board.getInventories().get(playerList[0]).getInvocation().getCardsOnBoard().size());
        assertEquals(1, board.getInventories().get(playerList[0]).getInvocation().getCurrentInvocations());
        assertEquals(7, board.getInventories().get(playerList[0]).getEnergyStock().getNbMaxEnergy());

        //board.getInventories().get(playerList[0]).getInvocation().getCardsOnBoard().add(magicCard);
        //assertEquals(2, board.getInventories().get(playerList[0]).getInvocation().getCardsOnBoard().size());

        amsug.use(board, player);

        assertEquals(0, board.getInventories().get(playerList[0]).getInvocation().getCardsOnBoard().size());
        assertEquals(0, board.getInventories().get(playerList[0]).getInvocation().getCurrentInvocations());
        assertEquals(7, board.getInventories().get(playerList[0]).getEnergyStock().getNbMaxEnergy());
    }


    @Test
    void isMagicTest() {
        assertFalse(amsug.isMagic());
    }

    @Test
    void isActivableTest() {
        assertFalse(amsug.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(amsug.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.NONE, amsug.getEffectType());
    }
}
