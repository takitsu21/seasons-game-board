package core.board;

import core.board.enums.Seasons;
import core.ia.Player;

import java.util.Arrays;

public class Year implements Cloneable{
    private final int nbMaxYears;
    Seasons[] seasons = {Seasons.WINTER, Seasons.SPRING, Seasons.SUMMER, Seasons.AUTUMN};
    private int nbYear = 1;
    private int seasonIdx = 0;

    public Year(int nbMaxYears) {
        this.nbMaxYears = nbMaxYears;
    }

    public Year(int i, int nbMaxYears) {
        this.nbYear = i;
        this.nbMaxYears = nbMaxYears;

    }


    public boolean updateYearAndSeason(Board board) {
        seasonIdx = getCurrentSeason().updateSeason(board).ordinal();
        if (nbYear != board.getCurrentCursor() / 12 + 1) {
            int newYear = (board.getCurrentCursor() / 12) + 1;
            if (newYear > nbMaxYears) {
                nbYear = newYear;
                return false;
            }
            if (newYear > nbYear) {
                for (Player player : board.getPlayers()) {
                    //System.out.println(" Before Player "+player.getName() + " cardInHand: "+board.getInventories().get(player).getCardsInHand()
                    //+" cardYear: "+ Arrays.deepToString(board.getInventories().get(player).getHand().getCardsByYear()));
                    board.getInventories().get(player).getHand().newUpdateHand(newYear);
                    //System.out.println("After Player "+player.getName() + " cardInHand: "+board.getInventories().get(player).getCardsInHand()
                     //       +" cardYear: "+ Arrays.deepToString(board.getInventories().get(player).getHand().getCardsByYear()));
                }
            }
            nbYear = newYear;
        }
        return true;
    }

    public int getNbYear() {
        return nbYear;
    }

    public void setNbYear(int nbYear) {
        this.nbYear = nbYear;
    }

    public int getNbMaxYears() { return nbMaxYears; }

    public Seasons getCurrentSeason() {
        return seasons[seasonIdx];
    }

    public Seasons getNextSeason() {
        return seasons[(seasonIdx + 1) % 4];
    }

    public Seasons getOppositeSeason() {
        return seasons[(seasonIdx + 2) % 4];
    }

    public Seasons getPreviousSeason() {
        int tmpSeasonIdx = seasonIdx;
        if (tmpSeasonIdx - 1 < 0) {
            return seasons[3];
        }
        return seasons[(tmpSeasonIdx - 1) % 4];
    }

    @Override
    public String toString() {
        return "Année " + nbYear + " saison " + seasonIdx;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
