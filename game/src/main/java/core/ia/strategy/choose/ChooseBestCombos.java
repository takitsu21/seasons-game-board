package core.ia.strategy.choose;

import core.cards.Card;
import core.cards.comparator.EnergySizeComparator;
import core.util.CardUtil;
import core.util.Pair;
import util.Util;

import java.util.ArrayList;
import java.util.List;

public class ChooseBestCombos {

    private static final ChooseBestCombos instance = new ChooseBestCombos();

    private ChooseBestCombos() {
    }

    public static ChooseBestCombos getInstance() {
        return instance;
    }

    public Card chooseCardForCombos(List<Card> cards) {
        if (cards == null || cards.isEmpty()) {
            return null;
        }
        int comboPower = 0;
        Card ret = null;
        for (Card c : cards) {
            List<Card> comboCards = CardUtil.findBestCombos(cards, c.getClass());
            int comboCardsSize = comboCards.size();
            if (comboPower < comboCardsSize) {
                comboPower = comboCardsSize;
                ret = c;
            }
        }
        return comboPower <= 0 ? null : ret;
    }

    public Pair<Card, Integer> chooseCardForCombos(List<Card> cards, List<Card> cardsInhand) {
        Card c = null;
        int comboPoint = 0;
        if (cardsInhand.isEmpty()) {
            cardsInhand = new ArrayList<>(cards);
        }
        for (Card cHand : cards) {
            List<Card> bestCombos = CardUtil.findBestCombos(cardsInhand, cHand);
            int bestCombosSize = bestCombos.size();
            if (comboPoint < bestCombosSize) {
                comboPoint = bestCombosSize;
                c = cHand;
            }
        }
        return comboPoint <= 0 ? new Pair<>(null, comboPoint): new Pair<>(c, comboPoint);
    }

    public List<Card> getComboCards(List<Card> cards, List<Card> cardsOnBoard) {
        List<Pair<Card, Integer>> maxEnergiesComboList = new ArrayList<>();
        if (cardsOnBoard.isEmpty()) {
            cardsOnBoard = new ArrayList<>(cards);
        }
        for (Card cHand : cards) {
            List<Card> bestCombos = CardUtil.findBestCombos(cardsOnBoard, cHand);
            int bestCombosSize = bestCombos.size();
            if (bestCombosSize > 0) {
                maxEnergiesComboList.add(new Pair<>(cHand, bestCombosSize));
            }
        }
        maxEnergiesComboList.sort(new EnergySizeComparator());
        List<Card> ret = new ArrayList<>();
        for (Pair<Card, Integer> pair : maxEnergiesComboList) {
            ret.add(pair.getKey());
        }
        return ret;
    }

    public Card chooseCardForCombosMaxEnergiesToCopy(List<Card> cards, List<Card> cardsOnBoard) {
        List<Card> cardsCombo=getComboCards(cards, cardsOnBoard);
        return cardsCombo.isEmpty()? null: cardsCombo.get(0);
    }

    public Card worstComboCard(List<Card> cards, List<Card> cardsInHand) {
        double comboPoint = Double.POSITIVE_INFINITY;
        Card ret = null;
        for (Card c : cards) {
            List<Card> bestCombos = CardUtil.findBestCombos(cardsInHand, c);
            int worstCombosSize = bestCombos.size();
            if (comboPoint > worstCombosSize) {
                comboPoint = worstCombosSize;
                ret = c;
            }
        }
        return comboPoint>0? null: ret;
    }
}
