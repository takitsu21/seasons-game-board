package core.ia.strategy.choose.energy_to_throw;

import core.board.Board;
import core.board.FacadeIA;
import core.board.enums.Energy;
import core.cards.Card;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ChooseEnergyToThrowPrefInvocTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board;
    private FacadeIA facadeIA;
    private ChooseEnergyToThrowPrefInvoc chooseEnergyToThrowPrefInvoc;
    private Card card1, card2;
    private Player player;
    private Player[] players;


    @BeforeEach
    void setUp() {
        players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        controller.initGame(players, config);
        board = controller.getBoard();
        player = players[0];

        facadeIA = new FacadeIA(board, player, config);
        player.setFacadeIA(facadeIA);

        chooseEnergyToThrowPrefInvoc = new ChooseEnergyToThrowPrefInvoc();
        card1 = Mockito.mock(Card.class);
        card2 = Mockito.mock(Card.class);

        board.getInventories().get(players[0]).getInvocation().setInvocationPoints(1);

        board.initHand(players[0]);

        board.getInventories().get(player).addCrystals(100);
        board.getInventories().get(player).getEnergyStock().addEnergy(Energy.FIRE);
        board.getInventories().get(player).getEnergyStock().addEnergy(Energy.WIND);

    }

    @Test
    void chooseWind() {
        when(card1.getCrystalCost()).thenReturn(0);
        when(card2.getCrystalCost()).thenReturn(0);
        when(card1.getEnergyCost()).thenReturn(List.of(Energy.FIRE));
        when(card2.getEnergyCost()).thenReturn(List.of());

        board.getInventories().get(player).getHand().setCardsInHand(new ArrayList<>(List.of(card1, card2)));

        assertEquals(Energy.WIND, chooseEnergyToThrowPrefInvoc.choose(player));
    }

    @Test
    void chooseEarth() {
        when(card1.getCrystalCost()).thenReturn(0);
        when(card2.getCrystalCost()).thenReturn(0);
        when(card1.getEnergyCost()).thenReturn(List.of(Energy.FIRE));
        when(card2.getEnergyCost()).thenReturn(List.of(Energy.WIND));

        board.getInventories().get(player).getHand().setCardsInHand(new ArrayList<>(List.of(card1, card2)));
        board.getInventories().get(player).getEnergyStock().addEnergy(Energy.EARTH);

        assertEquals(Energy.EARTH, chooseEnergyToThrowPrefInvoc.choose(player));
    }

}