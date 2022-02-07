package core.ia.inventory;

import core.cards.Card;
import core.util.CardUtil;

import java.util.ArrayList;
import java.util.List;

public class Invocation implements Cloneable{
    private final static int MAX_INVOCATION_POINTS = 15;
    private ArrayList<Card> cardsOnBoard = new ArrayList<>();
    private int invocationPoints = 0; //nombre total de cartes pouvant être placées sur le board
    private int currentInvocations = 0; //nombre de cartes invoquées jusque là

    ///// Function about invocation points

    public void addInvocationPoints(int i) {
        invocationPoints = Math.min(invocationPoints + i, MAX_INVOCATION_POINTS);
        invocationPoints = Math.max(invocationPoints, 0);
    }

    public int getInvocationPoints() {
        return invocationPoints;
    }


    public void setInvocationPoints(int invocationPoints) {
        if (invocationPoints <= MAX_INVOCATION_POINTS && invocationPoints >= 0) {
            this.invocationPoints = invocationPoints;
        }
    }

    /**
     * Sets currentInvocations to min(invocationPoints, currentInvocations + 1)
     */
    public void incrementCurrentInvocations() {
        currentInvocations = Math.min(invocationPoints, currentInvocations + 1);
    }

    /**
     * Sets currentInvocations to max(0, currentInvocations - 1)
     */
    public void decrementCurrentInvocations() {
        currentInvocations = Math.max(0, currentInvocations - 1);
    }

    public int getCurrentInvocations() {
        return currentInvocations;
    }

    public List<Card> getCardsOnBoard() {
        return cardsOnBoard;
    }


    /**
     * Removes the given card from the board and decrement currentInvocation.
     *
     * @param card card to remove
     */
    public boolean removeCardFromBoard(Card card) {
        if (cardsOnBoard.remove(card)) {
            decrementCurrentInvocations();
            return true;
        }
        return false;
    }

    public List<Card> getActivableCard() {
        List<Card> activableCard = new ArrayList<>();
        for (Card card : cardsOnBoard) {
            if (card.isActivable()) {
                activableCard.add(card);
            }
        }
        return activableCard;
    }

    public void setCardsOnBoard(ArrayList<Card> cardsOnBoard) {
        this.cardsOnBoard = cardsOnBoard;
    }

    public String toStringCardOnBoard() {
        return CardUtil.toStringArrayListCards(cardsOnBoard);
    }

    @Override
    public String toString() {
        return "Invocation{" +
                "MAX_INVOCATION_POINTS=" + MAX_INVOCATION_POINTS +
                ", invocationPoints=" + invocationPoints +
                ", cardsOnBoard=" + cardsOnBoard +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Invocation cloneInvocation= (Invocation) super.clone();
        setCardsOnBoard(new ArrayList<>(cardsOnBoard));
        return cloneInvocation;
    }
}
