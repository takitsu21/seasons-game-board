package core.board;

import core.dice.Dice;
import core.dice.Face;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.util.Config;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Quand;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class YearCucumberTest {
    private int cursor;
    private Year year;
    private Dice dice;
    private Config config = new Config();
    private Board board;
    private Player player;
    private GameController controller=new GameController();

    @Etantdonné("un {string} veux pouvoir changer d'année")
    public void unVeuxPouvoirChangerDAnnée(String arg0) {
        PlayerFactory playerFactory = new PlayerFactory(config);
        player = playerFactory.getPlayer(TypeAIPlayer.RANDOM, arg0);
        Player[] players = new Player[1];
        players[0] = player;
        controller.initGame(players, config);
        board = new Board(players, config);
        board.initPLayersInventory();
        board.initHand(player);
    }

    @Quand("le curseur est à la case {int}")
    public void leCurseurEstÀLaCase(Integer arg0) {
        cursor = arg0;
    }

    @Et("dans l'année {int}")
    public void dansLAnnée(Integer arg0) {
        year = new Year(arg0, config.getNbYears());
    }

    @Et("lance le dé qui tombe sur une face a {int} points pour avancer dans L'année")
    public void lanceLeDéQuiTombeSurUneFaceAPointsPourAvancerDansLAnnée(int arg0) {
        dice = Mockito.mock(Dice.class);
        when(dice.getCurrentFace()).thenReturn(new Face(0, false, false, false, arg0));
    }

    @Alors("il passe à l'année {int}")
    public void ilPasseÀLAnnée(Integer arg0) {
        int newCursor = cursor + dice.getCurrentFace().getNbAdvance();

        board.setCurrentCursor(newCursor);
        assertTrue(year.updateYearAndSeason(board));
        assertEquals(arg0, year.getNbYear());
    }

}
