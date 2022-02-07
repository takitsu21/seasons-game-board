package core.dice;

import core.board.Board;
import core.board.Year;
import core.cards.Deck;
import util.Util;

import java.util.ArrayList;
import java.util.Arrays;

public class DiceSet implements Cloneable{
    private Dice[] setOfDices;

    public DiceSet(int nbPlayer, Year year) {
        int r;
        this.setOfDices = new Dice[nbPlayer + 1];

        ArrayList<Integer> nbForRand = new ArrayList<>();
        nbForRand.add(0);
        nbForRand.add(1);
        nbForRand.add(2);
        nbForRand.add(3);
        nbForRand.add(4);

        for (int i = 0; i < nbPlayer + 1; i++) {
            r = Util.getNextInt(nbForRand.size());
            setOfDices[i] = new Dice(nbForRand.get(r), year);
            nbForRand.remove(nbForRand.get(r));
        }
    }

    public void removeDice(Dice dice) {
        Dice[] newDiceSet = new Dice[setOfDices.length - 1];
        int k=0;
        for (Dice value : setOfDices) {

            if (!value.equals(dice)) {
                newDiceSet[k] = value;
                k++;

            }
        }
        setOfDices = newDiceSet;
    }

    public Dice getDice(int i) {
        return setOfDices[i];
    }
    public Dice[] getSetOfDices() {
        return setOfDices;
    }

    public void setSetOfDices(Dice[] setOfDices) {
        this.setOfDices = setOfDices;
    }

    @Override
    public String toString() {
        return Arrays.toString(setOfDices);
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        DiceSet cloneDiceSet = (DiceSet) super.clone();
        Dice[] cloneSetOfDices=new Dice[setOfDices.length];
        for (int i=0; i<setOfDices.length; i++){
            cloneSetOfDices[i]= (Dice) setOfDices[i].clone();
        }

        cloneDiceSet.setSetOfDices(cloneSetOfDices);

        return cloneDiceSet;
    }
}
