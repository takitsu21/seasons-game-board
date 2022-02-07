package core.ia.strategy.choose.use_bonus_card;

import core.board.Board;
import core.board.FacadeIA;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChooseUseBonusCardFalseTest {
    private final Config config = new Config();
    private final GameController controller = new GameController();
    private final Board board = controller.getBoard();
    private FacadeIA facadeIA;
    private ChooseUseBonusCardFalse chooseUseBonusCardFalse;
    private Player player;

    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = players[0];

        facadeIA = new FacadeIA(board, player, config );
        player.setFacadeIA(facadeIA);

        chooseUseBonusCardFalse = new ChooseUseBonusCardFalse();

    }

    @Test
    void choose() {
        assertFalse(chooseUseBonusCardFalse.choose(player));
    }
}