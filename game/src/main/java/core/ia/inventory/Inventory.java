package core.ia.inventory;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.Card;
import core.cards.EffectFrequency;
import core.cards.effects.BatonDuPrintempsEffect;
import core.cards.effects.MainFortuneEffect;
import core.cards.effects.VaseOublieYjangEffect;
import core.dice.Dice;
import core.ia.EnumPlayerAction;
import core.ia.Player;
import core.util.Renderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Inventory implements Cloneable{
    private PlayerEnergyStock energyStock = new PlayerEnergyStock();
    private Bonus bonus = new Bonus();
    private Hand hand;
    private Dice currentDice;
    private boolean canCrystalize = false;
    private int bonusCrystal = 0;
    private int crystals = 0;

    private int prestigePoints = 0;
    private Invocation invocation = new Invocation();


    /**
     * Checks if a player has the ressources to put a given card on their board.
     * If the player has enough ressources and the card exists in their hand, removes the card from the hand and adds it
     * to the board.
     *
     * @param card The card the player is playing
     * @return whether the summoning was successful or not
     */
    public boolean summonCard(Card card, Board board) {
        Renderer.add(String.format("- Tente d'invoquer %s", card.getName()));
        // on prépare la carte en particulier par rapport aux nombres de joueurs
        card.prepare(board);
        //on réduit le coût avant de tout check
        List<Energy> copyOfEnergyCost = triggerMainFortuneIfPossible(card, getPlayerByInventory(board));
        if (isSummonable(card, copyOfEnergyCost)) {

            boolean successfullyRemoved = hand.getCardsInHand().remove(card);
            if (successfullyRemoved) {

                crystals -= card.getCrystalCost();

                if (!payEnergyCost(copyOfEnergyCost)) {
                    return false;
                }

                invocation.getCardsOnBoard().add(card);

                invocation.incrementCurrentInvocations();
                Renderer.add("- Invoque la carte: " + card.getName());

            }
            return successfullyRemoved;
        } else {
            Renderer.add("- L'invocation échoue");
            return false;
        }
    }

    public boolean isSummonable(Card card, List<Energy> possiblyModifiedCost) {
        return hasInvocationsLeft() && hasEnoughCrystals(card)
                && getEnergyStock().hasEnoughEnergy(possiblyModifiedCost, invocation.getCardsOnBoard());
    }

    public boolean anyCardSummonable(Player player) {
        for (Card card: getCardsInHand()) {
            List<Energy> possiblyModifiedCost = triggerMainFortuneIfPossible(card, player);
            if(isSummonable(card, possiblyModifiedCost)){
                return true;
            }
        }
        return false;
    }

    /**
     * Summons a card for free
     *
     * @param card  The card the player is summoning
     * @param board Board from which we retrieve the necessary information to summon
     * @return whether the summoning was successful or not
     */
    public boolean summonForFree(Card card, Board board) {
        if (hasInvocationsLeft()) {
            boolean successfullyRemoved = hand.getCardsInHand().remove(card);
            if (successfullyRemoved) {

                card.prepare(board);
                invocation.getCardsOnBoard().add(card);
                invocation.incrementCurrentInvocations();
                Renderer.add("-- Invoque gratuitement la carte: " + card.getName());

            }
            return successfullyRemoved;
        }
        return false;
    }

    /**
     * Removes the given card from the board and sends it back to the hand.
     * Similar to Invocation.removeCardFromBoard except that it adds the card in the hand.
     *
     * @param card card to remove from the board
     */
    public void retireCardFromBoard(Card card) {
        invocation.removeCardFromBoard(card);
        hand.addCard(card);
    }

    public List<BonusType> getPossibleBonus() {
        List<BonusType> possibleBonus = new ArrayList<>();
        if (energyStock.size() >= 2) {
            possibleBonus.add(BonusType.CHANGE_ENERGY);
        }
        if (!canCrystalize && !bonus.getInUseCrystallizeBonus()) {
            possibleBonus.add(BonusType.CRYSTALLIZE);
        }
        possibleBonus.add(BonusType.ADD_INVOCATION);
        return possibleBonus;
    }

    /**
     * Checks if there's a Baton du Printemps or a Vase Oublié d'Yjang on the player's board, and uses it if there's one.
     *
     * @param board  Board needed to call the effect of the (potential) Vase
     * @param player Player which the Vase would have an effect on
     */
    public void triggerVaseAndBaton(Board board, Player player) {
        for (Card c : getInvocation().getCardsOnBoard()) {
            if (c instanceof VaseOublieYjangEffect || c instanceof BatonDuPrintempsEffect) { //si c'est un Vase Oublié d'Yjang ou un Bâton du Printemps
                c.use(board, player);
            }
        }
    }

    /**
     * Checks if there's a Main de la Fortune on the player's board, and reduces the price in Energy the player
     * will have to pay if there's one.
     *
     * @param card Card which will see its price be reduced
     * @return The new cost of the card
     */
    public List<Energy> triggerMainFortuneIfPossible(Card card, Player player) {
        List<Energy> copyOfEnergyCost = new ArrayList<>();
        try{
            copyOfEnergyCost = new ArrayList<>(card.getEnergyCost()); //copie pour ne pas modifier le vrai coût de la vraie carte au cours de la méthode
        }
        catch (Exception e){
            int i = 0;
        }
        for (Card c : getInvocation().getCardsOnBoard()) {
            if (c instanceof MainFortuneEffect && card.getEnergyCost().size() > 1) { //si c'est une Main de la Fortune
                Renderer.add("- Main de la Fortune est activée");
                Energy removedEnergy = player.chooseEnergyToReduce(card.getEnergyCost());
                copyOfEnergyCost.remove(removedEnergy);
                Renderer.add(String.format("-- L'énergie %s est retirée du coût de %s", removedEnergy.name(), card.getName()));
                break;
            }
        }
        return copyOfEnergyCost;
    }

    public List<EnumPlayerAction> findPossibleAction(Board board) {
        List<EnumPlayerAction> possibleAction = new ArrayList<>();

        if (canCrystalize && (!energyStock.getEnergyStock().isEmpty())) {
            possibleAction.add(EnumPlayerAction.CRYSTALLISE);
        }
        if (!hand.getCardsInHand().isEmpty()
                && anyCardSummonable(getPlayerByInventory(board))) {
            possibleAction.add(EnumPlayerAction.SUMMON);
        }
        if (bonus.getUsedBonus() < bonus.getMaxBonus()) {
            possibleAction.add(EnumPlayerAction.BONUS);
        }

        for (Card card : invocation.getCardsOnBoard()) {
            if (card.getEffectFrequency() == EffectFrequency.ON_ACTIVATION
                    && card.isActivable()
                    && !card.isActivated()) {
                possibleAction.add(EnumPlayerAction.ACTIVATE_CARD);
                break;
            }
        }
        if (getInvocation().getCardsOnBoard().size() == 1
                && getInvocation().getCardsOnBoard().get(0).getId() == 5
                && getEnergyStock().listOfMultipleEnergy(3).isEmpty()) {
            possibleAction.remove(EnumPlayerAction.ACTIVATE_CARD);
        }
        possibleAction.add(EnumPlayerAction.NOTHING);
        return possibleAction;
    }

    private boolean payEnergyCost(List<Energy> cost) {
        while (!cost.isEmpty()) {
            boolean energyFound = energyStock.removeEnergy(cost.get(0));

            if (energyFound) {
                cost.remove(0);
            }
            int i = 0;
            while (!energyFound) { //on va itérer sur toutes les cartes du board jusqu'à trouver l'énergie (si pas déjà fait)
                if (i >= invocation.getCardsOnBoard().size()) {
                    return false;
                }
                Card currentCard = invocation.getCardsOnBoard().get(i);
                if (currentCard.getEnergyStock().removeEnergy(cost.get(0))) {
                    energyFound = true;
                    cost.remove(0);
                }
                i++;
            }
        }
        return true;
    }

    public int getPenaltyCardPoints() {
        return prestigePoints - getCardsInHand().size() * 5;
    }


    public int getPenaltyBonusPoints() {
        int usedBonus = bonus.getUsedBonus();
        switch (usedBonus) {
            case 1 -> {
                return 5;
            }

            case 2 -> {
                return 12;
            }

            case 3 -> {
                return 20;
            }

            default -> {
                return 0;
            }
        }
    }

    public int getPrestigePointsCard() {
        int sum = 0;
        for (Card card : invocation.getCardsOnBoard()) {
            sum += card.getPrestigePointValue();
        }
        return sum;
    }

    /**
     * Calculate the number of prestige point of the player
     * and put it into the fields prestigePoints
     * @return
     */
    public int calculatePrestigePoint() {
        // 1 cristal = 1 point de prestige en plus
        prestigePoints = prestigePoints + crystals;
        // cartes joué -> nombre de point de prestige indiqué en plus
        prestigePoints = prestigePoints + getPrestigePointsCard();

        // cartes non joué -> 5 point de prestige en moins
        prestigePoints = getPenaltyCardPoints();

        //malus de la piste des bonus (non implémenté pour le moment)
        prestigePoints -= getPenaltyBonusPoints();

        return prestigePoints;
    }

    public int calculatePrestigePoint2() {
        return prestigePoints + crystals + getPrestigePointsCard() - getPenaltyBonusPoints();
    }

    private Player getPlayerByInventory(Board board) {
        for (Map.Entry<Player, Inventory> entry : board.getInventories().entrySet()) {
            if (entry.getValue().equals(this)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public boolean canDraw() {
        return currentDice.getCurrentFace().isTakeCard();
    }

    public int getPrestigePoints() {
        return prestigePoints;
    }

    public void setPrestigePoints(int prestigePoints) {
        this.prestigePoints = prestigePoints;
    }


    public Invocation getInvocation() {
        return invocation;
    }


    //// Function about Energy

    public PlayerEnergyStock getEnergyStock() {
        return energyStock;
    }


    /// function about the crystals
    public int getCrystals() {
        return crystals;
    }

    public void setCrystals(int n) {
        crystals = Math.max(n, 0);
    }

    public void addCrystals(int n) {
        crystals = crystals + n;
    }

    public void addInvocationPoints(int n) {
        invocation.addInvocationPoints(n);
    }

    public int getBonusCrystal() {
        return this.bonusCrystal;
    }

    public void setBonusCrystal(int bonusCrystal) {
        this.bonusCrystal = bonusCrystal;
    }

    public boolean getCanCrystalize() {
        return canCrystalize;
    }

    public void setCanCrystalize(boolean canCristalize) {
        this.canCrystalize = canCristalize;
    }

    //// function about the dice
    public Dice getCurrentDice() {
        return currentDice;
    }

    public void setCurrentDice(Dice currentDice) {
        this.currentDice = currentDice;
    }


    /**
     * Send the card to the discard deck
     *
     * @param board Board
     * @param card  Card to discard
     */
    public void discard(Board board, Card card) {
        board.getDeck().getDiscardPool().add(card);
    }

    /**
     * Send all the cards in the given list to the discard deck
     *
     * @param board board containing the deck
     * @param cards cards to discard
     */
    public void discard(Board board, ArrayList<Card> cards) {
        board.getDeck().getDiscardPool().addAll(cards);
    }

    //// Function about the cards

    /**
     * Adds the given card to the hand
     *
     * @param card card to add to the hand
     */
    public void addCard(Card card) {
        hand.getCardsInHand().add(card);
    }


    public boolean hasInvocationsLeft() {
        return (invocation.getInvocationPoints() > 0
                && invocation.getCurrentInvocations() < invocation.getInvocationPoints());
    }

    public boolean hasEnoughCrystals(Card card) {
        return crystals >= card.getCrystalCost();
    }

    public List<Card> getCardsInHand() {
        return hand.getCardsInHand();
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Bonus getBonus() {
        return bonus;
    }

    /**
     * @return total card size for the current player inventory
     */
    public int getNbCards() {
        return getInvocation().getCardsOnBoard().size() +
                getCardsInHand().size() + getHand().getCardsByYear().length;
    }

    public void setInvocation(Invocation invocation) {
        this.invocation = invocation;
    }

    public void setEnergyStock(PlayerEnergyStock energyStock) {
        this.energyStock = energyStock;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "energyStock=" + energyStock +
                ", bonus=" + bonus +
                ", hand=" + hand +
                ", currentDice=" + currentDice +
                ", canCrystalize=" + canCrystalize +
                ", bonusCrystal=" + bonusCrystal +
                ", crystals=" + crystals +
                ", prestigePoints=" + prestigePoints +
                ", invocation=" + invocation +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        Inventory cloneInventory = (Inventory) super.clone();
        cloneInventory.setEnergyStock((PlayerEnergyStock) energyStock.clone());
        cloneInventory.setBonus((Bonus) bonus.clone());
        if (hand!=null) {
            cloneInventory.setHand((Hand) hand.clone());
        }
        if (currentDice != null) {
            cloneInventory.setCurrentDice((Dice) currentDice.clone());
        }
        cloneInventory.setInvocation((Invocation) invocation.clone());

        return cloneInventory;
    }
}
