package core.ia.strategy.choose;

import core.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ChooseCardComparator {

    private static final ChooseCardComparator instance = new ChooseCardComparator();

    private ChooseCardComparator() {
        // Singleton
    }

    public static ChooseCardComparator getInstance() {
        return instance;
    }

    public Card chooseCardMaxCompare(List<Card> cards, Comparator<Card> comparator) {
        if (!cards.isEmpty()) {
            return Collections.max(cards, comparator);
        }
        return null;
    }

    public Card chooseCardMinCompare(List<Card> cards, Comparator<Card> comparator) {
        if (!cards.isEmpty()) {
            return Collections.min(cards, comparator);
        }
        return null;
    }

    public List<Card> sortCardCompare(List<Card> cards, Comparator<Card> comparator) {
        if (cards != null && !cards.isEmpty()) {
            ArrayList<Card> arrayList = new ArrayList<>(cards);
            arrayList.sort(comparator);
            return arrayList;
        }
        return null;
    }
}
