package core.ia.strategy.choose.energy_to_get;

import core.board.Board;
import core.board.FacadeIA;
import core.board.enums.Energy;
import core.cards.Card;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.ia.inventory.Invocation;
import core.ia.inventory.PlayerEnergyStock;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ChooseEnergyToGetPrefPermanentTest {
    private ChooseEnergyToGetPrefPermanent chooseEnergyToGetPrefPermanent;
    private Player player;
    private Board board;
    Inventory playerInv;
    private FacadeIA facade;

    @BeforeEach
    void setup() {
        chooseEnergyToGetPrefPermanent = new ChooseEnergyToGetPrefPermanent();
        player = Mockito.mock(Player.class);

        Invocation invocation = Mockito.mock(Invocation.class);
        when(invocation.getCardsOnBoard()).thenReturn(new ArrayList<>());

        PlayerEnergyStock playerEnergyStock = Mockito.mock(PlayerEnergyStock.class);

        playerInv = Mockito.mock(Inventory.class);
        when(playerInv.getInvocation()).thenReturn(invocation);
        when(playerInv.getEnergyStock()).thenReturn(playerEnergyStock);

        Map<Player, Inventory> inventories = new HashMap<>();
        inventories.put(player, playerInv);

        board = Mockito.mock(Board.class);
        when(board.getInventories()).thenReturn(inventories);

        Config config = Mockito.mock(Config.class);
        facade = new FacadeIA(board, player, config);

        when(player.getFacadeIA()).thenReturn(facade);
    }

    @Test
    void chooseWithOnePermanentCard() {
        Card permanentCard = Mockito.mock(Card.class);
        when(permanentCard.hasPermanentEffect()).thenReturn(true);
        when(permanentCard.getEnergyCost()).thenReturn(List.of(Energy.FIRE));

        Card nonPermanentCard = Mockito.mock(Card.class);
        when(nonPermanentCard.hasPermanentEffect()).thenReturn(false);
        when(nonPermanentCard.getEnergyCost()).thenReturn(List.of(Energy.EARTH));

        when(playerInv.getCardsInHand()).thenReturn(new ArrayList<>(List.of(permanentCard, nonPermanentCard)));
        when(playerInv.getEnergyStock().hasEnoughEnergy(permanentCard.getEnergyCost(), playerInv.getInvocation().getCardsOnBoard())).thenReturn(false);
        when(playerInv.getEnergyStock().hasEnoughEnergy(nonPermanentCard.getEnergyCost(), playerInv.getInvocation().getCardsOnBoard())).thenReturn(false);

        assertEquals(Energy.FIRE, chooseEnergyToGetPrefPermanent.choose(player));
    }
}
