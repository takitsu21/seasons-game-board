package core.ia.inventory;

import core.cards.AbstractCard;
import core.cards.Card;
import core.util.CardUtil;

import java.awt.desktop.AboutEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hand implements Cloneable{
    private ArrayList<Card> cardsInHand;
    private Card[][] cardsByYear;

    public Hand(Card[][] cardsByYear) {
        this.cardsByYear = cardsByYear;
        cardsInHand = new ArrayList<>(Arrays.asList((cardsByYear[0])));
    }

    public void newUpdateHand(int year) {
        boolean allCardNull = true;
        for (Card card : cardsByYear[year -1]){
            if (card != null){
                allCardNull = false;
                break;
            }
        }
        if (!allCardNull){
            cardsInHand.addAll(Arrays.asList(cardsByYear[year - 1]));
            cardsByYear[year - 1] = new Card[0];
        }
    }

    public void updateHand(int nbYear, int nbMaxYears) {
        while (nbMaxYears - nbYear < cardsByYear.length - 1) {
            cardsInHand.addAll(Arrays.asList(cardsByYear[0]));
            removeFromHand();
        }
    }

    public void removeFromHand() {
        Card[][] newHand = new Card[cardsByYear.length - 1][cardsByYear[0].length];
        System.arraycopy(cardsByYear, 1, newHand, 0, cardsByYear.length - 1);
        cardsByYear = newHand;
    }


    //// Function about the cards
    public void addCard(Card card) {
        cardsInHand.add(card);
    }

    public List<Card> getCardsInHand() {
        return cardsInHand;
    }

    public void setCardsInHand(List<Card> cardsInHand) {
        this.cardsInHand = (ArrayList<Card>) cardsInHand;
    }

    public void removeCard(Card card) {
        cardsInHand.remove(card);
    }

    public Card[][] getCardsByYear() {
        return cardsByYear;
    }

    public void setCardsByYear(Card[][] cardsByYear) {
        this.cardsByYear = cardsByYear;
    }

    public String toStringCardInHand() {
        return CardUtil.toStringArrayListCards(cardsInHand);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Hand cloneHand= (Hand) super.clone();
        ArrayList<Card> cloneCardsInHand=new ArrayList<>(cardsInHand);
        cloneHand.setCardsInHand(cloneCardsInHand);
        Card[][] cloneCardsByYear = new Card[cardsByYear.length][cardsByYear[0].length];
        for (int i = 0; i < cardsByYear.length; i++) {
            if (cardsByYear[i].length > 0) {
                for (int j = 0; j < cardsByYear[i].length; j++) {
                    AbstractCard c = (AbstractCard) cardsByYear[i][j];
                    if (c!= null) {
                        cloneCardsByYear[i][j] = (Card) c.clone();
                    }
                }
            }

        }
        cloneHand.setCardsByYear(cloneCardsByYear);
        return cloneHand;
    }
}
