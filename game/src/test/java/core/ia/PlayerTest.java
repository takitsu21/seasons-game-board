package core.ia;

import core.board.Board;
import core.board.FacadeIA;
import core.exception.PlayerException;
import core.game.GameController;
import core.ia.inventory.BonusType;
import core.ia.strategy.choose.bonus.ChooseBonus;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class PlayerTest {

    Config config;
    GameController controller;
    PlayerFactory playerFactory;
    Player player;
    FacadeIA facadeIA;
    Board board;

    @BeforeEach
    void setUP() {
        config = new Config();
        controller = new GameController();
        playerFactory = new PlayerFactory(config);
        player = playerFactory.getPlayer(TypeAIPlayer.CHOOSE_FIRST, "IA");
        Player[] players = new Player[1];
        players[0] = player;
        controller.initGame(players, config);
        board = controller.getBoard();
        board.initPLayersInventory();
        facadeIA = new FacadeIA(board, player, config );
        player.setFacadeIA(facadeIA);
    }


    @Test
    void chooseBonusTest() {
        assertEquals(BonusType.class, player.chooseBonus().getClass());
    }


    @Test
    void PlayerExceptionTest() {
        assertThrows(PlayerException.class, () -> playerFactory.getPlayer(TypeAIPlayer.ERROR, "IA1"));
    }

    @Test
    void getTypeAIPlayerTest() {
        assertEquals(TypeAIPlayer.CHOOSE_FIRST, player.getTypeAIPlayer());
    }

    @Test
    void getFacadeIATest() {
        assertEquals(facadeIA, player.getFacadeIA());
    }


}
