package core.ia.strategy.choose.bonus;

import core.board.Board;
import core.board.FacadeIA;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.ia.inventory.BonusType;
import core.ia.strategy.choose.Context;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ChooseBonusRandomTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private Board board = controller.getBoard();
    private FacadeIA facadeIA;
    private ChooseBonusRandom chooseBonusRandom;
    private BonusType bonusType;
    private Player player;

    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = players[0];
        facadeIA = Mockito.mock(FacadeIA.class);
        player.setFacadeIA(facadeIA);

        chooseBonusRandom = new ChooseBonusRandom(Context.everyTime, null);
        bonusType = BonusType.ADD_INVOCATION;

    }

    @Test
    void choose() {
        when(facadeIA.getPossibleBonus()).thenReturn(List.of(BonusType.ADD_INVOCATION));
        assertEquals(bonusType, chooseBonusRandom.choose(player));
    }
}
