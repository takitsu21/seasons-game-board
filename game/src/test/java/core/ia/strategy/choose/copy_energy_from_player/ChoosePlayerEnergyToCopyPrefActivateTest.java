package core.ia.strategy.choose.copy_energy_from_player;

import core.board.Board;
import core.board.FacadeIA;
import core.board.enums.Energy;
import core.cards.Card;
import core.cards.Deck;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.inventory.Hand;
import core.ia.inventory.Inventory;
import core.ia.inventory.PlayerEnergyStock;
import core.util.Config;
import io.cucumber.java.hu.De;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ChoosePlayerEnergyToCopyPrefActivateTest {
    private final Config config = new Config();
    private final GameController controller = new GameController();
    private Board board;
    private Deck deck;
    private ChoosePlayerEnergyToCopyPrefActivate choosePlayerEnergyToCopyPrefActivate;
    private Player player1;
    private Player player;
    private Inventory inventory, inventory1;


    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(2, config);
        controller.initGame(players, config);
        board = controller.getBoard();
        deck = new Deck();

        choosePlayerEnergyToCopyPrefActivate = new ChoosePlayerEnergyToCopyPrefActivate();

        player = players[0];
        player1 = players[1];
        board.initPLayersInventory();
        inventory = board.getInventories().get(player);
        inventory1 = board.getInventories().get(player1);
        inventory1.getEnergyStock().setTheEnergyStock(new ArrayList<>(List.of(
                Energy.WIND,
                Energy.WIND,
                Energy.WIND
        )));

        Card card = deck.findCard(16);
        Card card2 = deck.findCard(15);
        board.initHand(player);
        inventory.getHand().setCardsInHand(new ArrayList<>(List.of(card, card2)));
    }

    @Test
    void choose() {
        assertEquals(player1, choosePlayerEnergyToCopyPrefActivate.choose(player));
    }
}
