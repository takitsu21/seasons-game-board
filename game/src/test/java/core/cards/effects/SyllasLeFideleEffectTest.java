package core.cards.effects;

import core.board.Board;
import core.cards.Card;
import core.cards.Deck;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class SyllasLeFideleEffectTest {
    private Card syllas;
    private Board board;
    private Player player;
    private Player[] playerList;


    @BeforeEach
    void setUp() {
        Config config = new Config();
        Deck fullDeck = new Deck();
        syllas = fullDeck.findCard(10);
        GameController controller = new GameController();
        playerList = App.initPlayer(4, config);
        player = playerList[0];
        controller.initGame(playerList, config);
        board = controller.getBoard();
        for (int i=0; i<playerList.length; i++){
            board.initHand(playerList[i]);
        }
    }

    @Test
    void useTest() {
        Card card1 = board.getDeck().drawCard();
        Card card2 = board.getDeck().drawCard();
        Card card3 = board.getDeck().drawCard();
        for (Player currentPlayer : playerList) {
            //on ajoute 3 cartes sur le board de chaque joueur
            board.getInventories().get(currentPlayer).getInvocation().getCardsOnBoard().addAll(new ArrayList<>(Arrays.asList(card1, card2, card3)));
        }
        syllas.use(board, player);
        for (Player currentPlayer : playerList) {
            if (currentPlayer != player) {
                //on vérifie si tous les adversaires de player ont une carte en moins sur leur board
                assertEquals(2, board.getInventories().get(currentPlayer).getInvocation().getCardsOnBoard().size());
            }
        }
    }

    @Test
    void useTestCardOnBordEmpty() {
        syllas.use(board, player);

        for (Player currentPlayer : playerList) {
            if (currentPlayer != player) {
                //on vérifie si tous les adversaires de player ont une carte en moins sur leur board
                assertEquals(0, board.getInventories().get(currentPlayer).getInvocation().getCardsOnBoard().size());
            }
        }
    }

    @Test
    void isMagic() {
        assertFalse(syllas.isMagic());
    }

    @Test
    void isActivableTest() {
        assertFalse(syllas.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(syllas.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.NONE, syllas.getEffectType());
    }
}
