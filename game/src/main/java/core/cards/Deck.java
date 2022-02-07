package core.cards;

import core.board.Board;
import core.board.Year;
import core.exception.CardNotFoundException;
import core.ia.inventory.Inventory;
import core.util.Renderer;
import util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck implements Cloneable{
    private ArrayList<Card> theDeck;
    private List<Card> discardPool;

    public Deck() {
        CardFactory cardLoader = CardFactory.getINSTANCE();
        cardLoader.load();
        this.theDeck = (ArrayList<Card>) cardLoader.getCards();
        shuffle();
        this.discardPool = new ArrayList<>();
    }

    public void shuffle() {
        Collections.shuffle(theDeck);
    }

    public Card findCard(String name) {
        for (Card card : theDeck) {
            if (card.getName().trim().equalsIgnoreCase(name.trim())) {
                return card;
            }
        }
        throw new CardNotFoundException(String.format("Card with name : %s cannot be found in the deck", name));
    }

    public void recycle() {
        if (discardPool.isEmpty()) {
            Renderer.add("- La défausse est vide elle ne peut donc pas être recyclé.");
        } else {
            Renderer.add("- Le deck est recyclé");
            theDeck.addAll(discardPool);
            shuffle();
            discardPool.clear();
        }

    }

    public boolean isEmpty() {
        return theDeck.isEmpty();
    }

    public Card findCard(int id) {
        for (Card card : theDeck) {
            if (card.getId() == id) {
                return card;
            }
        }
        throw new CardNotFoundException(String.format("Card with id : %d cannot be found in the deck", id));
    }

    /**
     * Draws the first card of the deck and immediately places it in the hand of the inventory
     *
     * @param inventory inventory in which the hand will get the card
     * @return true if the draw was successful, false otherwise
     */
    public boolean drawCard(Inventory inventory) {
        if (isEmpty()) {
            recycle();
        }
        if (!isEmpty()) {
            Card drawnCard = theDeck.get(0);
            inventory.getHand().addCard(drawnCard);
            theDeck.remove(0);
            return true;
        }
        return false;
    }

    /**
     * Draws the first card of the deck and returns it
     *
     * @return the drawn card if there is one, null otherwise
     */
    public Card drawCard() {
        if (isEmpty()) {
            recycle();
        }
        if (!isEmpty()) {
            Card drawnCard = theDeck.get(0);
            theDeck.remove(0);
            return drawnCard;
        }
        return null;
    }

    public int size() {
        return theDeck.size();
    }

    public List<Card> sampleCard(int nbCard) {
        ArrayList<Card> cards = new ArrayList<>();
        Card card;
        nbCard=Math.min(nbCard, theDeck.size()+discardPool.size());
        for (int i = 0; i < nbCard; i++) {
            if (theDeck.isEmpty() && !discardPool.isEmpty()) {
                recycle();
            }
            if (!theDeck.isEmpty()) {
                card = theDeck.get(Util.getNextInt(theDeck.size()));
                theDeck.remove(card);
                cards.add(card);
            }
        }
        return cards;
    }

    public List<Card> getDeck() {
        return theDeck;
    }

    public List<Card> getDiscardPool() {
        return discardPool;
    }

    public void setTheDeck(ArrayList<Card> theDeck) {
        this.theDeck = theDeck;
    }

    public void setDiscardPool(List<Card> discardPool) {
        this.discardPool = discardPool;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deck=" + theDeck +
                '}';
    }



    @Override
    public Object clone() throws CloneNotSupportedException{
        Deck cloneDeck = (Deck) super.clone();

        ArrayList<Card> cloneTheDeck=new ArrayList<>();
        List<Card> cloneDiscardPool=new ArrayList<>();

        for (Card c: theDeck){
            AbstractCard abstC= (AbstractCard) c;
            cloneTheDeck.add((Card) abstC.clone());
        }

        for (Card c: discardPool){
            AbstractCard abstC= (AbstractCard) c;
            cloneDiscardPool.add((Card) abstC.clone());
        }

        cloneDeck.setTheDeck(cloneTheDeck);
        cloneDeck.setDiscardPool(cloneDiscardPool);
        return cloneDeck;
    }
}
