package core.ia.strategy.choose;

import core.cards.Card;
import core.util.Config;

import java.util.LinkedList;
import java.util.List;

public class ChooseActivate {
    private static final ChooseActivate instance = new ChooseActivate();

    private ChooseActivate() {
    }

    public static ChooseActivate getInstance() {
        return instance;
    }

    public Card[][] initHand(List<Card> deck, Config config) {
        deck.sort((o1, o2) -> Boolean.compare(o2.isActivable(), o1.isActivable()));
        LinkedList<Card> linkedList = new LinkedList<>(deck);
        Card[][] cards = new Card[config.getNbYears()][config.getNbCardsPerYear()];
        for (int i = 0; i < config.getNbYears(); i++) {
            for (int j = 0; j < config.getNbCardsPerYear(); j++) {
                cards[i][j] = linkedList.pop();
            }
        }
        return cards;
    }
}
