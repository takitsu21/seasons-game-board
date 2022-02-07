package core.util;

import core.board.enums.Energy;
import core.cards.Card;
import core.cards.comparator.PrestigePointsCardComparator;
import core.dice.Dice;
import core.ia.Player;
import core.ia.strategy.choose.ChooseBestCombos;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.ChooseRandom;

import java.util.*;
import java.util.function.Predicate;

public class ChooseUtil {
    private final static ChooseUtil instance = new ChooseUtil();

    private ChooseUtil() {}

    public static ChooseUtil getInstance() {
        return instance;
    }

    /**
     * found the similarities between the energies cost of the card and the player energy stock
     * @param otherPlayers all players except you
     * @param card current card to analyse
     * @return best player that we found according to the energies of the card
     */
    public Player getBestSimilaritiesPlayerEnergies(List<Player> otherPlayers, Card card) {
        if (card == null) {
            return null;
        }
        int oldSimilarityCount = 0;
        Player currentPlayer = otherPlayers.get(0);
        for (Player p : otherPlayers) {
            List<Energy> tmp = new ArrayList<>();
            int count = 0;

            for (Energy energy : card.getEnergyCost()) {
                if (!tmp.contains(energy)) {
                    count += Collections.frequency(p.getFacadeIA().getEnergyStock(), energy);
                    tmp.add(energy);
                }
            }
            if (count > oldSimilarityCount) {
                oldSimilarityCount = count;
                currentPlayer = p;
            }
        }
        return oldSimilarityCount>0? currentPlayer: null;
    }

    /**
     * find the card according to the predictor
     * @param hand player hand
     * @param predicate boolean predicator to test a specific condtion
     * @return card if {@param predicate} is true
     */
    public Card chooseCardPredictor(List<Card> hand, Predicate<Card> predicate) {
        for (Card cHand : hand) {
            if (predicate.test(cHand)) {
                return cHand;
            }
        }
        return null;
    }

    /**
     * construct a list of card according to the predictor
     * @param hand player hand
     * @param predicate boolean predicator to test a specific condtion
     * @return list of card if {@param predicate} is true
     */
    public List<Card> chooseCardsPredictor(List<Card> hand, Predicate<Card> predicate) {
        List<Card> ret = new ArrayList<>();
        for (Card cHand : hand) {
            if (predicate.test(cHand)) {
                ret.add(cHand);
            }
        }
        return !ret.isEmpty() ? ret : hand;
    }

    public Map<Player, Card> chooseCardsForNaria(Player player, List<Player> otherPlayer, List<Card> cards, Card selfCard) {
        Map<Player, Card> map = new HashMap<>();
        //List<Player> otherPlayers = player.getFacadeIA().getOtherPLayers();
        cards.remove(selfCard);
        Card card;
        map.put(player, selfCard);
        cards.remove(selfCard);
        for (Player p : otherPlayer) {
            card = (Card) ChooseFirst.getInstance().chooseFirst(cards);
            map.put(p, card);
            cards.remove(card);
        }
        return map;
    }

    /**
     * choose a dice adapted to the card energy cost
     * @param player player
     * @param card chosen card to adapt the energy to take from the dice
     * @return one for the in the diceset
     */
    public Dice chooseDiceByEnergyForCards(Player player, Card card) {
        if (card == null) {
            return null;
        }
        Dice[] diceSet = player.getFacadeIA().getDiceSet();
        int count = 0;
        int idx = 0;
        for (int i = 0; i < diceSet.length;i++) {
            int tmpCount = 0;
            if (diceSet[i].getCurrentFace().getEnergy() != null) {
                for (Energy energy : diceSet[i].getCurrentFace().getEnergy()) {
                    if (card.getEnergyCost().contains(energy)) {
                        tmpCount++;
                    }
                }
                if (count < tmpCount) {
                    count = tmpCount;
                    idx = i;
                }
            }
        }
        if (count > 0) {
            return diceSet[idx];
        }
        return null;
    }

}
