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
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

class NariaLaProphetesseTest {
    private Card naria;
    private Board board;

    private Player[] playerList;
    private Card nullCard;

    @Mock
    private Player player;

    @BeforeEach
    void setUp() {
        Config config = new Config();
        Deck fullDeck = new Deck();
        naria = fullDeck.findCard(12);
        GameController controller = new GameController();
        playerList = App.initPlayer(4, config);
        player = playerList[0];
        controller.initGame(playerList, config);
        board = controller.getBoard();
        board.setDeck(fullDeck);
        nullCard = null;
//        player = Mockito.mock(Player.class);
        for (int i=0; i<playerList.length; i++){
            board.initHand(playerList[i]);
        }
    }

    @Test
    void useTest() {
        int[] oldHandSizes = new int[4];
        for (int i = 0; i < oldHandSizes.length; i++) {
            oldHandSizes[i] = board.getInventories().get(playerList[i]).getHand().getCardsInHand().size();
        }
        naria.use(board, player);
        for (int i = 0; i < playerList.length; i++) {
            assertEquals(oldHandSizes[i] + 1, board.getInventories().get(playerList[i]).getHand().getCardsInHand().size());
        }
    }

/*    @Test
    void chooseCardNullTest(){
        when(player.chooseCardComeBackInHand(new ArrayList<>())).thenReturn(nullCard);
        naria.use(board,player);
    }
*/

    @Test
    void isMagic() {
        assertFalse(naria.isMagic());
    }

    @Test
    void isActivableTest() {
        assertFalse(naria.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(naria.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.NONE, naria.getEffectType());
    }
}
