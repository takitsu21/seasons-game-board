package core.ia.strategy.choose;

import core.cards.Card;
import core.cards.comparator.CostCardComparator;
import core.cards.comparator.PrefCardListComparator;
import core.ia.Player;
import core.util.Config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChooseFromPrefList {

    private static final ChooseFromPrefList instance = new ChooseFromPrefList();

    private ChooseFromPrefList() {}

    public static ChooseFromPrefList getInstance() {
        return instance;
    }

    /**
     * Chooses a random card in the given list and returns it is in the list of preferred cards.
     *
     * @param cardList List of cards that will be checked
     * @param prefCardsList List of cards we're looking for
     * @return a Card of prefCardsList if there's one in cardList, null otherwise.
     */
    public Card chooseCardToGetFromList(List<Card> cardList, List<Class<?>> prefCardsList) {
        ArrayList<Card> copyOfCards = new ArrayList<>(cardList); //on copie les cartes pour pas modifier la vraie liste

        copyOfCards.sort(new PrefCardListComparator(prefCardsList));

        for (Card currentCard : copyOfCards) {
            if (prefCardsList.contains(currentCard.getClass())) { //si la carte n'est pas une de celles qu'on préfère
                return currentCard; //on la return
            }
        }
        return null; //si on a aucune carte qu'on préfère, on return null pour changer de strategie
    }

    /**
     * Chooses a random card in the given list and returns it if it isn't in the list of preferred cards.
     * If every card belongs to the list of preferred cards then it returns a randomly choosen one.
     *
     * @param cards List of cards to check
     * @param prefCardsList List of preferred cards
     * @return A random card that isn't in the list of preferred cards if it can, a random card otherwise, null if the list of cards is empty.
     */
    public Card chooseCardToRemoveFromBoard(List<Card> cards, List<Class<?>> prefCardsList) {
        ArrayList<Card> copyOfCards = new ArrayList<>(cards); //on copie les cartes pour pas modifier la vraie liste
        copyOfCards.sort(new PrefCardListComparator(prefCardsList));
        for (Card currentCard : copyOfCards) {
            if (!prefCardsList.contains(currentCard.getClass())) { //si la carte n'est pas une de celles qu'on préfère
                return currentCard; //on la return
            }
        }
        return null;
    }

    /**
     * Chooses a set of Cards which will later be used to create a Hand.
     * It will first take the cards in the given deck that correspond to a Card in the preferred card list (if there is any)
     * then choose randomly the rest if needed.
     *
     * @param deck List of cards from which the cards will be choosen
     * @param prefCardsList List of preferred cards
     * @param config Config from which we get the dimension of the returned arrays of cards
     * @return the arrays of cards which will be used to create a Hand
     */
    public Card[][] chooseInitHand(List<Card> deck, List<Class<?>> prefCardsList, Config config) {

        Card[][] cards = new Card[config.getNbYears()][config.getNbCardsPerYear()];
        Card card;
        for (int i = 0; i < config.getNbYears(); i++) {
            for (int j = 0; j < config.getNbCardsPerYear(); j++) {
                card = ChooseCardComparator.getInstance().chooseCardMinCompare(deck, new PrefCardListComparator(prefCardsList));
                deck.remove(card);
                cards[i][j] = card;
            }
        }
        return cards;
    }

    /**
     * Copies the player's summonable cards (to not change the actual hand) and shuffles it.
     * Then browses it to check if it finds a card from the preferred cards list and
     * returns it if it's the case
     *
     * @param player Player whose hand will be checked
     * @param prefCardsList List of preferred cards
     * @return The choosen card to summon, or null if it can't find a preferred card that is summonable
     */
    public Card chooseCardToSummon(Player player, List<Class<?>> prefCardsList) {
        List<Card> copyOfSummonableCards = new ArrayList<>(player.getFacadeIA().getSummonableCards());

        copyOfSummonableCards.sort(new PrefCardListComparator(prefCardsList));
        for (Card card : copyOfSummonableCards) { //on vérifie la carte courante est une de nos préférrées
            if (prefCardsList.contains(card.getClass())) {
                return card;
            }
        }
        return null;
    }

    /**
     * Copies the player's hand (to not change the actual hand) and shuffles it.
     * Then checks if there's any preferred card in it and summons the first one it finds if there's any.
     *
     * @param player Player whose hand will be checked
     * @param prefCardsList List of preferred cards
     * @return The choosen card to be summon for free or null if there is no preferred card
     */
    public Card chooseCardToSummonForFree(Player player, List<Class<?>> prefCardsList) {
        List<Card> cardInHand = new ArrayList<>(player.getFacadeIA().getCardInHand());

        cardInHand.sort(new PrefCardListComparator(prefCardsList));
        for (Card card : cardInHand) {
            if (prefCardsList.contains(card.getClass())) {
                return card;
            }
        }
        return null;
    }

    public boolean chooseToKeepCard(Card card, List<Class<?>> prefCardsList) {
        return prefCardsList.contains(card.getClass());
    }
}
