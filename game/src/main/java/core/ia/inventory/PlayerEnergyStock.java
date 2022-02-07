package core.ia.inventory;

import core.board.enums.Energy;

public class PlayerEnergyStock extends EnergyStock implements Cloneable{
    private final static int MIN_NB_MAX_ENERGY = 7;
    private final static int MAX_NB_MAX_ENERGY = 10;
    private int nbMaxEnergy = MIN_NB_MAX_ENERGY;

    public boolean addEnergy(Energy energyToAdd, Energy energyToThrow) {
        boolean success;
        if (this.theEnergyStock.size() == this.nbMaxEnergy) {
            if (energyToThrow != null) {
                if (theEnergyStock.contains(energyToThrow)) {
                    this.removeEnergy(energyToThrow);
                    this.theEnergyStock.add(energyToAdd);
                    success = true;
                } else {
                    success = false;
                }
            } else {
                success = false;
            }
        } else {
            this.theEnergyStock.add(energyToAdd);
            success = true;
        }
        return success;
    }

    public boolean modifyNbMaxEnergy(int nb) {
        if (nb > MAX_NB_MAX_ENERGY || nb < MIN_NB_MAX_ENERGY) {
            return false;
        } else if (nb < theEnergyStock.size()) { //on ne retire des énergies que si nb est inférieur à la taille actuelle du stock
            int originalStockSize = theEnergyStock.size();
            for (int i = originalStockSize; i > nb; i--) {
                theEnergyStock.remove(i - 1);
            }
        }
        this.nbMaxEnergy = nb;
        return true;
    }

    public int getNbMaxEnergy() {
        return nbMaxEnergy;
    }


    @Override
    public String toString() {
        return "Energy stock{" +
                ", MIN_NB_MAX_ENERGY=" + MIN_NB_MAX_ENERGY +
                ", MAX_NB_MAX_ENERGY=" + MAX_NB_MAX_ENERGY +
                ", energyStock=" + theEnergyStock +
                ", nbMaxEnergy=" + nbMaxEnergy +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
