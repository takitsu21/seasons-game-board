package core.ia.inventory;

import core.board.enums.Energy;
import core.cards.Card;
import core.cards.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnergyStockTest {
    EnergyStock energyStock;

    @BeforeEach
    void setUp() {
        energyStock = new EnergyStock();
    }

    @Test
    void addEnergy() {
        List<Energy> energyList = new ArrayList<>();
        assertEquals(energyList, energyStock.getEnergyStock());
        energyStock.addEnergy(Energy.EARTH);
        energyList.add(Energy.EARTH);
        assertEquals(energyList, energyStock.getEnergyStock());
    }

    @Test
    void removeEnergyTest() {

        assertFalse(energyStock.removeEnergy(Energy.WATER));  // Can't remove energy without energy in the stock

        energyStock.addEnergy(Energy.EARTH);
        energyStock.addEnergy(Energy.FIRE);
        energyStock.addEnergy(Energy.WIND);
        assertFalse(energyStock.removeEnergy(Energy.WATER)); // Can't remove energy without energy of a different type
        assertTrue(energyStock.removeEnergy(Energy.EARTH));  // Can remove energy if present in the stock

        energyStock.removeEnergy(new Energy[]{Energy.WIND, Energy.FIRE});
        assertEquals(energyStock.getEnergyStock(), new ArrayList<>());  // Can remove multiple energy

        energyStock.addEnergy(Energy.EARTH);
        energyStock.addEnergy(Energy.FIRE);
        assertFalse(energyStock.removeEnergy(new Energy[]{Energy.EARTH, Energy.WIND})); // Can't remove multiple energy if 1 is absent

        List<Energy> energyList = new ArrayList<>();
        energyList.add(Energy.EARTH);
        energyList.add(Energy.FIRE);
        assertEquals(energyStock.getEnergyStock(), energyList); // Can't remove multiple energy if 1 is absent
    }


    @Test
    void hasEnoughEnergyTest() {
        Deck deck = new Deck();
        Card card = deck.findCard(1);
        ArrayList<Card> cardsOnBoard = new ArrayList<>();
        energyStock.addEnergy(Energy.EARTH);
        assertFalse(energyStock.hasEnoughEnergy(card.getEnergyCost(), cardsOnBoard));
        energyStock.addEnergy(Energy.WIND);
        energyStock.addEnergy(Energy.WIND);
        assertTrue(energyStock.hasEnoughEnergy(card.getEnergyCost(), cardsOnBoard));

        //tests si les energies des cartes sont bien prises en compte lorsque le joueur n'a pas assez d'énergies dans sa réserve
        Card amulette = deck.findCard(4);
        Card amulette2 = deck.findCard(4);
        amulette.getEnergyStock().addEnergy(Energy.WATER);
        amulette.getEnergyStock().addEnergy(Energy.WATER);
        assertFalse(energyStock.hasEnoughEnergy(amulette.getEnergyCost(), cardsOnBoard));
        //test si les énergies sur amulette sont comptées
        cardsOnBoard.add(amulette);
        assertTrue(energyStock.hasEnoughEnergy(amulette2.getEnergyCost(), cardsOnBoard));
        //test si les énergies d'amulette ne sont plus comptées (au cas où)
        cardsOnBoard.remove(amulette);
        assertFalse(energyStock.hasEnoughEnergy(amulette2.getEnergyCost(), cardsOnBoard));
        //test si ça fonctionne lorsque le joueur ne possède qu'une partie des énergies, et la carte une autre partie
        energyStock.addEnergy(Energy.WATER);
        amulette.getEnergyStock().getEnergyStock().remove(Energy.WATER);
        cardsOnBoard.add(amulette);
        assertTrue(energyStock.hasEnoughEnergy(amulette2.getEnergyCost(), cardsOnBoard));
    }

    @Test
    void size() {
        assertEquals(0, energyStock.size());
        energyStock.addEnergy(Energy.EARTH);
        assertEquals(1, energyStock.size());
    }

    @Test
    void getEnergyStock() {
        List<Energy> energyList = new ArrayList<>();
        assertEquals(energyList, energyStock.getEnergyStock());
        energyStock.addEnergy(Energy.EARTH);
        energyList.add(Energy.EARTH);
        assertEquals(energyList, energyStock.getEnergyStock());
    }

    @Test
    void testToString() {
        assertEquals("[  ]",
                energyStock.toString());
    }
}