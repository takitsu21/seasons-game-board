package core.cards.effects;

import core.board.Board;
import core.cards.Card;
import core.cards.Deck;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.ia.inventory.Inventory;
import core.ia.strategy.choose.card_between_multiple_to_get.ChooseCardBetweenMultipleToGetRandom;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FireAmuletteEffectTest {
    private Card cardToPlay;
    private Deck deck;
    private Board board;
    private Player player;


    @BeforeEach
    void setUp() {
        Config config = new Config();
        GameController controller = new GameController();
        Player[] playerList = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        playerList[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = playerList[0];
        player.setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetRandom());
        controller.initGame(playerList, config);
        board = controller.getBoard();
        deck = new Deck();
        cardToPlay = deck.findCard("amulette de feu");
        board.initHand(player);
    }

    @Test
    void useTestDeckNotEmpty() {

        //Au moment où vous mettez en jeu l’Amulette de feu, piochez
        //4 cartes pouvoir. Consultez-les et gardez-en une que vous placez
        //dans votre main. Placez les 3 cartes restantes dans la défausse

        Inventory inventory = board.getInventories().get(player);
        int previousDeckSize = deck.size();
        int previousHandSize = inventory.getHand().getCardsInHand().size();
        cardToPlay.use(board, player);

        //test si les 4 cartes sont bien enlevées du deck et les 3 non choisies sont jetées
        assertEquals(previousDeckSize - 4, deck.size());

        //test si la main a bien une carte en plus
        assertEquals(previousHandSize + 1, inventory.getHand().getCardsInHand().size());
    }

    @Test
    void useTestDeckEmpty() {

        //Au moment où vous mettez en jeu l’Amulette de feu, piochez
        //4 cartes pouvoir. Consultez-les et gardez-en une que vous placez
        //dans votre main. Placez les 3 cartes restantes dans la défausse

        Inventory inventory = board.getInventories().get(player);
        int previousHandSize = inventory.getHand().getCardsInHand().size();

        deck.getDeck().clear();
        assertEquals(0, deck.getDeck().size());
        assertEquals(0, deck.getDiscardPool().size());

        cardToPlay.use(board, player);

        //test si le joueur ne reçoit rien (et si le deck ne bouge pas) lorsque le deck est vide
        assertEquals(0, deck.size());
        assertEquals(previousHandSize, inventory.getHand().getCardsInHand().size());
    }

    @Test
    void isMagic() {
        assertTrue(cardToPlay.isMagic());
    }

    @Test
    void isActivableTest() {
        assertFalse(cardToPlay.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(cardToPlay.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.NONE, cardToPlay.getEffectType());
    }
}

