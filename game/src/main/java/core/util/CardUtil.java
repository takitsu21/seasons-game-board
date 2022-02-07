package core.util;

import core.board.enums.Energy;
import core.cards.Card;
import core.cards.EffectFrequency;
import core.cards.effects.*;
import org.json.simple.JSONArray;

import java.util.*;

public final class CardUtil {
    public static final Map<Class<?>, List<Class<?>>> combos = new HashMap<>();
    public static final Map<Class<?>, CardInfo> cardsBestMoves = new HashMap<>();

    static { // Creation des combos
        combos.put(BottesTemporelles.class, List.of(AmsugLongcoupEffect.class));
        combos.put(CoffretMerveilleuxEffect.class, List.of(GrimoireEnsorceleEffect.class,
                DeDeLaMaliceEffect.class));
        combos.put(DeDeLaMaliceEffect.class, List.of(
                FigrimAvaricieuxEffect.class,
                CoffretMerveilleuxEffect.class));
        combos.put(FigrimAvaricieuxEffect.class, List.of(DeDeLaMaliceEffect.class));
        combos.put(BatonDuPrintempsEffect.class, List.of(
                AmsugLongcoupEffect.class,
                DeDeLaMaliceEffect.class));
        combos.put(BourseIoEffect.class, List.of(
                PotionSavoirEffect.class,
                WaterAmuletEffect.class,
                CorneDuMendiant.class));
        combos.put(AmsugLongcoupEffect.class, List.of(
                BottesTemporelles.class,
                BatonDuPrintempsEffect.class));
        combos.put(PotionSavoirEffect.class, List.of(
                BourseIoEffect.class
        ));
        combos.put(WaterAmuletEffect.class, List.of(
                BourseIoEffect.class
        ));
        combos.put(CorneDuMendiant.class, List.of(
                BourseIoEffect.class,
                KairnDestructeurEffect.class
        ));
        combos.put(KairnDestructeurEffect.class, List.of(CorneDuMendiant.class));


        cardsBestMoves.put(AmsugLongcoupEffect.class,
                new CardInfo(List.of(1, 3), true));
        cardsBestMoves.put(BatonDuPrintempsEffect.class,
                new CardInfo(List.of(1), false));
        cardsBestMoves.put(MainFortuneEffect.class,
                new CardInfo(List.of(1), false));
        cardsBestMoves.put(BottesTemporelles.class,
                new CardInfo(List.of(3), true));
        cardsBestMoves.put(CoffretMerveilleuxEffect.class,
                new CardInfo(List.of(1), false));
        cardsBestMoves.put(CaliceDivinEffect.class,
                new CardInfo(List.of(2), false));
        cardsBestMoves.put(BourseIoEffect.class,
                new CardInfo(List.of(2), false));
        cardsBestMoves.put(KairnDestructeurEffect.class,
                new CardInfo(List.of(2), false));
        cardsBestMoves.put(PotionSavoirEffect.class,
                new CardInfo(List.of(1, 3), false));
        cardsBestMoves.put(WaterAmuletEffect.class,
                new CardInfo(List.of(1), false));
        cardsBestMoves.put(PotionPuissanceEffect.class,
                new CardInfo(List.of(2), false));
        cardsBestMoves.put(FireAmuletEffect.class,
                new CardInfo(List.of(2), false));
        cardsBestMoves.put(SceptreGrandeurEffect.class,
                new CardInfo(List.of(3), false));
        cardsBestMoves.put(SyllasLeFideleEffect.class,
                new CardInfo(List.of(1), true));
        cardsBestMoves.put(StatueBenieOlafEffect.class,
                new CardInfo(Collections.emptyList(), false));
        cardsBestMoves.put(WindAmuletEffect.class,
                new CardInfo(List.of(1), false));
        cardsBestMoves.put(GrimoireEnsorceleEffect.class,
                new CardInfo(Collections.emptyList(), false));
        cardsBestMoves.put(HeaumeRagfieldEffect.class,
                new CardInfo(List.of(3), false));
        cardsBestMoves.put(LewisGrisemineEffect.class,
                new CardInfo(List.of(2, 3), false));
        cardsBestMoves.put(SablierTempsEffect.class,
                new CardInfo(Collections.emptyList(), false));
        cardsBestMoves.put(EarthAmuletEffect.class,
                new CardInfo(Collections.emptyList(), false));
        cardsBestMoves.put(BalanceIshtarEffect.class,
                new CardInfo(Collections.emptyList(), false));
        cardsBestMoves.put(NariaLaProphetesseEffect.class,
                new CardInfo(Collections.emptyList(), false));
        cardsBestMoves.put(CorneDuMendiant.class,
                new CardInfo(Collections.emptyList(), false));
        cardsBestMoves.put(CubeRuniqueEolisEffect.class,
                new CardInfo(Collections.emptyList(), false));
        cardsBestMoves.put(PotionRevesEffect.class,
                new CardInfo(Collections.emptyList(), false));
        cardsBestMoves.put(PotionVieEffect.class,
                new CardInfo(Collections.emptyList(), false));
        cardsBestMoves.put(DeDeLaMaliceEffect.class,
                new CardInfo(List.of(1), false));
        cardsBestMoves.put(FigrimAvaricieuxEffect.class,
                new CardInfo(List.of(1), false));
        cardsBestMoves.put(VaseOublieYjangEffect.class,
                new CardInfo(List.of(1), false));
    }

    CardUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static EffectFrequency strFrequencyToEffectFrequency(String frequency) {
        if (frequency == null) {
            return EffectFrequency.ON_PLAY;
        }
        switch (frequency) {
            case "invok":
                return EffectFrequency.ON_PLAY;
            case "permanent":
                return EffectFrequency.EACH_TURN;
            case "activation":
                return EffectFrequency.ON_ACTIVATION;
            case "permanent | invok":
                return EffectFrequency.ON_PLAY_AND_EACH_TURN;
            default: //on peut pas juste faire default return ON_PLAY et virer le if et le return en dessous?
                break;
        }
        return EffectFrequency.ON_PLAY;
    }

    /**
     * convert json input to array list
     * @param energies json input data
     * @return List<Energy> energies converted
     */
    public static List<Energy> convertJsonArray(JSONArray energies) {
        ArrayList<Energy> energiesArray = new ArrayList<>();
        Energy currentEnergy;
        for (Object energy : energies) {
            currentEnergy = switch ((String) energy) {
                case "water" -> Energy.WATER;
                case "feather" -> Energy.WIND;
                case "fire" -> Energy.FIRE;
                default -> Energy.EARTH;
            };
            energiesArray.add(currentEnergy);
        }
        return energiesArray;
    }

    /**
     * Returns a string formatted as an array containing the name of the cards in the given arrayList
     *
     * @param cards Cards which name will be added to the string
     * @return a string formatted as follows: "[ name1, name2, name3 ]"
     */
    public static String toStringArrayListCards(ArrayList<Card> cards) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        if (cards.size() > 0) {
            for (int i = 0; i < cards.size() - 1; i++) {
                sb.append(cards.get(i).getName()).append(", ");
            }
            sb.append(cards.get(cards.size() - 1).getName());
        }
        sb.append(" ]");
        return sb.toString();
    }

    /**
     * @return a list containing the cards allowing the players to summon more cards
     */
    public static ArrayList<Class<?>> getCardsForMoreInvocation() {
        return new ArrayList<>(Arrays.asList(
                CaliceDivinEffect.class,
                PotionRevesEffect.class,
                PotionPuissanceEffect.class
        ));
    }

    /**
     * @return a list containing the cards how can played with time
     */
    public static ArrayList<Class<?>> getCardsPlayedWithTime() {
        return new ArrayList<>(List.of(
                BottesTemporelles.class
        ));
    }

    /**
     * @return a list containing the cards giving more cystals to the player through their effect
     */
    public static ArrayList<Class<?>> getCrystalCards() {
        return new ArrayList<>(Arrays.asList(
                BalanceIshtarEffect.class,
                BatonDuPrintempsEffect.class,
                BourseIoEffect.class,
                CoffretMerveilleuxEffect.class,
                DeDeLaMaliceEffect.class,
                EarthAmuletEffect.class,
                FigrimAvaricieuxEffect.class,
                HeaumeRagfieldEffect.class,
                PotionVieEffect.class,
                SceptreGrandeurEffect.class,
                StatueBenieOlafEffect.class));
    }

    /**
     * Browses the given list and checks for every card if it has a permanent effect.
     * @param listOfCards List of Card to check
     * @return a new list containing the class of every card that has a permanent effect in the given list
     */
    public static List<Class<?>> getPermanentEffectCardsClassIn(List<Card> listOfCards) {
        List<Class<?>> permanentEffectCards = new ArrayList<>();
        for (Card card : listOfCards) {
            if (card.hasPermanentEffect()) {
                permanentEffectCards.add(card.getClass());
            }
        }
        return permanentEffectCards;
    }

    /**
     * Browses the given list and checks for every card if it has a permanent effect.
     * @param listOfCards List of Card to check
     * @return a new list containing every card that has a permanent effect in the given list
     */
    public static List<Card> getPermanentEffectCardsIn(List<Card> listOfCards) {
        List<Card> permanentEffectCards = new ArrayList<>();
        for (Card card : listOfCards) {
            if (card.hasPermanentEffect()) {
                permanentEffectCards.add(card);
            }
        }
        return permanentEffectCards;
    }

    /**
     * @return a list containing the cards which can inflict a malus on the opponents
     */
    public static ArrayList<Class<?>> getMalusCards() {
        return new ArrayList<>(Arrays.asList(
                AmsugLongcoupEffect.class,
                FigrimAvaricieuxEffect.class,
                KairnDestructeurEffect.class,
                SyllasLeFideleEffect.class));
    }

    /**
     * Find best combos from a card
     *
     * @param cards          Current cards to compare
     * @param currentCompare card to be compare
     * @return a list of card in which we add every contained card in cards which have a good combo with the currentCompare
     * card
     */
    public static List<Card> findBestCombos(List<Card> cards, Class<?> currentCompare) {
        if (combos.containsKey(currentCompare)) {
            List<Card> cardCreator = new ArrayList<>();
            List<Class<?>> cardsClasses = combos.get(currentCompare);
            for (Card c : cards) {
                if (cardsClasses.contains(c.getClass()) && !c.getClass().equals(currentCompare)) {
                    cardCreator.add(c);
                }
            }
            return cardCreator;
        }
        return Collections.emptyList();
    }

    /**
     * Find best combos for {@param currentCompare}
     *
     * @param cards          Current cards to compare
     * @param currentCompare card to be compare
     * @return a list of card in which we add every contained card in cards which have a good combo with the currentCompare
     * card
     */
    public static List<Card> findBestCombos(List<Card> cards, Card currentCompare) {
        return findBestCombos(cards, currentCompare.getClass());
    }

    /**
     * Find every possible combos from {@param cards}
     *
     * @param cards Current cards to compare
     * @return all combos for each card of {@param cards}
     */
    public static Map<Card, List<Card>> findAllBestCombos(List<Card> cards) {
        Map<Card, List<Card>> cardCreator = new HashMap<>();
        for (Card c : cards) {
            cardCreator.put(c, findBestCombos(cards, c.getClass()));
        }
        return cardCreator;
    }

    /**
     * create players hand according to the best season to play a card
     *
     * @param cards  cards to choose
     * @param config game config
     * @return hand of the player
     */
    public static Card[][] classifiedCardsByYearAndCombos(List<Card> cards, Config config) {
        Map<Integer, List<Card>> cardsByYear = new HashMap<>();
        for (int i = 0; i < config.getNbYears() + 1; i++) {
            List<Card> currentCardsByYear = getCardsByYear(cards, i);
            cardsByYear.put(i, currentCardsByYear);
            cards.removeAll(currentCardsByYear);
        }
        rearrange(cardsByYear);
        Card[][] cardsInHand = new Card[config.getNbYears()][config.getNbCardsPerYear()];
        for (int i = 0; i < config.getNbYears(); i++) {
            cardsInHand[i] = cardsByYear.get(i).toArray(new Card[0]);
        }
        return cardsInHand;
    }

    /**
     * construct the set of card for a specific year
     *
     * @param cards sample cards
     * @param year  construct the deck with that param
     * @return set of card for this year
     */
    private static List<Card> getCardsByYear(List<Card> cards, int year) {
        List<Card> cardsToKeep = new ArrayList<>();
        for (Card c : cards) {
            if (year > 2) {
                cardsToKeep.add(c);
            } else {
                for (int i : cardsBestMoves.get(c.getClass()).getYear()) {
                    if (i - 1 == year) {
                        cardsToKeep.add(c);
                    }
                }
            }
        }
        return cardsToKeep;
    }

    /**
     * redistribute unused card between the years
     *
     * @param cardsByYear each set of card associated with the year
     * @return rearranged set of cards
     */
    private static void rearrange(Map<Integer, List<Card>> cardsByYear) {
        LinkedList<Card> toRedistribute = new LinkedList<>();
//        Map<Integer, List<Card>> copyCardsByYear = new HashMap<>(cardsByYear);
        for (Map.Entry<Integer, List<Card>> entry : cardsByYear.entrySet()) {
            if (entry.getValue().size() > 2 && entry.getKey() != 3) { // si on a trop de cartes dans une année et si on doit en redistribuer
                List<Card> subList = entry.getValue().subList(3, entry.getValue().size());
                toRedistribute.addAll(subList);
                entry.getValue().removeAll(subList);
            }
        }
        toRedistribute.addAll(cardsByYear.get(3));
        for (Map.Entry<Integer, List<Card>> entry : cardsByYear.entrySet()) {
            if (entry.getKey() != 3) {
                for (int i = entry.getValue().size(); i < 3; i++) {
                    entry.getValue().add(toRedistribute.pop());
                }
            }
        }
    }
}
