package core.board;

import core.board.enums.Energy;
import core.board.enums.Seasons;
import core.cards.Card;
import core.dice.Dice;
import core.dice.Face;
import core.ia.EnumPlayerAction;
import core.ia.Player;
import core.ia.inventory.BonusType;
import core.ia.inventory.EnergyStock;
import core.ia.inventory.Inventory;
import core.ia.inventory.Invocation;
import core.util.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FacadeIA implements Cloneable{
    private Board board;
    private Player player;
    private Config config;

    public FacadeIA(Board board, Player player, Config config) {
        this.board = board;
        this.player = player;
        this.config = config;
    }

    public FacadeIA(Player player, Config config) {
        this.player = player;
        this.config = config;
    }

    public Board getBoard() {
        return board;
    }

    public Config getConfig() {
        return config;
    }

    public int getPrestigePointsCards() {
        return board.getInventories().get(player).getPrestigePointsCard();
    }

    public int getCrystalPoints() {
        return board.getInventories().get(player).getCrystals();
    }


    public int getInvocationPoint() {
        return board.getInventories().get(player).getInvocation().getInvocationPoints();
    }

    public int getCurrentInvocationPoint() {
        return board.getInventories().get(player).getInvocation().getCurrentInvocations();
    }

    public boolean isSummonable(Card card, List<Energy> copyOfEnergyCost) {
        return board.getInventories().get(player).isSummonable(card, copyOfEnergyCost);
    }

    public List<Card> getSummonableCards() {
        List<Card> cardInHand = getCardInHand();
        List<Card> sumonableCards = new ArrayList<>();
        for (Card card : cardInHand) {
            List<Energy> copyOfEnergyCost = getTriggerMainFortuneIfPossible(card);
            if (board.getInventories().get(player).isSummonable(card, copyOfEnergyCost)) {
                sumonableCards.add(card);
            }
        }
        return sumonableCards;
    }

    public int getNbCrystals(Player player) {
        return board.getInventories().get(player).getCrystals();
    }

    /**
     * @return The name of the players who is different than the one linked to this facade and who has the most crystals.
     * It returns only the name so no IA can access the player itself.
     */
    public HashMap<String, Integer> getOtherPlayersCrystalsAndBoard() {
        List<Player> otherPlayers = getOtherPLayers();
        HashMap<String, Integer> playersNameAndCrystals = new HashMap<>();
        for (Player currentPlayer : otherPlayers) {
            playersNameAndCrystals.put(currentPlayer.getName(), board.getInventories().get(currentPlayer).getCrystals());
        }
        return playersNameAndCrystals;
    }

    /**
     * @param playerName Name of the player we want the board of
     * @return The cards on the given player's board
     */
    public List<Card> getPlayersBoard(String playerName) {
        Player player = getPlayer(playerName);
        if (player != null) {
            return board.getInventories().get(player).getInvocation().getCardsOnBoard();
        }
        return new ArrayList<>();
    }

    /**
     * @return The list containing the board of every player that isn't the one owning this facade
     */
    public List<List<Card>> getEveryOtherPlayersBoard() {
        List<Player> players = getOtherPLayers();
        List<List<Card>> boards = new ArrayList<>();
        for (Player currentPlayer : players) {
            boards.add(board.getInventories().get(currentPlayer).getInvocation().getCardsOnBoard());
        }
        return boards;
    }

    public boolean anyCardCostIsEnough() {
        Inventory inventory = board.getInventories().get(player);
        for (Card card : getCardInHand()) {
            List<Energy> possiblyModifiedCost = inventory.triggerMainFortuneIfPossible(card, player);
            if (inventory.hasEnoughCrystals(card) && inventory.getEnergyStock().hasEnoughEnergy(possiblyModifiedCost, inventory.getInvocation().getCardsOnBoard())) {
                return true;
            }
        }
        return false;
    }

    public boolean hasEnoughEnergy(Card card) {
        Inventory inventory = board.getInventories().get(player);
        return inventory.getEnergyStock().hasEnoughEnergy(card.getEnergyCost(), inventory.getInvocation().getCardsOnBoard());
    }

    public List<Energy> getTriggerMainFortuneIfPossible(Card card) {
        return board.getInventories().get(player).triggerMainFortuneIfPossible(card, player);
    }

    public int playersInvocationsLeft(String playerName) {
        Player player = getPlayer(playerName);
        Invocation playersInvoc = board.getInventories().get(player).getInvocation();
        return playersInvoc.getInvocationPoints() - playersInvoc.getCurrentInvocations();
    }

    public boolean hasInvocationsLeft() {
        Inventory inventory = board.getInventories().get(player);
        return inventory.hasInvocationsLeft();
    }
    //// Dice


    public Dice[] getDiceSet() {
        return board.getDices().getSetOfDices();
    }

    ///// Energy

    public List<Energy> getEnergyStock() {
        return getEnergyStock(player);
    }

    public List<Energy> getEnergyStock(Player player) {
        return board.getInventories().get(player).getEnergyStock().getEnergyStock();
    }

    /**
     * @param name Name of the player we want the energie stock of
     * @return The energies in the given player's energy stock
     */
    public List<Energy> getPlayersEnergyStock(String name) {
        return getEnergyStock(getPlayer(name));
    }

    public int getMaxSizeEnergyStock(Player player) {
        return board.getInventories().get(player).getEnergyStock().getNbMaxEnergy();
    }

    public List<Energy> getAllTypeEnergy() {
        List<Energy> energyList = new ArrayList<>();
        for (Energy energy : Energy.values()) {
            if (energy != Energy.ERROR) {
                energyList.add(energy);
            }
        }
        return energyList;
    }

    public List<List<Energy>> getPlayersListOfMultipleEnergy(int n, String playerName) {
        Player player = getPlayer(playerName);
        EnergyStock energyStock = board.getInventories().get(player).getEnergyStock();
        return energyStock.listOfMultipleEnergy(n);
    }

    public List<List<Energy>> getListOfMultipleEnergy(int n) {
        EnergyStock energyStock = board.getInventories().get(player).getEnergyStock();
        return energyStock.listOfMultipleEnergy(n);
    }

    public Energy missingEnergy(List<Card> cardInHand) {
        if (cardInHand != null) {
            for (Card card : cardInHand) {
                if (!hasEnoughEnergy(card)) {
                    for (Energy energy : card.getEnergyCost()) {
                        if (getEnergyStock().isEmpty()) {
                            return energy;
                        }
                        for (Energy energyInStock : getEnergyStock()) {
                            if (energy != energyInStock) {
                                return energy;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public Energy uselessEnergy(List<Card> cardInHand) {
        ArrayList<Energy> energyNeed = new ArrayList<>();
        ArrayList<Energy> energyStock = new ArrayList<>(getEnergyStock());

        if (cardInHand != null) {
            for (Card card : cardInHand) {
                energyNeed.addAll(card.getEnergyCost());
            }
        }

        int nbWaterNeed = Collections.frequency(energyNeed, Energy.WATER);
        int nbFireNeed = Collections.frequency(energyNeed, Energy.FIRE);
        int nbEarthNeed = Collections.frequency(energyNeed, Energy.EARTH);
        int nbWindNeed = Collections.frequency(energyNeed, Energy.WIND);

        int nbWaterStock = Collections.frequency(energyStock, Energy.WATER);
        int nbFireStock = Collections.frequency(energyStock, Energy.FIRE);
        int nbEarthStock = Collections.frequency(energyStock, Energy.EARTH);
        int nbWindStock = Collections.frequency(energyStock, Energy.WIND);

        if (nbWaterNeed < nbWaterStock) {
            return Energy.WATER;
        }
        if (nbFireNeed < nbFireStock) {
            return Energy.FIRE;
        }
        if (nbWindNeed < nbWindStock) {
            return Energy.WIND;
        }
        if (nbEarthNeed < nbEarthStock) {
            return Energy.EARTH;
        }

        return null;
    }

    public ArrayList<Energy> uselessSimilarEnergy(List<Card> cardInHand, int n) {
        ArrayList<Energy> energyNeed = new ArrayList<>();
        ArrayList<Energy> energyStock = new ArrayList<>(getEnergyStock());

        List<List<Energy>> listSimilarEnergy = getListOfMultipleEnergy(n);

        if (cardInHand != null) {
            for (Card card : cardInHand) {
                energyNeed.addAll(card.getEnergyCost());
            }
        }

        int nbWaterNeed = Collections.frequency(energyNeed, Energy.WATER);
        int nbFireNeed = Collections.frequency(energyNeed, Energy.FIRE);
        int nbEarthNeed = Collections.frequency(energyNeed, Energy.EARTH);
        int nbWindNeed = Collections.frequency(energyNeed, Energy.WIND);

        int nbWaterStock = Collections.frequency(energyStock, Energy.WATER);
        int nbFireStock = Collections.frequency(energyStock, Energy.FIRE);
        int nbEarthStock = Collections.frequency(energyStock, Energy.EARTH);
        int nbWindStock = Collections.frequency(energyStock, Energy.WIND);

        for (List<Energy> e : listSimilarEnergy) {
            if (e.get(0) == Energy.WATER) {
                if (nbWaterNeed <= nbWaterStock - n) {
                    return (ArrayList<Energy>) e;
                }
            } else if (e.get(0) == Energy.FIRE) {
                if (nbFireNeed <= nbFireStock - n) {
                    return (ArrayList<Energy>) e;
                }
            } else if (e.get(0) == Energy.WIND) {
                if (nbWindNeed <= nbWindStock - n) {
                    return (ArrayList<Energy>) e;
                }
            } else if (e.get(0) == Energy.EARTH) {
                if (nbEarthNeed <= nbEarthStock - n) {
                    return (ArrayList<Energy>) e;
                }
            }
        }
        return new ArrayList<>();
    }

    ///// Player Action

    public List<EnumPlayerAction> getPossibleAction() {
        return board.getInventories().get(player).findPossibleAction(board);
    }


    //// Bonus

    public List<BonusType> getPossibleBonus() {
        return board.getInventories().get(player).getPossibleBonus();
    }


    ///// Card

    public List<Card> getCardInHand() {
        return board.getInventories().get(player).getCardsInHand();
    }

    /**
     * Returns the activable cards on the player's board
     *
     * @return Every cards on the board that can be activated
     */
    public List<Card> getMagicCardsOnBoard(Player player) {
        List<Card> playerBoard = board.getInventories().get(player).getInvocation().getCardsOnBoard();
        List<Card> magicObjects = new ArrayList<>();

        for (Card card : playerBoard) {
            if (card.isMagic()) {
                magicObjects.add(card);
            }
        }

        return magicObjects;
    }

    public List<Card> getMagicCardsOnBoard() {
        return getMagicCardsOnBoard(player);
    }

    // Activate in the board
    public List<Card> getActivableCard() {
        List<Card> activableCards = new ArrayList<>();
        List<Card> playersCards = board.getInventories().get(player).getInvocation().getCardsOnBoard();
        for (Card card : playersCards) {
            if (card.isActivable()) {
                activableCards.add(card);
            }
        }
        return activableCards;
    }

    public int getCursor() {
        return board.getCurrentCursor();
    }

    ///// PLayers
    public List<Player> getOtherPLayers() {
        List<Player> players = new ArrayList<>();
        for (Player p : board.getPlayers()) {
            if (!p.equals(player)) {
                players.add(p);
            }
        }
        return players;
    }

    public int calculatePrestigePoints(){
        return board.getInventories().get(player).calculatePrestigePoint();
    }

    public int calculatePrestigePoints2(){
        return board.getInventories().get(player).calculatePrestigePoint2();
    }

    //// State of the game
    public Seasons getSeason() {
        return board.getYear().getCurrentSeason();
    }

    public int getNbYear() {
        return board.getYear().getNbYear();
    }

    public Seasons getNextSeason() {
        return board.getYear().getNextSeason();
    }

    public Seasons getPreviousSeason() {
        return board.getYear().getPreviousSeason();
    }

    public List<Card> getCardOnBoard() {
        return board.getInventories().get(player).getInvocation().getCardsOnBoard();
    }

    public Year getYear() {
        return board.getYear();
    }

    public Face getDiceCurrentFace(Player player) {
        return board.getInventories().get(player).getCurrentDice().getCurrentFace();
    }

    public Face getDiceCurrentFace() {
        return getDiceCurrentFace(player);
    }

    private Player getPlayer(String name) {
        for (Player otherPLayer : getOtherPLayers()) {
            if (otherPLayer.getName().equals(name)) {
                return otherPLayer;
            }
        }
        return null;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        FacadeIA cloneFacadeIA = (FacadeIA) super.clone();

        return cloneFacadeIA;
    }

    public int getAmountOfPlayers() {
        return board.getPlayers().length;
    }
}
