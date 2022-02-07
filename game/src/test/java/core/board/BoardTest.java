package core.board;

import core.game.App;
import core.game.GameController;
import core.game.TurnBasedGameLoop;
import core.ia.Player;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BoardTest {
    private GameController controller;
    private Board board;


    @BeforeEach
    void setUp() {
        Config config = new Config();
        controller = new GameController();
        Player[] playerList = App.initPlayer(2, config);
        TurnBasedGameLoop turnBasedGameLoop = new TurnBasedGameLoop(playerList, config);
        controller.initGame(playerList, config);
        controller.getBoard().initPLayersInventory();
        board = controller.getBoard();
        board.initHand(playerList[0]);
        board.initHand(playerList[1]);
    }

    //   Make test init player inventory
    @Test
    void initPlayersTest() {
        //initPlayers est appelée dans le constructeur, pas besoin de rappeler la méthode

        //check si les joueurs sont bien tous initiés
        assertEquals(2, board.getPlayers().length);

        //check s'il y a bien autant de joueurs que d'inventaires
        assertEquals(2, board.getInventories().size());

        //check si tous les joueurs sont bien liés à un inventaire
        for (Player player : board.getPlayers()) {
            assertNotNull(board.getInventories().get(player));
        }

        //on pourrait test si les mains sont bien initiées
    }


    @Test
    void findWinnerTest() {

        //test pour un choix simple de gagnant
        for (int i = 0; i < board.getPlayers().length; i++) {
            Player player = board.getPlayers()[i];
            board.getInventories().get(player).setPrestigePoints(i * 1000);
        }
        List<Player> winners = board.findWinner();
        assertEquals(board.getPlayers()[board.getPlayers().length - 1], winners.get(0));
        assertEquals(1, winners.size());

        //test pour un cas avec plusieurs gagnants
        for (Player player : board.getPlayers()) {
            board.getInventories().get(player).setPrestigePoints(1000);
        }
        winners = board.findWinner();
        assertEquals(board.getPlayers().length, winners.size());
    }

}