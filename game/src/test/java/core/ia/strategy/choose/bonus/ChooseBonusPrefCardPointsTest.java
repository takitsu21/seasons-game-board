package core.ia.strategy.choose.bonus;

import core.board.FacadeIA;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.ia.inventory.BonusType;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ChooseBonusPrefCardPointsTest {
    private Config config = new Config();
    private GameController controller = new GameController();
    private ChooseBonusPrefCardPoints chooseBonusPrefCardPoints = new ChooseBonusPrefCardPoints();
    private FacadeIA facadeIA;
    private Player player;

    @BeforeEach
    void setUp() {
        Player[] players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = players[0];
        facadeIA = Mockito.mock(FacadeIA.class);
        player.setFacadeIA(facadeIA);
    }

    @Test
    void chooseTest() {
        List<BonusType> bonusTypeList = new ArrayList<>();
        when(facadeIA.getPossibleBonus()).thenReturn(bonusTypeList);
        bonusTypeList.add(BonusType.ADD_INVOCATION);
        bonusTypeList.add(BonusType.CRYSTALLIZE);
        bonusTypeList.add(BonusType.CHANGE_ENERGY);

        BonusType bonus = chooseBonusPrefCardPoints.choose(player);
        assertEquals(BonusType.ADD_INVOCATION, bonus);          // Choose Crystallize if Crystallize in the list
    }
}
