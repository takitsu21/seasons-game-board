package core.ia.inventory;

import core.board.enums.Energy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PlayerEnergyStockTest {
    PlayerEnergyStock playerEnergyStock;

    @BeforeEach
    void setUp() {
        playerEnergyStock = new PlayerEnergyStock();
    }

    @Test
    void addEnergyTest() {
        //SetUp
        List<Energy> energyList = new ArrayList<>();

        // Can add one energy with stock not full
        playerEnergyStock.addEnergy(Energy.WIND, null);
        energyList.add(Energy.WIND);
        assertEquals(playerEnergyStock.getEnergyStock(), energyList);  // Can add one energy

        // Can add energy with to throw at non null and stock no full
        playerEnergyStock.addEnergy(Energy.FIRE, Energy.WIND);
        energyList.add(Energy.FIRE);
        assertEquals(energyList, playerEnergyStock.getEnergyStock());

        // Can't add energy with stock full and toThrow null
        for (int i = 0; i < 5; i++) {
            playerEnergyStock.addEnergy(Energy.WIND, null);
            energyList.add(Energy.WIND);
        }
        playerEnergyStock.addEnergy(Energy.WATER, null);
        assertEquals(energyList, playerEnergyStock.getEnergyStock());

        // Can add energy with stock full and toThrow valid
        playerEnergyStock.addEnergy(Energy.WATER, Energy.WIND);
        energyList.remove(Energy.WIND);
        energyList.add(Energy.WATER);
        assertEquals(energyList, playerEnergyStock.getEnergyStock());

        // can't add energy with stock full and toTrow nonValid
        playerEnergyStock.addEnergy(Energy.WIND, Energy.EARTH);
        assertEquals(energyList, playerEnergyStock.getEnergyStock());
    }


    @Test
    void modifyNbMaxEnergy() {

        playerEnergyStock.modifyNbMaxEnergy(10);
        assertEquals(10, playerEnergyStock.getNbMaxEnergy());   // Change the nbMaxEnergy
        assertFalse(playerEnergyStock.modifyNbMaxEnergy(11));    // Can't have more than 10 energy places
        assertFalse(playerEnergyStock.modifyNbMaxEnergy(6));   // Can't have less than 7 energy places
    }

    @Test
    void modifyNbMaxEnergyWithEnergy() {
        playerEnergyStock.modifyNbMaxEnergy(10);
        for (int i = 0; i < playerEnergyStock.getNbMaxEnergy(); i++) {
            playerEnergyStock.addEnergy(Energy.FIRE);
        }

        assertEquals(10, playerEnergyStock.getEnergyStock().size());
        playerEnergyStock.modifyNbMaxEnergy(7);
        assertEquals(7, playerEnergyStock.getEnergyStock().size());
    }

    @Test
    void getNbMaxEnergy() {
        assertEquals(7, playerEnergyStock.getNbMaxEnergy());
        playerEnergyStock.modifyNbMaxEnergy(9);
        assertEquals(9, playerEnergyStock.getNbMaxEnergy());
    }

    @Test
    void testToString() {
        assertEquals("Energy stock{" +
                        ", MIN_NB_MAX_ENERGY=" + 7 +
                        ", MAX_NB_MAX_ENERGY=" + 10 +
                        ", energyStock=" + "[]" +
                        ", nbMaxEnergy=" + 7 +
                        '}',
                playerEnergyStock.toString());
    }
}