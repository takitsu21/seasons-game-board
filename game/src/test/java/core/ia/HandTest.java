package core.ia;

import core.cards.Card;
import core.cards.Deck;
import core.ia.inventory.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {
    private Hand hand;
    private Card card1;
    private Card card2;
    private Deck deck;


    @BeforeEach
    public void setUp() {
        deck = new Deck();
        card1 = deck.findCard("amulette de terre");
        card2 = deck.findCard(2);
        hand = new Hand(new Card[][]{new Card[]{card1}, new Card[]{card2}});
    }

    @Test
    void removeCard() {
        assertEquals(1, hand.getCardsInHand().size());
        hand.removeCard(card1);
        assertEquals(0, hand.getCardsInHand().size());
    }


    @Test
    void updateHandTest() {
        List<Card> cardInHand = new ArrayList<>();
        cardInHand.add(card1);
        assertEquals(cardInHand, hand.getCardsInHand());
        hand.newUpdateHand(2);
        cardInHand.add(card2);
        assertEquals(cardInHand, hand.getCardsInHand());
    }

    @Test
    void toStringCardInHandTest() {
        assertEquals("[ Amulette de terre ]", hand.toStringCardInHand());

        hand.addCard(card2);
        assertEquals("[ Amulette de terre, Amulette de feu ]", hand.toStringCardInHand());

        hand.removeCard(card1);
        hand.removeCard(card2);

        assertEquals("[  ]", hand.toStringCardInHand());
    }

}
