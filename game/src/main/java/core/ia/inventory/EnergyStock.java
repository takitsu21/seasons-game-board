package core.ia.inventory;

import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.Card;
import core.util.Renderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EnergyStock implements Cloneable{
    protected List<Energy> theEnergyStock = new ArrayList<>();

    public void addEnergy(Energy energyToAdd) {
        if (energyToAdd != Energy.ERROR) {
            this.theEnergyStock.add(energyToAdd);
        }
    }

    public boolean removeEnergy(Energy[] energyArray) {
        return removeEnergy(Arrays.stream(energyArray).toList());
    }

    public boolean removeEnergy(List<Energy> energyArray) {
        boolean successAll;
        if (energyArray != null && theEnergyStock.containsAll(energyArray)) {
            for (Energy e : energyArray) {
                removeEnergy(e);
            }
            successAll = true;
        } else {
            successAll = false;
        }
        return successAll;
    }

    public boolean removeEnergy(Energy energy) {
        boolean success;
        if (this.theEnergyStock.contains(energy)) {
            this.theEnergyStock.remove(energy);
            success = true;
        } else {
            Renderer.add(String.format("Le joueur ne possède pas d'énergie %s", energy.name()));
            success = false;
        }
        return success;
    }

    /**
     * Checks if the energyStock contains the right energies to summon the given card.
     * If not, it will iterate over the cards on the player's board to check if a card can help pay the price.
     *
     * @param cardCost     Energy cost used as the reference to calculate the returned boolean
     * @param cardsOnBoard Cards on the  player's board
     * @return True if the player has enough energy (possibly using energies from his own cards), False otherwise
     */
    public boolean hasEnoughEnergy(List<Energy> cardCost, List<Card> cardsOnBoard) {

        List<Energy> copyOfStock = new ArrayList<>(theEnergyStock);
        List<Energy> copyOfCost = new ArrayList<>(cardCost);
        lookForEnergies(cardCost, copyOfCost, copyOfStock);

        boolean enoughEnergy = copyOfCost.isEmpty(); //si la copie du coût est vide, c'est que le stock contient toutes les énergies nécessaires
        //si la reserve du joueur seule ne suffit pas, on refait la même chose, mais avec les cartes
        if (!enoughEnergy) {
            List<Energy> copyOfCopyOfCost = new ArrayList<>(copyOfCost); //pour éviter le ConcurrentModification avec copyOfCost
            for (Card currentCard : cardsOnBoard) {
                //de ce que j'ai lu dans les règles, on a que cette carte qui peut stocker des énergies séparément de la réserve du joueur
                if (currentCard.getId() == 4) { //si c'est une Amulette d'eau
                    List<Energy> copyOfCardStock = new ArrayList<>(currentCard.getEnergyStock().getEnergyStock());
                    lookForEnergies(copyOfCost, copyOfCopyOfCost, copyOfCardStock);
                }
            }
            enoughEnergy = copyOfCopyOfCost.isEmpty();
        }
        return enoughEnergy;
    }

    private void lookForEnergies(List<Energy> energiesToLookFor, List<Energy> copyOfEnergiesToLookFor, List<Energy> listToLookIn) {
        for (Energy energy : energiesToLookFor) {
            if (listToLookIn.contains(energy)) {
                //on enlève les energies "valides" pour éviter de les compter plusieurs fois
                listToLookIn.remove(energy);
                copyOfEnergiesToLookFor.remove(energy);
            }
        }
    }

    public int size() {
        return theEnergyStock.size();
    }

    public List<Energy> getEnergyStock() {
        return theEnergyStock;
    }

    public List<List<Energy>> listOfMultipleEnergy(int nb) {
        List<Energy> energyStock = getEnergyStock();
        if (energyStock.size() >= nb) {
            int freq;
            List<List<Energy>> possibleListOfEnergy = new ArrayList<>();
            List<Energy> list = new ArrayList<>();
            for (Energy e : energyStock) {
                List<Energy> el = energyStock;
                freq = Collections.frequency(el, e);
                if (freq >= nb) {
                    for (int i = 0; i < nb; i++) {
                        list.add(e);
                    }
                    possibleListOfEnergy.add(new ArrayList<>(list));
                    list.clear();
                }
            }
            return possibleListOfEnergy;
        }
        return Collections.emptyList();
    }

    public void setTheEnergyStock(List<Energy> theEnergyStock) {
        this.theEnergyStock = theEnergyStock;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (Energy energy : theEnergyStock) {
            sb.append(energy);
            sb.append(", ");
        }
        sb.append(" ]");
        return sb.toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        EnergyStock cloneEnergyStock= (EnergyStock) super.clone();

        List<Energy> cloneTheEnergyStock=new ArrayList<>(theEnergyStock);

        cloneEnergyStock.setTheEnergyStock(cloneTheEnergyStock);

        return cloneEnergyStock;
    }
}
