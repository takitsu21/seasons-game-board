package core.dice;

import core.board.Year;
import core.board.enums.Energy;
import core.exception.DiceException;
import core.exception.SeasonException;
import util.Util;

import java.util.logging.Level;

public class Dice implements IDice, Cloneable {
    private static final int NB_FACE = 6;
    private Face[] faces = new Face[NB_FACE];
    private Face currentFace;

    public Dice(int i, Year year) {
        try {
            initFaces(i, year);
        } catch (DiceException | SeasonException e) {
            Util.logger.log(Level.SEVERE, "Creation of dice", e);
        }
        int r = Util.getNextInt(NB_FACE);
        currentFace = faces[r];
    }

    @Override
    public void initFaces(int i, Year year) {
        Energy prevSeason = year.getPreviousSeason().getAssociatedEnergyBySeason();
        Energy oppositeSeason = year.getOppositeSeason().getAssociatedEnergyBySeason();
        Energy currentSeason = year.getCurrentSeason().getAssociatedEnergyBySeason();
        switch (i) {
            case (0) -> { // de 1 etc ...
                faces[0] = new Face(0, false, false, true, 1, new Energy[]{currentSeason, currentSeason});
                faces[1] = new Face(0, true, false, false, 1);
                faces[2] = new Face(0, false, true, false, 3, new Energy[]{currentSeason, prevSeason});
                faces[3] = new Face(0, false, true, true, 2, new Energy[]{prevSeason});
                faces[4] = new Face(3, false, false, false, 3, new Energy[]{currentSeason, currentSeason});
                faces[5] = new Face(0, false, false, true, 2, new Energy[]{oppositeSeason});
            }
            case (1) -> {
                faces[0] = new Face(0, false, false, true, 2, new Energy[]{currentSeason, currentSeason});
                faces[1] = new Face(0, true, false, false, 2);
                faces[2] = new Face(0, false, false, true, 3, new Energy[]{prevSeason, prevSeason});
                faces[3] = new Face(0, false, true, false, 1, new Energy[]{currentSeason, prevSeason});
                faces[4] = new Face(0, false, true, true, 3, new Energy[]{oppositeSeason});
                faces[5] = new Face(3, false, false, false, 1, new Energy[]{currentSeason, currentSeason});
            }
            case (2) -> {
                faces[0] = new Face(6, false, false, false, 2);
                faces[1] = new Face(2, false, false, false, 2, new Energy[]{currentSeason, currentSeason});
                faces[2] = new Face(0, false, true, true, 3, new Energy[]{prevSeason});
                faces[3] = new Face(0, false, true, false, 1, new Energy[]{currentSeason, prevSeason});
                faces[4] = new Face(0, false, false, true, 3, new Energy[]{oppositeSeason});
                faces[5] = new Face(0, false, false, true, 1, new Energy[]{currentSeason, currentSeason});
            }
            case (3) -> {
                faces[0] = new Face(0, false, false, true, 1, new Energy[]{oppositeSeason, oppositeSeason});
                faces[1] = new Face(0, false, true, true, 1, new Energy[]{prevSeason});
                faces[2] = new Face(0, false, false, true, 3, new Energy[]{currentSeason, currentSeason});
                faces[3] = new Face(0, false, true, false, 2, new Energy[]{currentSeason, prevSeason});
                faces[4] = new Face(0, true, false, false, 3);
                faces[5] = new Face(1, false, false, false, 2, new Energy[]{currentSeason, currentSeason});
            }
            case (4) -> {
                faces[0] = new Face(1, false, false, false, 2, new Energy[]{currentSeason, currentSeason});
                faces[1] = new Face(0, false, true, false, 2, new Energy[]{prevSeason, prevSeason});
                faces[2] = new Face(0, false, false, false, 1, new Energy[]{oppositeSeason});
                faces[3] = new Face(6, false, false, false, 3);
                faces[4] = new Face(0, false, true, false, 1, new Energy[]{prevSeason});
                faces[5] = new Face(0, false, false, true, 3, new Energy[]{currentSeason, currentSeason});
            }
            default -> throw new DiceException("dé demandé non connu");
        }
    }

    public Face getCurrentFace() {
        return currentFace;
    }

    public Face[] getFaces() {
        return faces;
    }

    public void setCurrentFace(int currentFace) {
        this.currentFace = faces[currentFace];
    }

    public void reRollDice() {
        int r = Util.getNextInt(NB_FACE);
        currentFace = faces[r];
    }

    public void setFaces(Face[] faces) {
        this.faces = faces;
    }

    public void setCurrentFace(Face currentFace) {
        this.currentFace = currentFace;
    }

    @Override
    public String toString() {
        return currentFace.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Dice d = (Dice) obj;
        if (!currentFace.equals(((Dice) obj).getCurrentFace())) {
            return false;
        }
        if (d.getCurrentFace().equals(currentFace)) {
            for (int i = 0; i < 6; i++) {
                if (!d.getFaces()[i].equals(faces[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Dice cloneDice = (Dice) super.clone();

        cloneDice.setCurrentFace((Face) currentFace.clone());
        Face[] cloneFaces = new Face[faces.length];

        for (int i = 0; i < faces.length; i++) {
            cloneFaces[i] = (Face) faces[i].clone();
        }
        cloneDice.setFaces(cloneFaces);
        return cloneDice;
    }
}
