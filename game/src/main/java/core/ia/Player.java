package core.ia;

import core.board.FacadeIA;
import core.board.enums.Energy;
import core.cards.Card;
import core.dice.Dice;
import core.ia.inventory.BonusType;
import core.ia.strategy.choose.use_de_de_la_malice.ChooseUseDeDeLaMaliceRandom;
import core.ia.strategy.choose.use_de_de_la_malice.StrategyChooseUseDeDeLaMalice;
import core.ia.strategy.choose.bonus.ChooseBonusRandom;
import core.ia.strategy.choose.bonus.StrategyChooseBonus;
import core.ia.strategy.choose.card_between_multiple_to_get.ChooseCardBetweenMultipleToGetRandom;
import core.ia.strategy.choose.card_between_multiple_to_get.StrategyChooseCardBetweenMultipleToGet;
import core.ia.strategy.choose.card_come_back_in_hand.ChooseCardComeBackInHandRandom;
import core.ia.strategy.choose.card_come_back_in_hand.StrategyChooseCardComeBackInHand;
import core.ia.strategy.choose.card_for_init_hand.ChooseCardForInitHandRandom;
import core.ia.strategy.choose.card_for_init_hand.StrategyChooseCardForInitHand;
import core.ia.strategy.choose.card_naria_la_prophetesse.ChooseCardNariaLaProphetesseRandom;
import core.ia.strategy.choose.card_naria_la_prophetesse.StrategyChooseCardNariaLaProphetesse;
import core.ia.strategy.choose.card_to_delete.ChooseCardToDeleteRandom;
import core.ia.strategy.choose.card_to_delete.StrategyChooseCardToDelete;
import core.ia.strategy.choose.card_to_summon.ChooseCardToSummonRandom;
import core.ia.strategy.choose.card_to_summon.StrategyChooseCardToSummon;
import core.ia.strategy.choose.card_to_summon_for_free.ChooseCardToSummonForFreeRandom;
import core.ia.strategy.choose.card_to_summon_for_free.StrategyChooseCardToSummonForFree;
import core.ia.strategy.choose.cards_to_activate.ChooseCardsToActivateRandom;
import core.ia.strategy.choose.cards_to_activate.StrategyChooseCardsToActivate;
import core.ia.strategy.choose.dice.ChooseDiceRandom;
import core.ia.strategy.choose.dice.StrategyChooseDice;
import core.ia.strategy.choose.energy_to_crystalize.ChooseEnergyToCrystalizeRandom;
import core.ia.strategy.choose.energy_to_crystalize.StrategyChooseEnergyToCrystalize;
import core.ia.strategy.choose.energy_to_get.ChooseEnergyToGetRandom;
import core.ia.strategy.choose.energy_to_get.StrategyChooseEnergyToGet;
import core.ia.strategy.choose.energy_to_reduce.ChooseEnergyToReduceRandom;
import core.ia.strategy.choose.energy_to_reduce.StrategyChooseEnergyToReduce;
import core.ia.strategy.choose.energy_to_throw.ChooseEnergyToThrowRandom;
import core.ia.strategy.choose.energy_to_throw.StrategyChooseEnergyToThrow;
import core.ia.strategy.choose.go_to_the_previous_season.ChooseGoToThePreviousSeasonRandom;
import core.ia.strategy.choose.go_to_the_previous_season.StrategyChooseGoToThePreviousSeason;
import core.ia.strategy.choose.nb_deplacement_season.ChooseNbDeplacementSeasonRandom;
import core.ia.strategy.choose.nb_deplacement_season.StrategyChooseNbDeplacementSeason;
import core.ia.strategy.choose.copy_energy_from_player.ChoosePlayerEnergyToCopyRandom;
import core.ia.strategy.choose.copy_energy_from_player.StrategyChoosePlayerEnergyToCopy;
import core.ia.strategy.choose.player_action.ChoosePlayerActionRandom;
import core.ia.strategy.choose.player_action.StrategyChoosePlayerAction;
import core.ia.strategy.choose.similar_energy_to_delete.ChooseSimilarEnergiesToDeleteRandom;
import core.ia.strategy.choose.similar_energy_to_delete.StrategyChooseSimilarEnergyToDelete;
import core.ia.strategy.choose.go_to_the_next_season.ChooseGoToTheNextSeasonRandom;
import core.ia.strategy.choose.go_to_the_next_season.StrategyChooseGoToTheNextSeason;
import core.ia.strategy.choose.stay_in_the_season.ChooseStayInTheSeasonRandom;
import core.ia.strategy.choose.stay_in_the_season.StrategyChooseStayInTheSeason;
import core.ia.strategy.choose.to_keep_drawn_card.ChooseToKeepDrawnCardRandom;
import core.ia.strategy.choose.to_keep_drawn_card.StrategyChooseToKeepDrawnCard;
import core.ia.strategy.choose.use_bonus_card.ChooseUseBonusCardRandom;
import core.ia.strategy.choose.use_bonus_card.StrategyChooseUseBonusCard;
import core.util.Config;

import java.util.List;
import java.util.Map;

public class Player implements IPlayer, Cloneable{
    protected final String name;
    protected Config config;
    protected TypeAIPlayer typeAIPlayer;
    protected FacadeIA facadeIA;
    protected boolean satisfyingDice = true;
    protected StrategyChooseCardComeBackInHand chooseCardComeBackInHand = new ChooseCardComeBackInHandRandom();
    protected StrategyChooseCardForInitHand chooseCardForInitHand = new ChooseCardForInitHandRandom();
    protected StrategyChooseCardBetweenMultipleToGet chooseCardBetweenMultipleToGet = new ChooseCardBetweenMultipleToGetRandom();
    protected StrategyChooseCardsToActivate chooseCardsToActivate = new ChooseCardsToActivateRandom();
    protected StrategyChooseCardToSummon chooseCardToSummon = new ChooseCardToSummonRandom();
    protected StrategyChooseCardToSummonForFree chooseCardToSummonForFree = new ChooseCardToSummonForFreeRandom();
    protected StrategyChooseToKeepDrawnCard chooseToKeepDrawnCard = new ChooseToKeepDrawnCardRandom();
    protected StrategyChooseBonus chooseBonus = new ChooseBonusRandom();
    protected StrategyChooseUseBonusCard chooseUseBonusCard = new ChooseUseBonusCardRandom();
    protected StrategyChooseDice chooseDice = new ChooseDiceRandom();
    protected StrategyChooseEnergyToCrystalize chooseEnergyToCrystallize = new ChooseEnergyToCrystalizeRandom();
    protected StrategyChooseEnergyToGet chooseEnergyToGet = new ChooseEnergyToGetRandom();
    protected StrategyChooseEnergyToThrow chooseEnergyToThrow = new ChooseEnergyToThrowRandom();
    protected StrategyChoosePlayerEnergyToCopy choosePlayerEnergyToCopy = new ChoosePlayerEnergyToCopyRandom();
    protected StrategyChoosePlayerAction choosePlayerAction = new ChoosePlayerActionRandom();
    protected StrategyChooseSimilarEnergyToDelete chooseMultipleEnergyToDelete = new ChooseSimilarEnergiesToDeleteRandom();
    protected StrategyChooseNbDeplacementSeason chooseNbDeplacementSeason = new ChooseNbDeplacementSeasonRandom();
    protected StrategyChooseEnergyToReduce chooseEnergyToReduce = new ChooseEnergyToReduceRandom();
    protected StrategyChooseCardNariaLaProphetesse chooseCardNariaLaProphetesse = new ChooseCardNariaLaProphetesseRandom();
    protected StrategyChooseCardToDelete chooseCardToDelete = new ChooseCardToDeleteRandom();
    protected StrategyChooseStayInTheSeason chooseStayInTheSeason = new ChooseStayInTheSeasonRandom();
    protected StrategyChooseGoToTheNextSeason chooseGoToTheNextSeason = new ChooseGoToTheNextSeasonRandom();
    protected StrategyChooseGoToThePreviousSeason chooseGoToThePreviousSeason = new ChooseGoToThePreviousSeasonRandom();
    protected StrategyChooseUseDeDeLaMalice chooseUseDeDeLaMalice = new ChooseUseDeDeLaMaliceRandom();

    protected Player(String name, Config config, TypeAIPlayer typeAIPlayer) {
        this.name = name;
        this.config = config;
        this.typeAIPlayer = typeAIPlayer;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (obj.getClass() != this.getClass()) {
//            return false;
//        }
//
////        return ((Player) obj).getName().equals(toString());
//        return true;
        return super.equals(obj);
    }

    ////// Energy
    public Energy chooseEnergyToCrystalize() {
        return chooseEnergyToCrystallize.doChoose(this);
    }

    public Energy chooseEnergyToThrow() {
        return chooseEnergyToThrow.doChoose(this);
    }

    public Energy chooseEnergyToGet() {
        return chooseEnergyToGet.doChoose(this);
    }

    public List<Energy> chooseSimilarEnergiesToDelete(int nb) {
        return chooseMultipleEnergyToDelete.doChoose(this, nb);
    }

    /////// Player Action/Related
    public EnumPlayerAction choosePlayerAction() {
        return choosePlayerAction.doChoose(this);
    }

    public Player choosePlayer() {
        return choosePlayerEnergyToCopy.doChoose(this);
    }

    /////// Dice
    public Dice chooseDice() {
        setSatisfyingDice(true);
        Dice d=chooseDice.doChoose(this);
        return d;
    }

    ////// Card
    public boolean chooseToKeepDrawnCard(Card card) {
        return chooseToKeepDrawnCard.doChoose(this, card);
    }

    public Card chooseCardToSummon() {
        return chooseCardToSummon.doChoose(this);
    }

    public Card chooseCardToSummonForFree() {
        return chooseCardToSummonForFree.doChoose(this);
    }

    public Card chooseCardToDelete(List<Card> cards) {
        return chooseCardToDelete.doChoose(this);
    }

    public Card chooseCardBetweenMultipleToGet(List<Card> cards) {
        return chooseCardBetweenMultipleToGet.doChoose(this, cards);
    }

    public Card chooseCardToActivate() {
        return chooseCardsToActivate.doChoose(this);
    }

    public Card[][] chooseCardForInitHand(List<Card> deck) {
        return chooseCardForInitHand.doChoose(this, deck, config);
    }

    public Card chooseCardComeBackInHand() {
        return chooseCardComeBackInHand.doChoose(this);
    }

    //////// Bonus
    public BonusType chooseBonus() {
        return chooseBonus.doChoose(this);
    }

    public Energy chooseEnergyToReduce(List<Energy> energies) {
        return chooseEnergyToReduce.doChoose(this, energies);
    }

    public boolean chooseUseBonusCard() {
        return chooseUseBonusCard.doChoose(this);
    }

    public boolean chooseUseDeDeLaMalice() {
        return chooseUseDeDeLaMalice.doChoose(this);
    }

    public int chooseNbDeplacementSeason(int min, int max) {
        return chooseNbDeplacementSeason.doChoose(this, min, max);
    }

    public Map<Player, Card> chooseCardNariaLaProfetesse(List<Card> cards) {
        return chooseCardNariaLaProphetesse.doChoose(this, cards);
    }

    public boolean chooseStayInTheSeason() {
        return chooseStayInTheSeason.doChoose(this);
    }

    public boolean chooseGoToTheNextSeason() {
        return chooseGoToTheNextSeason.doChoose(this);
    }

    public boolean chooseGoToThePreviousSeason() {
        return chooseGoToThePreviousSeason.doChoose(this);
    }

    public void setChooseBonus(StrategyChooseBonus chooseBonus) {
        this.chooseBonus = chooseBonus;
    }

    public void setChooseCardBetweenMultipleToGet(StrategyChooseCardBetweenMultipleToGet chooseCardBetweenMultipleToGet) {
        this.chooseCardBetweenMultipleToGet = chooseCardBetweenMultipleToGet;
    }

    public void setChooseCardForInitHand(StrategyChooseCardForInitHand chooseCardForInitHand) {
        this.chooseCardForInitHand = chooseCardForInitHand;
    }

    public void setChooseCardsToActivate(StrategyChooseCardsToActivate chooseCardsToActivate) {
        this.chooseCardsToActivate = chooseCardsToActivate;
    }

    public void setChooseCardComeBackInHand(StrategyChooseCardComeBackInHand chooseCardThanksToCardEffect) {
        this.chooseCardComeBackInHand = chooseCardThanksToCardEffect;
    }

    public void setChooseCardToSummon(StrategyChooseCardToSummon chooseCardToSummon) {
        this.chooseCardToSummon = chooseCardToSummon;
    }

    public void setChooseCardToSummonForFree(StrategyChooseCardToSummonForFree chooseCardToSummonForFree) {
        this.chooseCardToSummonForFree = chooseCardToSummonForFree;
    }

    public void setChooseCardToDelete(StrategyChooseCardToDelete chooseCardToDelete) {
        this.chooseCardToDelete = chooseCardToDelete;
    }

    public void setChooseDice(StrategyChooseDice chooseDice) {
        this.chooseDice = chooseDice;
    }

    public void setChooseEnergyToCrystallize(StrategyChooseEnergyToCrystalize chooseEnergyToCrystallize) {
        this.chooseEnergyToCrystallize = chooseEnergyToCrystallize;
    }

    public void setChooseEnergyToGet(StrategyChooseEnergyToGet chooseEnergyToGet) {
        this.chooseEnergyToGet = chooseEnergyToGet;
    }

    public void setChooseEnergyToThrow(StrategyChooseEnergyToThrow chooseEnergyToThrow) {
        this.chooseEnergyToThrow = chooseEnergyToThrow;
    }

    public void setChooseSimilarEnergyToDelete(StrategyChooseSimilarEnergyToDelete chooseMultipleEnergyToDelete) {
        this.chooseMultipleEnergyToDelete = chooseMultipleEnergyToDelete;
    }

    public void setChoosePlayerEnergyToCopy(StrategyChoosePlayerEnergyToCopy choosePlayerEnergyToCopyRandom) {
        this.choosePlayerEnergyToCopy = choosePlayerEnergyToCopyRandom;
    }

    public void setChoosePlayerAction(StrategyChoosePlayerAction choosePlayerAction) {
        this.choosePlayerAction = choosePlayerAction;
    }

    public void setChooseToKeepDrawnCard(StrategyChooseToKeepDrawnCard chooseToKeepDrawnCard) {
        this.chooseToKeepDrawnCard = chooseToKeepDrawnCard;
    }

    public void setChooseUseBonusCard(StrategyChooseUseBonusCard chooseUseBonusCard) {
        this.chooseUseBonusCard = chooseUseBonusCard;
    }

    public void setChooseUseDeDeLaMalice(StrategyChooseUseDeDeLaMalice chooseUseDeDeLaMalice) {
        this.chooseUseDeDeLaMalice = chooseUseDeDeLaMalice;
    }

    public void setChooseNbDeplacementSeason(StrategyChooseNbDeplacementSeason chooseNbDeplacementSeason) {
        this.chooseNbDeplacementSeason = chooseNbDeplacementSeason;
    }

    public void setChooseCardNariaLaProphetesse(StrategyChooseCardNariaLaProphetesse chooseCardNariaLaProphetesse) {
        this.chooseCardNariaLaProphetesse = chooseCardNariaLaProphetesse;
    }

    public void setChooseStayInTheSeason(StrategyChooseStayInTheSeason chooseStayInTheSeason) {
        this.chooseStayInTheSeason = chooseStayInTheSeason;
    }

    public void setChooseGoToTheNextSeason(StrategyChooseGoToTheNextSeason chooseGoToTheNextSeason) {
        this.chooseGoToTheNextSeason = chooseGoToTheNextSeason;
    }

    public void setChooseGoToThePreviousSeason(StrategyChooseGoToThePreviousSeason chooseGoToThePreviousSeason) {
        this.chooseGoToThePreviousSeason = chooseGoToThePreviousSeason;
    }

    public TypeAIPlayer getTypeAIPlayer() {
        return typeAIPlayer;
    }

    public String getName() {
        return name + " (" + typeAIPlayer + ")";
    }

    public FacadeIA getFacadeIA() {
        return facadeIA;
    }

    public void setFacadeIA(FacadeIA facadeIA) {
        this.facadeIA = facadeIA;
    }

    public void setChooseEnergyToReduce(StrategyChooseEnergyToReduce chooseEnergyToReduce) {
        this.chooseEnergyToReduce = chooseEnergyToReduce;
    }

    public boolean isSatisfyingDice() {
        return satisfyingDice;
    }

    public void setSatisfyingDice(boolean satisfyingDice) {
        this.satisfyingDice = satisfyingDice;
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        Player clonePlayer= (Player) super.clone();
        clonePlayer.setFacadeIA(new FacadeIA(clonePlayer, config));

        return clonePlayer;
    }

}


