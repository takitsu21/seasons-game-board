package core.board;

import core.board.enums.Energy;
import core.board.enums.Seasons;
import core.game.GameController;
import core.ia.EnumPlayerAction;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.ia.inventory.BonusType;
import core.ia.inventory.Inventory;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FacadeIATest {
    private GameController controller;
    private Config config;
    private Player p1;
    private Player p2;
    private Player p3;
    private Board board;
    private FacadeIA facadeIA;
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        config = new Config();
        controller = new GameController();

        PlayerFactory playerFactory = new PlayerFactory(config);
        Player[] players = new Player[3];
        p1 = playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA1");
        p2 = playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA2");
        p3 = playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA3");
        players[0] = p1;
        players[1] = p2;
        players[2] = p3;


        controller.initGame(players, config);
        board = controller.getBoard();
        board.initPLayersInventory();
        inventory = board.getInventories().get(p1);
        board.initHand(p1);

        facadeIA = new FacadeIA(board, p1, config);
        p1.setFacadeIA(facadeIA);


    }


    @Test
    void getEnergyStockTest() {
        List<Energy> energyList = new ArrayList<>();
        energyList.add(Energy.WIND);
        inventory.getEnergyStock().addEnergy(Energy.WIND, null);
        assertEquals(energyList, facadeIA.getEnergyStock(p1));

    }

    @Test
    void getAllTypeEnergyTest() {
        List<Energy> energyList = new ArrayList<>();
        for (Energy energy : Energy.values()) {
            if (energy != Energy.ERROR) {
                energyList.add(energy);
            }
        }
        assertEquals(energyList, facadeIA.getAllTypeEnergy());
    }


    @Test
    void getPossibleActionTest() {
        inventory.setCanCrystalize(true);
        inventory.getEnergyStock().addEnergy(Energy.WIND);  // can crystallize
        inventory.getBonus().setUsedBonus(3);               // can't use bonus
        // can't invoc card

        List<EnumPlayerAction> possibleAction = new ArrayList<>();
        possibleAction.add(EnumPlayerAction.CRYSTALLISE);
        possibleAction.add(EnumPlayerAction.NOTHING);
        assertEquals(possibleAction, facadeIA.getPossibleAction());
    }

    @Test
    void getPossibleBonusTestCanCrystallize() {
        inventory.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory.getEnergyStock().addEnergy(Energy.WIND, null); // Can change energy

        List<BonusType> bonusTypeList = new ArrayList<>();
        bonusTypeList.add(BonusType.ADD_INVOCATION);
        bonusTypeList.add(BonusType.CHANGE_ENERGY);
        bonusTypeList.add(BonusType.CRYSTALLIZE);

        List<BonusType> possibleBonus = facadeIA.getPossibleBonus();
        assertTrue(bonusTypeList.containsAll(possibleBonus) && possibleBonus.containsAll(bonusTypeList));
    }

    @Test
    void getPossibleBonusTestCantCrystallize1() {
        inventory.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory.getEnergyStock().addEnergy(Energy.WIND, null); // Can change energy
        inventory.getBonus().setInUseCrystallizeBonus(true);

        List<BonusType> bonusTypeList = new ArrayList<>();
        bonusTypeList.add(BonusType.ADD_INVOCATION);
        bonusTypeList.add(BonusType.CHANGE_ENERGY);

        List<BonusType> possibleBonus = facadeIA.getPossibleBonus();
        assertTrue(bonusTypeList.containsAll(possibleBonus) && possibleBonus.containsAll(bonusTypeList));
    }

    @Test
    void getPossibleBonusTestCantCrystallize2() {
        inventory.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory.getEnergyStock().addEnergy(Energy.WIND, null); // Can change energy
        inventory.setCanCrystalize(true);

        List<BonusType> bonusTypeList = new ArrayList<>();
        bonusTypeList.add(BonusType.ADD_INVOCATION);
        bonusTypeList.add(BonusType.CHANGE_ENERGY);

        List<BonusType> possibleBonus = facadeIA.getPossibleBonus();
        assertTrue(bonusTypeList.containsAll(possibleBonus) && possibleBonus.containsAll(bonusTypeList));
    }

    @Test
    void getCardInHandTest() {
        assertEquals(inventory.getHand().getCardsInHand(), facadeIA.getCardInHand());
    }

    @Test
    void getOtherPLayersTest() {
        List<Player> playerList = new ArrayList<>();
        playerList.add(p2);
        playerList.add(p3);
        assertEquals(playerList, facadeIA.getOtherPLayers());
    }

    @Test
    void getSeasonTest() {
        assertEquals(Seasons.WINTER, facadeIA.getSeason());
    }
}