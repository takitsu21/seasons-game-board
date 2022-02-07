package core.ia;

import core.board.Board;
import core.cards.Card;
import core.dice.Dice;
import core.dice.Face;
import core.game.App;
import core.game.GameController;
import core.ia.inventory.Inventory;
import core.util.Config;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Quand;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class PlayerCucumberTest {
    private Config config;
    private Player player;
    private Inventory inventory;
    private PlayerTurnLoop playerTurnLoop;
    private Card card1;
    private Card card2;
    private Dice dice;
    private GameController controller;
    private Board board;

    //Cucumber test for invocation place


    @Etantdonné("un {string}")
    public void un(String arg0) {
        config = new Config();
        controller = new GameController();
        Player[] playerList = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        playerList[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        player = playerList[0];
        controller.initGame(playerList, config);
        controller.getBoard().initPLayersInventory();
        board = controller.getBoard();
        inventory = board.getInventories().get(player);
        playerTurnLoop = new PlayerTurnLoop(player, controller);

    }

    @Quand("le joueur à {int} place d'invocation")
    public void qaundLeJoueurNPlaceDInvocation(Integer n) {
        inventory.getInvocation().setInvocationPoints(n);
    }

    @Et("lance le dé qui tombe sur une face a {string}")
    public void lanceLeDéQuiTombeSurUneFaceAEtoile(String arg0) {
        dice = Mockito.mock(Dice.class);
        inventory.setCurrentDice(dice);
        if (Objects.equals(arg0, "étoile")) {
            when(dice.getCurrentFace()).thenReturn(new Face(0, false, false, true, 0));
        } else {
            when(dice.getCurrentFace()).thenReturn(new Face(0, false, false, false, 0));
        }
    }

    @Et("lance le dé qui tombe sur une face a {int} cristaux")
    public void lanceLeDéQuiTombeSurUneFaceACristaux(int arg0) {
        dice = Mockito.mock(Dice.class);
        inventory.setCurrentDice(dice);
        when(dice.getCurrentFace()).thenReturn(new Face(arg0, false, false, false, 0));
    }

    @Alors("il gagne {int} place d'invocation")
    public void ilGagneUnePlaceDInvocation(Integer n) {
        int nbInvocationBase = inventory.getInvocation().getInvocationPoints();
        playerTurnLoop.actionDice();
        assertEquals(nbInvocationBase + n, inventory.getInvocation().getInvocationPoints());
    }

    //cucumber tests for win crystals

    @Quand("le joueur à {int} cristaux")
    public void leJoueurACristaux(int arg0) {
        inventory.setCrystals(arg0);
    }

    @Alors("il gagne {int} cristaux")
    public void ilGagneCristaux(int arg0) {
        int nbCrystals = inventory.getCrystals();
        playerTurnLoop.actionDice();
        assertEquals(nbCrystals + arg0, inventory.getCrystals());
    }

    @Quand("le joueur à {int} points de prestige")
    public void leJoueurÀPointsDePrestige(int arg0) {
        inventory.setPrestigePoints(arg0);
        board.initHand(player);
        inventory.getHand().setCardsInHand(new ArrayList<>());
    }

    @Et("a une carte qui rapporte {int} points de prestige sur le plateau")
    public void aUneCarteQuiRapportePointsDePrestigeSurLePlateau(int arg0) {
        card1 = Mockito.mock(Card.class);
        when(card1.getPrestigePointValue()).thenReturn(arg0);
        when(card1.getCrystalCost()).thenReturn(0);
        when(card1.getEnergyCost()).thenReturn(new ArrayList<>() {
        });
        inventory.addCard(card1);
        inventory.getInvocation().setInvocationPoints(15);
        assertTrue(inventory.summonCard(card1, controller.getBoard()));
    }

    @Et("a une deuxieme carte qui rapporte {int} points de prestige sur le plateau")
    public void aUneDeuxiemeCarteQuiRapportePointsDePrestigeSurLePlateau(int arg0) {
        card2 = Mockito.mock(Card.class);
        when(card2.getPrestigePointValue()).thenReturn(arg0);
        when(card2.getCrystalCost()).thenReturn(0);
        when(card2.getEnergyCost()).thenReturn(new ArrayList<>() {
        });
        inventory.addCard(card2);
        assertTrue(inventory.summonCard(card2, controller.getBoard()));

    }

    @Alors("il gagne à {int} points de prestige")
    public void ilGagneÀPointsDePrestige(int arg0) {
        inventory.calculatePrestigePoint();
        assertEquals(arg0, inventory.getPrestigePoints());
    }

}

