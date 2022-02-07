package core.board;

import core.board.enums.Energy;
import core.board.enums.Seasons;
import core.exception.EnergyException;
import core.exception.SeasonException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class SeasonsTest {
    private Seasons s1;
    private Seasons s2;

    @BeforeEach
    void setUp() {
        s1 = Seasons.WINTER;
        s2 = Seasons.SUMMER;
    }

    @Test
    void energyPrices() {
        assertEquals(1, s1.getEnergyValue(Energy.WATER));
        assertEquals(1, s1.getEnergyValue(Energy.WIND));
        assertEquals(2, s1.getEnergyValue(Energy.FIRE));
        assertEquals(3, s1.getEnergyValue(Energy.EARTH));

        assertEquals(1, s2.getEnergyValue(Energy.FIRE));
        assertEquals(1, s2.getEnergyValue(Energy.EARTH));
        assertEquals(2, s2.getEnergyValue(Energy.WATER));
        assertEquals(3, s2.getEnergyValue(Energy.WIND));
    }

    @Test
    void verifyAssociatedEnergyWinter() {
        assertEquals(Energy.WATER, s1.getAssociatedEnergyBySeason());
    }

    @Test
    void verifyAssociatedEnergySummer() {
        assertEquals(Energy.FIRE, s2.getAssociatedEnergyBySeason());
    }

    @Test
    void verifyAssociatedEnergyAutomn() {
        assertEquals(Energy.WIND, Seasons.AUTUMN.getAssociatedEnergyBySeason());
    }

    @Test
    void verifyAssociatedEnergySpring() {
        assertEquals(Energy.EARTH, Seasons.SPRING.getAssociatedEnergyBySeason());
    }

    @Test
    void verifyAssociatedEnergyError() {
        assertThrows(SeasonException.class, Seasons.ERROR::getAssociatedEnergyBySeason);
    }

    @Test
    void energyWaterPriceInWinter() {
        assertEquals(1, Seasons.WINTER.getEnergyValue(Energy.WATER));
    }

    @Test
    void energyFirePriceInWinter() {
        assertEquals(2, Seasons.WINTER.getEnergyValue(Energy.FIRE));
    }

    @Test
    void energyWindPriceInWinter() {
        assertEquals(1, Seasons.WINTER.getEnergyValue(Energy.WIND));
    }

    @Test
    void energyEarthPriceInWinter() {
        assertEquals(3, Seasons.WINTER.getEnergyValue(Energy.EARTH));
    }

    @Test
    void energyWaterPriceInSpring() {
        assertEquals(1, Seasons.SPRING.getEnergyValue(Energy.WATER));
    }

    @Test
    void energyFirePriceInSpring() {
        assertEquals(3, Seasons.SPRING.getEnergyValue(Energy.FIRE));
    }

    @Test
    void energyWindPriceInSpring() {
        assertEquals(2, Seasons.SPRING.getEnergyValue(Energy.WIND));
    }

    @Test
    void energyEarthPriceInSpring() {
        assertEquals(1, Seasons.SPRING.getEnergyValue(Energy.EARTH));
    }

    @Test
    void energyWaterPriceInSummer() {
        assertEquals(2, Seasons.SUMMER.getEnergyValue(Energy.WATER));
    }

    @Test
    void energyFirePriceInSummer() {
        assertEquals(1, Seasons.SUMMER.getEnergyValue(Energy.FIRE));
    }

    @Test
    void energyWindPriceInSummer() {
        assertEquals(3, Seasons.SUMMER.getEnergyValue(Energy.WIND));
    }

    @Test
    void energyEarthPriceInSummer() {
        assertEquals(1, Seasons.SUMMER.getEnergyValue(Energy.EARTH));
    }

    @Test
    void energyWaterPriceInAutumn() {
        assertEquals(3, Seasons.AUTUMN.getEnergyValue(Energy.WATER));
    }

    @Test
    void energyFirePriceInAutumn() {
        assertEquals(1, Seasons.AUTUMN.getEnergyValue(Energy.FIRE));
    }

    @Test
    void energyWindPriceInAutumn() {
        assertEquals(1, Seasons.AUTUMN.getEnergyValue(Energy.WIND));
    }

    @Test
    void energyEarthPriceInAutumn() {
        assertEquals(2, Seasons.AUTUMN.getEnergyValue(Energy.EARTH));
    }

    @Test
    void energyPriceErrorEnergy() {
        assertThrows(EnergyException.class, () -> {
            Seasons.WINTER.getEnergyValue(Energy.ERROR);
        });
    }

    @Test
    void energyPriceErrorSeason() {
        assertThrows(SeasonException.class, () -> {
            Seasons.ERROR.getEnergyValue(Energy.EARTH);
        });
    }

}
