package core.cards;

import core.board.Board;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.CardUtil;
import core.util.Config;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    private GameController controller;
    private Board board;
    private Deck deck;
    private Player p1;
    private Player p2;
    private Card card;

    @BeforeEach
    void setUp() {
        Config config = new Config();
        Player[] playerList = App.initPlayer(2, config);
        controller = new GameController();
        p1 = playerList[0];
        p2 = playerList[1];
        controller.initGame(playerList, config);
        board = controller.getBoard();
        controller.getBoard().initPLayersInventory();
        board.initHand(p1);
        board.initHand(p2);
        deck = new Deck();
        card = deck.findCard(10);
    }

    @Test
    void defaultIsMagic() {
        assertFalse(card.isMagic());
    }

    @Test
    void defaultIsActivable() {
        assertFalse(card.isActivable());
    }

    @Test
    void useCardTest() {
        Inventory inventory = board.getInventories().get(p1);
        Card cardToPlay1 = board.findCard("amulette d'air", deck.getDeck());
        Card cardToPlay2 = board.findCard("amulette de feu", deck.getDeck());

        int oldInvocGauge = inventory.getInvocation().getInvocationPoints();
        assertEquals(0, oldInvocGauge);
        cardToPlay1.use(board, p1);
        assertEquals(oldInvocGauge + 2, inventory.getInvocation().getInvocationPoints());


        int oldHandSize = inventory.getHand().getCardsInHand().size();
        cardToPlay2.use(board, p1);
        assertTrue(oldHandSize < inventory.getHand().getCardsInHand().size());

    }

    @Test
    void cardFactoryTest() {
        try {
            final JSONParser parser = new JSONParser();

            Object jsonObj = parser.parse(new FileReader(Paths.get(CardFactory.class.getClassLoader().getResource("cards.json").
                    toURI()).toFile().getAbsolutePath()));
            JSONArray json = (JSONArray) ((JSONObject) jsonObj).get("data");

            for (int i = 0; i < json.size(); i++) {
                JSONObject data = (JSONObject) json.get(i);
                JSONObject cost = (JSONObject) data.get("cost");

                Class<?> cl = CardFactory.getINSTANCE().getCard(((Long) data.get("id")).intValue());

                Object[] obj = {
                        i + 1,
                        cost.get("players"),
                        ((Long) cost.get("crystal")).intValue(),
                        CardUtil.convertJsonArray((JSONArray) cost.get("energy")),
                        CardUtil.strFrequencyToEffectFrequency((String) data.get("frequence")),
                        data.get("name"),
                        ((Long) data.get("points")).intValue()
                };
                Card card = CardFactory.getINSTANCE().createCard(cl, obj);
                assertEquals(card.getClass().getName(), cl.getName());
            }
        } catch (ReflectiveOperationException | URISyntaxException | IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void dontFindCard() {
        assertNull(board.findCard("error", deck.getDeck()));
    }

}
