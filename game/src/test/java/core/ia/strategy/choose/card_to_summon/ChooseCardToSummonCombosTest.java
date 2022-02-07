package core.ia.strategy.choose.card_to_summon;

import core.board.Board;
import core.board.FacadeIA;
import core.cards.Card;
import core.cards.Deck;
import core.cards.effects.AmsugLongcoupEffect;
import core.cards.effects.BottesTemporelles;
import core.cards.effects.MainFortuneEffect;
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

public class ChooseCardToSummonCombosTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board;
    private FacadeIA facadeIA;
    private ChooseCardToSummonCombos chooseCardToSummonCombos;
    private Card card1, card2, card3;
    private Player player;
    private Player[] players;
    private Deck deck;


    @BeforeEach
    void setUp() {
        players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        controller.initGame(players, config);
        board = controller.getBoard();
        player = players[0];

        facadeIA = Mockito.mock(FacadeIA.class);
        player.setFacadeIA(facadeIA);

        chooseCardToSummonCombos = new ChooseCardToSummonCombos();
        player.setChooseCardToSummon(chooseCardToSummonCombos);
        player.setFacadeIA(facadeIA);
        deck = new Deck();

        card1 = Mockito.mock(BottesTemporelles.class);
        card2 = Mockito.mock(AmsugLongcoupEffect.class);
        card3 = Mockito.mock(MainFortuneEffect.class);
        when(facadeIA.getSummonableCards()).thenReturn(
                new ArrayList<>(List.of(card1, card2, card3))
        );

        board.initHand(players[0]);

    }

    @Test
    void choose() {
        Card card = chooseCardToSummonCombos.choose(player);
        assertEquals(card1, card);
    }

}
