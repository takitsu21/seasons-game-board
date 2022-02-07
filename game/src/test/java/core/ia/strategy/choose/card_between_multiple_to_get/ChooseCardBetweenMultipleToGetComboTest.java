package core.ia.strategy.choose.card_between_multiple_to_get;

import core.board.Board;
import core.board.FacadeIA;
import core.cards.Card;
import core.cards.Deck;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChooseCardBetweenMultipleToGetComboTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board = controller.getBoard();
    private FacadeIA facadeIA;
    private ChooseCardBetweenMultipleToGetCombos chooseCardBetweenMultipleToGetCombos;
    private Card card1, card2, card3;
    private Player player;
    private Deck deck;


    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        controller.initGame(players, config);
        player = players[0];

        facadeIA = new FacadeIA(board, player, config );
        player.setFacadeIA(facadeIA);

        chooseCardBetweenMultipleToGetCombos = new ChooseCardBetweenMultipleToGetCombos();
        deck = controller.getBoard().getDeck();
        card1 = deck.findCard(7); // botte temporelles
        card2 = deck.findCard(1); // carte sans combo donné
        card3 = deck.findCard(17); // amsug


    }

    @Test
    void choose() {
        assertEquals(card1, chooseCardBetweenMultipleToGetCombos.choose(player, List.of(card1, card2, card3)));
    }
}
