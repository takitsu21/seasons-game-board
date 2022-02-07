package core.board;

import core.board.enums.Seasons;
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

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SeasonCucumberTest {
    private final Config config = new Config();
    private int cursor;
    private Year year;
    private Dice dice;
    private Seasons season;
    private String strSeason;
    private Board board;
    private Player player;
    private GameController controller=new GameController();


    @Etantdonné("un {string} veux pouvoir changer de saison")
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

    @Quand("le curseur est à l'année {int} et case {int}")
    public void leCurseurEstÀLAnnéeEtCase(int arg0, int arg1) {
        year = new Year(arg0);
        cursor = arg1;
    }

    @Quand("le curseur est à la case {int} de saison {string}")
    public void leCurseurEstÀLaCaseDeSaison(int arg0, String arg1) {
        year = new Year(1);
        strSeason = arg1;
        switch (arg1) {
            case "printemps" -> season = Seasons.SPRING;
            case "été" -> season = Seasons.SUMMER;
            case "automne" -> season = Seasons.AUTUMN;
            case "hiver" -> season = Seasons.WINTER;
        }
        cursor = arg0 + 3 * season.ordinal();
//        year.updateYearAndSeason(cursor);

    }

    @Et("lance le dé qui tombe sur une face a {int} points pour avancer dans la saison")
    public void lanceLeDéQuiTombeSurUneFaceAPointsPourAvancerDansLaSaison(int arg0) {
        dice = Mockito.mock(Dice.class);
        when(dice.getCurrentFace()).thenReturn(new Face(0, false, false, false, arg0));
    }

    @Alors("il passe à la saison {string}")
    public void ilPasseÀLaSaison(String arg0) {
        int newCursor = cursor + dice.getCurrentFace().getNbAdvance();
        switch (strSeason) {
            case "printemps" -> assertEquals(Seasons.SPRING, season);
            case "été" -> assertEquals(Seasons.SUMMER, season);
            case "automne" -> assertEquals(Seasons.AUTUMN, season);
            case "hiver" -> assertEquals(Seasons.WINTER, season);
        }
        board.setCurrentCursor(newCursor);
        year.updateYearAndSeason(board);
        switch (arg0) {
            case "printemps" -> assertEquals(Seasons.SPRING, year.getCurrentSeason());
            case "été" -> assertEquals(Seasons.SUMMER, year.getCurrentSeason());
            case "automne" -> assertEquals(Seasons.AUTUMN, year.getCurrentSeason());
            case "hiver" -> assertEquals(Seasons.WINTER, year.getCurrentSeason());
            default -> fail();
        }
    }


}
