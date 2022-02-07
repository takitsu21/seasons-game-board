package core.ia.inventory;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.Card;
import core.exception.BonusException;
import core.ia.Player;
import core.util.Renderer;

import java.util.ArrayList;

public class Bonus implements Cloneable{
    private static final int MAX_BONUS = 3;
    private int usedBonus = 0;

    private boolean inUseCrystallizeBonus = false;

    public Bonus() {
        // Empty constructor
    }


    public boolean getInUseCrystallizeBonus() {
        return inUseCrystallizeBonus;
    }

    public void setInUseCrystallizeBonus(boolean inUseCrystallizeBonus) {
        this.inUseCrystallizeBonus = inUseCrystallizeBonus;
    }

    public void addUsedBonus() {
        if (usedBonus < 3) {
            usedBonus++;
        }
    }

    public int getUsedBonus() {
        return usedBonus;
    }

    public void setUsedBonus(int usedBonus) {
        this.usedBonus = usedBonus;
    }

    public int getMaxBonus() {
        return MAX_BONUS;
    }


    public void useBonus(BonusType bonus, Player player, Board board) {
        if (usedBonus == getMaxBonus()) {
            Renderer.add("- A utilisé tous ses bonus");
            return;
        }
        Inventory inventory = board.getInventories().get(player);
        usedBonus++;
        Renderer.add(usedBonus + " /3 bonus utilisés");
        switch (bonus) {
            case CRYSTALLIZE -> {
                crystallize(inventory);
                inUseCrystallizeBonus = true;
            }
            case CHANGE_ENERGY -> changeEnergy(inventory, player);

            case DRAW_2_CARDS -> draw2Card(board, player, inventory);

            case ADD_INVOCATION -> addIncovation(inventory);

            default -> throw new BonusException("Unreconize Bonus");

        }
    }

    public void crystallize(Inventory inventory) {
        inventory.setBonusCrystal(inventory.getBonusCrystal() + 1);
        inventory.setCanCrystalize(true);
        Renderer.add("- Peut cristalliser pendant son tour et gagnera un cristal a chaque cristalisation grâce à son bonus");

    }

    public void changeEnergy(Inventory inventory, Player player) {
        if (inventory.getEnergyStock().getEnergyStock().size() >= 2) {
            Energy[] toThrow = new Energy[2];
            Energy[] toReplace = new Energy[2];
            for (int i = 0; i < 2; i++) {
                toThrow[i] = player.chooseEnergyToThrow();
                toReplace[i] = player.chooseEnergyToGet();
            }
            inventory.getEnergyStock().removeEnergy(toThrow);
            inventory.getEnergyStock().addEnergy(toReplace[0]);
            inventory.getEnergyStock().addEnergy(toReplace[1]);
            Renderer.add("- A jeté " + toThrow[0].toString() + " " + toThrow[1].toString()
                    + " pour récuperer " + toReplace[0].toString() + " " + toReplace[1].toString()
                    + " grâce à son bonus");
        } else {
            Renderer.add("- N'a pas assez d'énergies pour les échanger");
        }
    }

    public void draw2Card(Board board, Player player, Inventory inventory) {
        if (board.getDeck().getDeck().size() >= 2) {
            ArrayList<Card> possibleCard = new ArrayList<>(2);
            for (int i = 0; i < 2; i++) {
                possibleCard.add(board.getDeck().drawCard());
            }
            Card chosenCard = player.chooseCardBetweenMultipleToGet(possibleCard);
            if (chosenCard == null){
                Renderer.add("- Ne peut pas choisir entre 2 cartes");
            }
            else{
                Renderer.add("- Peut choisir une carte entre: " + possibleCard.get(0).getName() + " et " + possibleCard.get(1).getName() + " grâce à son bonus ");
                inventory.getHand().addCard(chosenCard);
                Renderer.add("-- Garde la carte: " + chosenCard.getName());
                if(possibleCard.get(0)==chosenCard){
                    inventory.discard(board, possibleCard.get(1));
                }
                else{
                    inventory.discard(board, possibleCard.get(0));
                }
            }


        }
    }

    public void addIncovation(Inventory inventory) {
        inventory.getInvocation().addInvocationPoints(1);
        Renderer.add("- Gagne un point d'invocation grâce à son bonus");
    }

    @Override
    public String toString() {
        return "Bonus{" +
                "usedBonus=" + usedBonus +
                ", inUseCrystallizeBonus=" + inUseCrystallizeBonus +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
