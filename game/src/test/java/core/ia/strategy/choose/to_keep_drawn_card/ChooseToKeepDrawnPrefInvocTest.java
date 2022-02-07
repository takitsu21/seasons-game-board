package core.ia.strategy.choose.to_keep_drawn_card;

import core.board.Board;
import core.board.FacadeIA;
import core.cards.Card;
import core.cards.effects.BottesTemporelles;
import core.cards.effects.CaliceDivinEffect;
import core.cards.effects.PotionPuissanceEffect;
import core.cards.effects.PotionRevesEffect;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.ia.strategy.choose.player_action.ChoosePlayerActionCristallizePref;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChooseToKeepDrawnPrefInvocTest {
    private final Config config = new Config();
    private final GameController controller = new GameController();
    private Board board;
    Player player;
    private FacadeIA facadeIA;
    private ChooseToKeepDrawnCardPrefInvoc chooseToKeepDrawnCard;
    private Card card1;

    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        controller.initGame(players, config);
        player = players[0];

        controller.getBoard().initPLayersInventory();
        controller.getBoard().initHand(player);

        board= controller.getBoard();

        facadeIA = new FacadeIA(board, player, config);
        chooseToKeepDrawnCard = new ChooseToKeepDrawnCardPrefInvoc();
        player.setFacadeIA(facadeIA);
    }

    @Test
    void chooseTrue() {
        assertTrue(chooseToKeepDrawnCard.choose(player, Mockito.mock(CaliceDivinEffect.class)));
        assertTrue(chooseToKeepDrawnCard.choose(player, Mockito.mock(PotionRevesEffect.class)));
        assertTrue(chooseToKeepDrawnCard.choose(player, Mockito.mock(PotionPuissanceEffect.class)));

    }

    @Test
    void chooseFalseHandNotEmpty() {
        card1 = Mockito.mock(Card.class);

        assertFalse(chooseToKeepDrawnCard.choose(player, card1));
    }

    @Test
    void chooseTrueHandEmpty() {
        card1 = Mockito.mock(Card.class);
        board.getInventories().get(player).getCardsInHand().clear();

        assertTrue(chooseToKeepDrawnCard.choose(player, card1));
    }

    @Test
    void chooseFalseHandEmptyLastYear() {
        card1 = Mockito.mock(Card.class);
        board.getInventories().get(player).getCardsInHand().clear();
        board.setCurrentCursor(36);
        board.getYear().updateYearAndSeason(board);

        assertFalse(chooseToKeepDrawnCard.choose(player, card1));
    }
}