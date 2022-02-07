package core.ia;

import core.board.Board;
import core.game.GameController;
import core.ia.inventory.Inventory;
import core.util.Config;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Quand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandCucumberTest {
    private Config config = new Config();
    private Inventory inventory;
    private int nextYear;
    private Board board;
    private Player player;
    private GameController controller=new GameController();

    @Etantdonné("un inventaire je veux que mes cartes en main évolue en fonction des années.")
    public void unInventaireJeVeuxQueMesCartesEnMainÉvolueEnFonctionDesAnnées() {
        PlayerFactory playerFactory = new PlayerFactory(config);
        player = playerFactory.getPlayer(TypeAIPlayer.RANDOM, "joueur");
        Player[] players = new Player[1];
        players[0] = player;
        board = new Board(players, config);
    }

    @Quand("la main est initialisé")
    public void laMainEstInitialisé() {
        board.initPLayersInventory();
        inventory = board.getInventories().get(player);
        board.initHand(player);
    }

    @Et("le curseur est dans l'année {int}")
    public void leCurseurEstDansLAnnée(int arg0) {
        inventory.getHand().updateHand(arg0, config.getNbYears());
    }

    @Et("le curseur passe à l'année {int}")
    public void leCurseurPasseÀLAnnée(int arg0) {
        nextYear = arg0;
    }

    @Alors("l'inventaire passe de {int} à {int} cartes en main")
    public void lInventairePasseDeÀCartesEnMain(int arg0, int arg1) {
        assertEquals(inventory.getHand().getCardsInHand().size(), arg0);
        inventory.getHand().updateHand(nextYear, config.getNbYears());
        assertEquals(inventory.getHand().getCardsInHand().size(), arg1);
    }


    @Alors("l'inventaire à {int} cartes en main")
    public void lInventaireÀCartesEnMain(int arg0) {
        assertEquals(inventory.getHand().getCardsInHand().size(), arg0);
    }
}
