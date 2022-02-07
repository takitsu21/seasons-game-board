package core.board.enums;

import core.board.Board;
import core.cards.Card;
import core.cards.effects.FigrimAvaricieuxEffect;
import core.cards.effects.SablierTempsEffect;
import core.exception.EnergyException;
import core.exception.SeasonException;
import core.ia.Player;

public enum Seasons {
    WINTER(0), SPRING(1), SUMMER(2), AUTUMN(3), ERROR(4);
    private int season;

    Seasons(int season) {
        this.season = season;
    }

    public int getEnergyValue(Energy energy) {
        return switch (season) {
            case 0 -> priceEnergy(energy, 1, 2, 1, 3);
            case 1 -> priceEnergy(energy, 1, 3, 2, 1);
            case 2 -> priceEnergy(energy, 2, 1, 3, 1);
            case 3 -> priceEnergy(energy, 3, 1, 1, 2);
            default -> throw new SeasonException("Unrecognized Season");
        };
    }

    public Seasons updateSeason(Board board) {
        if (board.getCurrentCursor() < 0) {
            board.setCurrentCursor(0);
        }
        Seasons s = Seasons.values()[(board.getCurrentCursor() % 12) / 3];
        if (s.ordinal() != season) {
            checkEndSeasonBonuses(board);
        }
        return s;
    }

    /**
     * Check permanent card on each season changes
     *
     * @param board - The board game
     */
    private void checkEndSeasonBonuses(Board board) {
        for (Player player : board.getPlayers()) {
            for (Card card : board.getInventories().get(player).getInvocation().getCardsOnBoard()) {
                if (card instanceof FigrimAvaricieuxEffect
                        || card instanceof SablierTempsEffect) { //s'il y a un Sablier du Temps
                    card.use(board, player);
                }
            }
        }
    }

    /**
     * Get energy prices according to seasons
     *
     * @param energy     The energy to check
     * @param priceWater The price of a water energy
     * @param priceFire  The price of a fire energy
     * @param priceWind  The price of a wind energy
     * @param priceEarth The price of an earth energy
     * @return the price according to the current season
     * @throws EnergyException If the energy isn't recognized
     */
    public int priceEnergy(Energy energy, int priceWater, int priceFire, int priceWind, int priceEarth) throws
            EnergyException {
        return switch (energy) {
            case WATER -> priceWater;
            case FIRE -> priceFire;
            case WIND -> priceWind;
            case EARTH -> priceEarth;
            default -> throw new EnergyException("Unrecognized energy");
        };
    }

    /**
     * @return Energy associated with the season
     * @throws SeasonException If the season isn't recognized
     */
    public Energy getAssociatedEnergyBySeason() {
        return switch (season) {
            case 0 -> Energy.WATER;
            case 1 -> Energy.EARTH;
            case 2 -> Energy.FIRE;
            case 3 -> Energy.WIND;
            default -> throw new SeasonException("Unrecognized season");
        };
    }

    public Energy getMaxEnergyBySeason() {
        return switch (season) {
            case 0 -> Energy.EARTH;
            case 1 -> Energy.FIRE;
            case 2 -> Energy.WIND;
            case 3 -> Energy.WATER;
            default -> throw new SeasonException("Unrecognized season");
        };
    }

    public int getSeasonNumber() { return season; }
}
