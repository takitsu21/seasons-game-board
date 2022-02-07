package core.ia.strategy.choose.copy_energy_from_player;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.Card;
import core.cards.Deck;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChoosePlayerEnergyToCopyPrefCardPointsTest {
    private final Config config = new Config();
    private final GameController controller = new GameController();
    private Board board;
    private Deck deck;
    private ChoosePlayerEnergyToCopyPrefCardPoints choosePlayerEnergyToCopyPrefCardPoints;
    private Player player1, player2;
    private Player player;
    private Inventory inventory, inventory1, inventory2;


    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(3, config);
        controller.initGame(players, config);
        board = controller.getBoard();
        deck = new Deck();

        choosePlayerEnergyToCopyPrefCardPoints = new ChoosePlayerEnergyToCopyPrefCardPoints();

        player = players[0];
        player1 = players[1];
        player2 = players[2];
        board.initPLayersInventory();
        inventory = board.getInventories().get(player);
        inventory1 = board.getInventories().get(player1);
        inventory2 = board.getInventories().get(player2);
        inventory1.getEnergyStock().setTheEnergyStock(new ArrayList<>(List.of(
                Energy.WIND,
                Energy.WIND,
                Energy.WIND
        )));

        inventory2.getEnergyStock().setTheEnergyStock(new ArrayList<>(List.of(
                Energy.WIND
        )));
        Card card = deck.findCard(16);
        Card card2 = deck.findCard(15);
        board.initHand(player);
        inventory.getHand().setCardsInHand(new ArrayList<>(List.of(card, card2)));
    }

    @Test
    void choose() {
        assertEquals(player1, choosePlayerEnergyToCopyPrefCardPoints.choose(player));
    }
}
