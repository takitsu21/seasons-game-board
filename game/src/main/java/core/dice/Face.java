package core.dice;

import core.board.enums.Energy;

import java.util.Arrays;

public class Face implements Cloneable {
    private final int nbCrystals;
    private final boolean takeCard;
    private final boolean crystalize;
    private final boolean invocationSup;
    private final int nbAdvance;
    private Energy[] energies;

    /**
     * Initialise une face du dé
     *
     * @param nbCrystals    Nombre de crystaux que la face fournie
     * @param takeCard      Si oui ou non la face permet de piocher une carte
     * @param crystalize    Si oui ou non la face permet de piocher une carte
     * @param invocationSup Si oui ou non la face donne une place d'invocation
     * @param nbAdvance     Nombre de cases à faire avancer le pion si la face est sur le dé choisi par aucun des joueurs
     * @param energies      Les énergies que la face fournie
     */
    public Face(int nbCrystals, boolean takeCard, boolean crystalize, boolean invocationSup, int nbAdvance, Energy[] energies) {
        this.nbCrystals = nbCrystals;
        this.takeCard = takeCard;
        this.crystalize = crystalize;
        this.invocationSup = invocationSup;
        this.nbAdvance = nbAdvance;
        this.energies = energies;
    }

    /**
     * Initialise une face du dé
     *
     * @param nbCrystals    Nombre de crystaux que la face fournie
     * @param takeCard      Si oui ou non la face permet de piocher une carte
     * @param crystalize    Si oui ou non la face permet de piocher une carte
     * @param invocationSup Si oui ou non la face donne une place d'invocation
     * @param nbAdvance     Nombre de cases à faire avancer le pion si la face est sur le dé choisi par aucun des joueurs
     */
    public Face(int nbCrystals, boolean takeCard, boolean crystalize, boolean invocationSup, int nbAdvance) {
        this.nbCrystals = nbCrystals;
        this.takeCard = takeCard;
        this.crystalize = crystalize;
        this.invocationSup = invocationSup;
        this.nbAdvance = nbAdvance;
    }

    /**
     * @return nbPoint
     */
    public int getNbCrystals() {
        return nbCrystals;
    }

    public boolean isTakeCard() {
        return takeCard;
    }

    public boolean isCrystalize() {
        return crystalize;
    }

    public boolean isInvocationSup() {
        return invocationSup;
    }

    public int getNbAdvance() {
        return nbAdvance;
    }

    public Energy[] getEnergy() {
        return energies;
    }

    @Override
    public String toString() {

        return "{ Cristaux: " + nbCrystals +
                "\t| Piocher une carte: " + takeCard +
                "\t| Cristalliser: " + crystalize +
                "\t| Invocation: " + invocationSup +
                "\t| Avancement du pion: " + nbAdvance +
                "\t| Energies: " + Arrays.toString(energies) +
                '}';


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
        Face f = (Face) obj;

        if (f.getNbCrystals() != nbCrystals || f.takeCard != isTakeCard() || f.isCrystalize() != crystalize ||
                f.isInvocationSup() != invocationSup || f.getNbAdvance() != nbAdvance) {
            return false;
        }
        if ((energies == null && f.getEnergy() != null) ||
                (energies != null && f.getEnergy() == null)) {
            return false;
        }
        if (energies != null && f.getEnergy() != null) {
            if (energies.length != f.getEnergy().length) {
                return false;
            }

            for (int i = 0; i < energies.length; i++) {
                if (energies[i] != f.getEnergy()[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
