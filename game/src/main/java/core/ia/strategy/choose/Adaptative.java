package core.ia.strategy.choose;

import core.cards.Card;
import core.ia.Player;
import core.ia.TypeAIPlayer;
import core.ia.strategy.choose.bonus.*;
import core.ia.strategy.choose.card_between_multiple_to_get.*;
import core.ia.strategy.choose.card_come_back_in_hand.*;
import core.ia.strategy.choose.card_for_init_hand.*;
import core.ia.strategy.choose.card_naria_la_prophetesse.*;
import core.ia.strategy.choose.card_to_delete.*;
import core.ia.strategy.choose.card_to_summon.*;
import core.ia.strategy.choose.card_to_summon_for_free.*;
import core.ia.strategy.choose.cards_to_activate.*;
import core.ia.strategy.choose.copy_energy_from_player.*;
import core.ia.strategy.choose.dice.*;
import core.ia.strategy.choose.energy_to_crystalize.ChooseEnergyToCrystalizeAdaptative;
import core.ia.strategy.choose.energy_to_crystalize.ChooseEnergyToCrystalizeRandom;
import core.ia.strategy.choose.energy_to_crystalize.ChooseEnergyToCrystallizeCrystallizePref;
import core.ia.strategy.choose.energy_to_get.*;
import core.ia.strategy.choose.energy_to_reduce.ChooseEnergyToReduceAdaptative;
import core.ia.strategy.choose.energy_to_reduce.ChooseEnergyToReducePrefInvoc;
import core.ia.strategy.choose.energy_to_reduce.ChooseEnergyToReduceRandom;
import core.ia.strategy.choose.energy_to_throw.*;
import core.ia.strategy.choose.go_to_the_next_season.*;
import core.ia.strategy.choose.go_to_the_previous_season.*;
import core.ia.strategy.choose.nb_deplacement_season.ChooseNbDeplacementSeasonAdaptative;
import core.ia.strategy.choose.nb_deplacement_season.ChooseNbDeplacementSeasonRandom;
import core.ia.strategy.choose.nb_deplacement_season.ChooseNbDeplacementSeasonTime;
import core.ia.strategy.choose.player_action.*;
import core.ia.strategy.choose.similar_energy_to_delete.*;
import core.ia.strategy.choose.stay_in_the_season.*;
import core.ia.strategy.choose.to_keep_drawn_card.*;
import core.ia.strategy.choose.use_bonus_card.ChooseUseBonusCardAdaptative;
import core.ia.strategy.choose.use_bonus_card.ChooseUseBonusCardFalse;
import core.ia.strategy.choose.use_de_de_la_malice.ChooseUseDeDeLaMaliceSmart;
import core.ia.strategy.choose.use_de_de_la_malice.ChooseUseDeDeLaMaliveAdaptative;
import core.util.CardUtil;
import core.util.Config;
import core.util.Renderer;

import java.util.*;

public class Adaptative extends Player {
    String focusedPlayersName;

    public Adaptative(String name, Config config, TypeAIPlayer typeAIPlayer) {
        super(name, config, typeAIPlayer);
    }

    public void analyzeOpponentStrategy() {
        List<Card> bestBoard = getPotentialWinnersBoard(facadeIA.getOtherPlayersCrystalsAndBoard());

        HashMap<TypeAIPlayer, Float> strategyScore = new HashMap<>();

        float crystalProportion = 0;
        float cardPointProportion = 0;
        float invocProportion = 0;
        float comboProportion = 0;
        float malusProportion = 0;
        float timeProportion = 0;
        float activableProportion = 0;
        float permanentProportion = 0;
        for (Card card : bestBoard) {
            // Check pour PREF_CRYSTALLIZE
            if (CardUtil.getCrystalCards().contains(card.getClass())) {
                crystalProportion += 1;
            }

            // Check pour PREF_CARD_POINTS
            int AVERAGE_PRESTIGE_POINT_VALUE = 7;
            if (card.getPrestigePointValue() > AVERAGE_PRESTIGE_POINT_VALUE) {
                cardPointProportion += 1;
            }

            // Check pour PREF_INVOCATION
            if (CardUtil.getCardsForMoreInvocation().contains(card.getClass())) {
                invocProportion += 1;
            }

            // Check pour COMBOS
            if (CardUtil.combos.containsKey(card.getClass())) {
                //comboProportion += 1; // je vais peut-être enlever cette ligne
                for (Card card1 : bestBoard) {
                    if (CardUtil.combos.get(card.getClass()).contains(card1.getClass())) {
                        comboProportion += 1;
                    }
                }
            }

            // Check for MALUS
            if (CardUtil.getMalusCards().contains(card.getClass())) {
                malusProportion += 1;
            }

            // Check for time strategies (not really efficient since they only care about 1 card)
            if (CardUtil.getCardsPlayedWithTime().contains(card.getClass())) {
                timeProportion += 1;
            }

            // Check for ACTIVATE
            if (card.isActivable()) {
                activableProportion += 1;
            }

            //Check for PREF_PERMANENT
            if (card.hasPermanentEffect()) {
                permanentProportion += 1;
            }
        }
        strategyScore.put(TypeAIPlayer.PREF_CRYSTALLIZE, crystalProportion / bestBoard.size());
        strategyScore.put(TypeAIPlayer.PREF_CARD_POINTS, cardPointProportion / bestBoard.size());
        strategyScore.put(TypeAIPlayer.PREF_INVOCATION, invocProportion / bestBoard.size());
        strategyScore.put(TypeAIPlayer.COMBOS, comboProportion / bestBoard.size());
        strategyScore.put(TypeAIPlayer.MALUS, malusProportion / bestBoard.size());
        strategyScore.put(TypeAIPlayer.TIME_RANDOM, timeProportion / bestBoard.size());
        strategyScore.put(TypeAIPlayer.TIME_FIRST, timeProportion / bestBoard.size());
        strategyScore.put(TypeAIPlayer.TIME_CRYSTALLIZE, timeProportion / bestBoard.size());
        strategyScore.put(TypeAIPlayer.TIME_INVOCATION, timeProportion / bestBoard.size());
        strategyScore.put(TypeAIPlayer.ACTIVATE, activableProportion / bestBoard.size());
        strategyScore.put(TypeAIPlayer.PREF_PERMANENT, permanentProportion / bestBoard.size());

        Float maxProbability = Collections.max(strategyScore.values());
        List<TypeAIPlayer> mostProbableTypes = new ArrayList<>();

        //J'ajoute tous les types qui ont la plus grande probabilité pour pouvoir faire d'autres comparaisons entre eux plus tard
        strategyScore.forEach((t, v) -> {
            if (Objects.equals(v, maxProbability)) {
                mostProbableTypes.add(t);
            }
        });

        switch (decideBetweenSeveralPossibilities(mostProbableTypes)) {
            case PREF_CRYSTALLIZE -> {
                setChooseBonus(new ChooseBonusPrefCrystallize());
                setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetPrefCrystallize());
                setChooseCardComeBackInHand(new ChooseCardComeBackInHandPrefCrystallize());
                setChooseCardForInitHand(new ChooseCardForInitHandPrefCrystallize());
                setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetessePrefCrystallize());
                setChooseCardToDelete(new ChooseCardToDeletePrefCrystallize());
                setChooseCardToSummon(new ChooseCardToSummonPrefCrystallize());
                setChooseCardToSummonForFree(new ChooseCardToSummonForFreePrefCrystallize());
                setChooseCardsToActivate(new ChooseCardsToActivatePrefCrystallize());
                setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyRandom());
                setChooseDice(new ChooseDicePrefCrystallize());
                setChooseEnergyToCrystallize(new ChooseEnergyToCrystallizeCrystallizePref());
                setChooseEnergyToGet(new ChooseEnergyToGetCrystallizePref());
                setChooseEnergyToReduce(new ChooseEnergyToReduceRandom());
                setChooseEnergyToThrow(new ChooseEnergyToThrowCrystallizePref());
                setChooseGoToTheNextSeason(new ChooseGoToTheNextSeasonPrefCrystallize());
                setChooseGoToThePreviousSeason(new ChooseGoToThePreviousSeasonPrefCrystallize());
                setChooseNbDeplacementSeason(new ChooseNbDeplacementSeasonRandom());
                setChoosePlayerAction(new ChoosePlayerActionCristallizePref());
                setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeleteRandom());
                setChooseStayInTheSeason(new ChooseStayInTheSeasonPrefCrystallize());
                setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardPrefCrystallize());
                setChooseUseBonusCard(new ChooseUseBonusCardFalse());
                setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
            case PREF_CARD_POINTS -> {
                setChooseBonus(new ChooseBonusPrefCardPoints());
                setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetPrefCardPoints());
                setChooseCardComeBackInHand(new ChooseCardComeBackInHandCardPoints());
                setChooseCardForInitHand(new ChooseCardForInitHandPrefCardPoints());
                setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetessePrefCardPoints());
                setChooseCardToDelete(new ChooseCardToDeletePrefCardPoints());
                setChooseCardToSummon(new ChooseCardToSummonPrefCardPoints());
                setChooseCardToSummonForFree(new ChooseCardToSummonForFreePrefCardPoints());
                setChooseCardsToActivate(new ChooseCardsToActivateRandom());
                setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyPrefCardPoints());
                setChooseDice(new ChooseDicePrefCardPoints());
                setChooseEnergyToCrystallize(new ChooseEnergyToCrystalizeRandom());
                setChooseEnergyToGet(new ChooseEnergyToGetPrefCardPoint());
                setChooseEnergyToReduce(new ChooseEnergyToReducePrefInvoc());
                setChooseEnergyToThrow(new ChooseEnergyToThrowPrefCardPoint());
                setChooseGoToTheNextSeason(new ChooseGoToTheNextSeasonRandom());
                setChooseGoToThePreviousSeason(new ChooseGoToThePreviousSeasonRandom());
                setChooseNbDeplacementSeason(new ChooseNbDeplacementSeasonRandom());
                setChoosePlayerAction(new ChoosePlayerActionPrefInvoc());
                setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeletePrefInvoc());
                setChooseStayInTheSeason(new ChooseStayInTheSeasonRandom());
                setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardPrefCardPoints());
                setChooseUseBonusCard(new ChooseUseBonusCardFalse());
                setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
            case PREF_INVOCATION -> {
                setChooseBonus(new ChooseBonusPrefInvoc());
                setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetPrefInvoc());
                setChooseCardComeBackInHand(new ChooseCardComeBackInHandPrefInvoc());
                setChooseCardForInitHand(new ChooseCardForInitHandPrefInvocation());
                setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetessePrefInvoc());
                setChooseCardToDelete(new ChooseCardToDeleteRandom());
                setChooseCardToSummon(new ChooseCardToSummonPrefInvoc());
                setChooseCardToSummonForFree(new ChooseCardToSummonForFreePrefInvoc());
                setChooseCardsToActivate(new ChooseCardsToActivatePrefInvoc());
                setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyPrefInvoc());
                setChooseDice(new ChooseDicePrefInvoc());
                setChooseEnergyToCrystallize(new ChooseEnergyToCrystalizeRandom());
                setChooseEnergyToGet(new ChooseEnergyToGetPrefInvoc());
                setChooseEnergyToReduce(new ChooseEnergyToReducePrefInvoc());
                setChooseEnergyToThrow(new ChooseEnergyToThrowPrefInvoc());
                setChooseGoToTheNextSeason(new ChooseGoToTheNextSeasonRandom());
                setChooseGoToThePreviousSeason(new ChooseGoToThePreviousSeasonRandom());
                setChooseNbDeplacementSeason(new ChooseNbDeplacementSeasonRandom());
                setChoosePlayerAction(new ChoosePlayerActionPrefInvoc());
                setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeletePrefInvoc());
                setChooseStayInTheSeason(new ChooseStayInTheSeasonPrefInvoc());
                setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardPrefInvoc());
                setChooseUseBonusCard(new ChooseUseBonusCardFalse());
                setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
            case COMBOS -> {
                setChooseBonus(new ChooseBonusRandom());
                setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetCombos());
                setChooseCardComeBackInHand(new ChooseCardComeBackInHandCombos());
                setChooseCardForInitHand(new ChooseCardForInitHandCombos());
                setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetesseCombos());
                setChooseCardToDelete(new ChooseCardToDeleteCombos());
                setChooseCardToSummon(new ChooseCardToSummonCombos());
                setChooseCardToSummonForFree(new ChooseCardToSummonForFreeCombos());
                setChooseCardsToActivate(new ChooseCardsToActivateCombos());
                setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyCombos());
                setChooseDice(new ChooseDiceCombos());
                setChooseEnergyToCrystallize(new ChooseEnergyToCrystalizeRandom());
                setChooseEnergyToGet(new ChooseEnergyToGetCombo());
                setChooseEnergyToReduce(new ChooseEnergyToReducePrefInvoc());
                setChooseEnergyToThrow(new ChooseEnergyToThrowCombo());
                setChooseGoToTheNextSeason(new ChooseGoToTheNextSeasonRandom());
                setChooseGoToThePreviousSeason(new ChooseGoToThePreviousSeasonRandom());
                setChooseNbDeplacementSeason(new ChooseNbDeplacementSeasonRandom());
                setChoosePlayerAction(new ChoosePlayerActionRandom());
                setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeletePrefInvoc());
                setChooseStayInTheSeason(new ChooseStayInTheSeasonRandom());
                setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardRandom());
                setChooseUseBonusCard(new ChooseUseBonusCardFalse());
                setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
            case MALUS -> {
                setChooseBonus(new ChooseBonusMalus());
                setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetMalus());
                setChooseCardComeBackInHand(new ChooseCardComeBackInHandMalus());
                setChooseCardForInitHand(new ChooseCardForInitHandPrefMalus());
                setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetesseRandom());
                setChooseCardToDelete(new ChooseCardToDeleteMalus());
                setChooseCardToSummon(new ChooseCardToSummonMalus());
                setChooseCardToSummonForFree(new ChooseCardToSummonForFreeMalus());
                setChooseCardsToActivate(new ChooseCardsToActivateMalus());
                setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyRandom());
                setChooseDice(new ChooseDiceRandom());
                setChooseEnergyToCrystallize(new ChooseEnergyToCrystalizeRandom());
                setChooseEnergyToGet(new ChooseEnergyToGetMalus());
                setChooseEnergyToReduce(new ChooseEnergyToReduceRandom());
                setChooseEnergyToThrow(new ChooseEnergyToThrowRandom());
                setChooseGoToTheNextSeason(new ChooseGoToTheNextSeasonRandom());
                setChooseGoToThePreviousSeason(new ChooseGoToThePreviousSeasonRandom());
                setChooseNbDeplacementSeason(new ChooseNbDeplacementSeasonRandom());
                setChoosePlayerAction(new ChoosePlayerActionRandom());
                setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeleteRandom());
                setChooseStayInTheSeason(new ChooseStayInTheSeasonRandom());
                setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardMalus());
                setChooseUseBonusCard(new ChooseUseBonusCardFalse());
                setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
            case TIME_RANDOM -> {
                setChooseBonus(new ChooseBonusRandom());
                setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetTime());
                setChooseCardComeBackInHand(new ChooseCardComeBackInHandTime());
                setChooseCardForInitHand(new ChooseCardForInitHandTime());
                setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetesseTime());
                setChooseCardToDelete(new ChooseCardToDeleteTime());
                setChooseCardToSummon(new ChooseCardToSummonTime());
                setChooseCardToSummonForFree(new ChooseCardToSummonForFreeTime());
                setChooseCardsToActivate(new ChooseCardsToActivateTime());
                setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyTime());
                setChooseDice(new ChooseDiceTime());
                setChooseEnergyToCrystallize(new ChooseEnergyToCrystalizeRandom());
                setChooseEnergyToGet(new ChooseEnergyToGetTime());
                setChooseEnergyToReduce(new ChooseEnergyToReducePrefInvoc());
                setChooseEnergyToThrow(new ChooseEnergyToThrowTime());
                setChooseGoToTheNextSeason(new ChooseGoToTheNextSeasonRandom());
                setChooseGoToThePreviousSeason(new ChooseGoToThePreviousSeasonRandom());
                setChooseNbDeplacementSeason(new ChooseNbDeplacementSeasonTime());
                setChoosePlayerAction(new ChoosePlayerActionRandom());
                setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeleteTime());
                setChooseStayInTheSeason(new ChooseStayInTheSeasonRandom());
                setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardTime());
                setChooseUseBonusCard(new ChooseUseBonusCardFalse());
                setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
            case TIME_FIRST -> {
                setChooseBonus(new ChooseBonusRandom());
                setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetTime());
                setChooseCardComeBackInHand(new ChooseCardComeBackInHandTime());
                setChooseCardForInitHand(new ChooseCardForInitHandTime());
                setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetesseTime());
                setChooseCardToDelete(new ChooseCardToDeleteTime());
                setChooseCardToSummon(new ChooseCardToSummonTime());
                setChooseCardToSummonForFree(new ChooseCardToSummonForFreeTime());
                setChooseCardsToActivate(new ChooseCardsToActivateTime());
                setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyTime());
                setChooseDice(new ChooseDiceTime());
                setChooseEnergyToCrystallize(new ChooseEnergyToCrystalizeRandom());
                setChooseEnergyToGet(new ChooseEnergyToGetTime());
                setChooseEnergyToReduce(new ChooseEnergyToReducePrefInvoc());
                setChooseEnergyToThrow(new ChooseEnergyToThrowTime());
                setChooseGoToTheNextSeason(new ChooseGoToTheNextSeasonFirst());
                setChooseGoToThePreviousSeason(new ChooseGoToThePreviousSeasonFirst());
                setChooseNbDeplacementSeason(new ChooseNbDeplacementSeasonTime());
                setChoosePlayerAction(new ChoosePlayerActionRandom());
                setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeleteTime());
                setChooseStayInTheSeason(new ChooseStayInTheSeasonFirst());
                setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardTime());
                setChooseUseBonusCard(new ChooseUseBonusCardFalse());
                setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
            case TIME_CRYSTALLIZE -> {
                setChooseBonus(new ChooseBonusRandom());
                setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetTime());
                setChooseCardComeBackInHand(new ChooseCardComeBackInHandTime());
                setChooseCardForInitHand(new ChooseCardForInitHandTime());
                setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetesseTime());
                setChooseCardToDelete(new ChooseCardToDeleteTime());
                setChooseCardToSummon(new ChooseCardToSummonTime());
                setChooseCardToSummonForFree(new ChooseCardToSummonForFreeTime());
                setChooseCardsToActivate(new ChooseCardsToActivateTime());
                setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyTime());
                setChooseDice(new ChooseDiceTime());
                setChooseEnergyToCrystallize(new ChooseEnergyToCrystalizeRandom());
                setChooseEnergyToGet(new ChooseEnergyToGetTime());
                setChooseEnergyToReduce(new ChooseEnergyToReducePrefInvoc());
                setChooseEnergyToThrow(new ChooseEnergyToThrowTime());
                setChooseGoToTheNextSeason(new ChooseGoToTheNextSeasonPrefCrystallize());
                setChooseGoToThePreviousSeason(new ChooseGoToThePreviousSeasonPrefCrystallize());
                setChooseNbDeplacementSeason(new ChooseNbDeplacementSeasonTime());
                setChoosePlayerAction(new ChoosePlayerActionRandom());
                setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeleteTime());
                setChooseStayInTheSeason(new ChooseStayInTheSeasonPrefCrystallize());
                setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardTime());
                setChooseUseBonusCard(new ChooseUseBonusCardFalse());
                setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
            case TIME_INVOCATION -> {
                setChooseBonus(new ChooseBonusRandom());
                setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetTime());
                setChooseCardComeBackInHand(new ChooseCardComeBackInHandTime());
                setChooseCardForInitHand(new ChooseCardForInitHandTime());
                setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetesseTime());
                setChooseCardToDelete(new ChooseCardToDeleteTime());
                setChooseCardToSummon(new ChooseCardToSummonTime());
                setChooseCardToSummonForFree(new ChooseCardToSummonForFreeTime());
                setChooseCardsToActivate(new ChooseCardsToActivateTime());
                setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyTime());
                setChooseDice(new ChooseDiceTime());
                setChooseEnergyToCrystallize(new ChooseEnergyToCrystalizeRandom());
                setChooseEnergyToGet(new ChooseEnergyToGetTime());
                setChooseEnergyToReduce(new ChooseEnergyToReducePrefInvoc());
                setChooseEnergyToThrow(new ChooseEnergyToThrowTime());
                setChooseGoToTheNextSeason(new ChooseGoToTheNextSeasonPrefInvoc());
                setChooseGoToThePreviousSeason(new ChooseGoToThePreviousSeasonPrefInvoc());
                setChooseNbDeplacementSeason(new ChooseNbDeplacementSeasonTime());
                setChoosePlayerAction(new ChoosePlayerActionRandom());
                setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeleteTime());
                setChooseStayInTheSeason(new ChooseStayInTheSeasonPrefInvoc());
                setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardTime());
                setChooseUseBonusCard(new ChooseUseBonusCardFalse());
                setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
            case ACTIVATE -> {
                setChooseBonus(new ChooseBonusPrefActivate());
                setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetPrefActivate());
                setChooseCardComeBackInHand(new ChooseCardComeBackInHandPrefActivate());
                setChooseCardForInitHand(new ChooseCardForInitHandPrefActivate());
                setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetessePrefActivate());
                setChooseCardToDelete(new ChooseCardToDeletePrefActivate());
                setChooseCardToSummon(new ChooseCardToSummonPrefActivate());
                setChooseCardToSummonForFree(new ChooseCardToSummonForFreePrefActivate());
                setChooseCardsToActivate(new ChooseCardsToActivateRandom());
                setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyPrefActivate());
                setChooseDice(new ChooseDicePrefActivate());
                setChooseEnergyToCrystallize(new ChooseEnergyToCrystalizeRandom());
                setChooseEnergyToGet(new ChooseEnergyToGetPrefActivate());
                setChooseEnergyToReduce(new ChooseEnergyToReducePrefInvoc());
                setChooseEnergyToThrow(new ChooseEnergyToThrowPrefInvoc());
                setChooseGoToTheNextSeason(new ChooseGoToTheNextSeasonRandom());
                setChooseGoToThePreviousSeason(new ChooseGoToThePreviousSeasonRandom());
                setChooseNbDeplacementSeason(new ChooseNbDeplacementSeasonRandom());
                setChoosePlayerAction(new ChoosePlayerActionPrefActivate());
                setChooseSimilarEnergyToDelete(new ChooseSimilarEnergyToDeletePrefActivate());
                setChooseStayInTheSeason(new ChooseStayInTheSeasonRandom());
                setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardPrefActivate());
                setChooseUseBonusCard(new ChooseUseBonusCardFalse());
                setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
            case PREF_PERMANENT -> {
                setChooseBonus(new ChooseBonusPrefPermanent());
                setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetPrefPermanent());
                setChooseCardComeBackInHand(new ChooseCardComeBackInHandPrefPermanent());
                setChooseCardForInitHand(new ChooseCardForInitHandPrefPermanent());
                setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetessePrefPermanent());
                setChooseCardToDelete(new ChooseCardToDeletePrefPermanent());
                setChooseCardToSummon(new ChooseCardToSummonPrefPermanent());
                setChooseCardToSummonForFree(new ChooseCardToSummonForFreePrefPermanent());
                setChooseCardsToActivate(new ChooseCardsToActivateRandom());
                setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyPrefPermanent());
                setChooseDice(new ChooseDiceRandom());
                setChooseEnergyToCrystallize(new ChooseEnergyToCrystalizeRandom());
                setChooseEnergyToGet(new ChooseEnergyToGetPrefPermanent());
                setChooseEnergyToReduce(new ChooseEnergyToReduceRandom());
                setChooseEnergyToThrow(new ChooseEnergyToThrowRandom());
                setChooseGoToTheNextSeason(new ChooseGoToTheNextSeasonRandom());
                setChooseGoToThePreviousSeason(new ChooseGoToThePreviousSeasonRandom());
                setChooseNbDeplacementSeason(new ChooseNbDeplacementSeasonRandom());
                setChoosePlayerAction(new ChoosePlayerActionRandom());
                setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeleteRandom());
                setChooseStayInTheSeason(new ChooseStayInTheSeasonRandom());
                setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardPrefPermanent());
                setChooseUseBonusCard(new ChooseUseBonusCardFalse());
                setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
        }
    }

    public TypeAIPlayer decideBetweenSeveralPossibilities(List<TypeAIPlayer> possibleTypes) {
//        if (possibleTypes.size() == 1) {
//            return possibleTypes.get(0);
//        }
//        Will do additional checks
        Renderer.add(String.format("Joueur %s pense que %s utilise %s.", name, focusedPlayersName, possibleTypes.get(0).name()));
        return possibleTypes.get(0);
    }

    public List<Card> getPotentialWinnersBoard(HashMap<String, Integer> otherPlayers) {
        List<Card> bestPlayersBoard = new ArrayList<>();
        int maxPotential = 0;

        for (Map.Entry<String, Integer> nameAndCrystals : otherPlayers.entrySet()) {
            List<Card> currentBoard;
            String name = nameAndCrystals.getKey();
            int potentialFinalCrystals = nameAndCrystals.getValue();
            currentBoard = facadeIA.getPlayersBoard(name);
            potentialFinalCrystals += calculateCardsIncome(currentBoard, name);
            if (potentialFinalCrystals >= maxPotential) {
                maxPotential = potentialFinalCrystals;
                bestPlayersBoard = facadeIA.getPlayersBoard(name);
                focusedPlayersName = name;
            }
        }
        Renderer.add(String.format("Joueur %s se concentre sur %s qui semble pouvoir gagner avec %d cristaux.", getName(), focusedPlayersName, maxPotential));
        return bestPlayersBoard;
    }

    private int calculateCardsIncome(List<Card> board, String playerName) {
        int potentialTotalIncome = 0;
        for (Card currentCard : board) {
            potentialTotalIncome += currentCard.getPrestigePointValue();

            switch(currentCard.getId()) {
                case 5 -> { // Balance d'Ishtar
                    int possibleUses = facadeIA.getListOfMultipleEnergy(3).size();
                    potentialTotalIncome += possibleUses * 9;
                }
                case 6 -> // Bâton du Printemps
                        potentialTotalIncome += 3 * facadeIA.playersInvocationsLeft(playerName);
                case 8 -> // Bourse d'Io
                        potentialTotalIncome += facadeIA.getPlayersEnergyStock(playerName).size();
                case 11 -> { // Figrim l'Avaricieux
                    int numberSeasonsLeft = calculateLeftNbOfSeasons();
                    int playersWhoWillPay = facadeIA.getAmountOfPlayers() - 1; //-1 because we remove the owner of the card
                    potentialTotalIncome += numberSeasonsLeft * playersWhoWillPay * 2; //*2 because each player will lose a crystal, and the player will gain those crystals
                }
                case 13 -> { // Coffret merveilleux
                    if (facadeIA.getPlayersEnergyStock(playerName).size() >= 4) {
                        //on va dire 1 manche/saison, plus simple
                        potentialTotalIncome += calculateLeftNbOfSeasons() * 3;
                    }
                }
                case 16 -> // Kairn le Destructeur
                        potentialTotalIncome += 4 * facadeIA.getPlayersEnergyStock(playerName).size();
                case 19 -> { // Heaume de Ragfield
                    List<Integer> everyBoardSize = new ArrayList<>();
                    for (List<Card> currentBoard : facadeIA.getEveryOtherPlayersBoard()) {
                        everyBoardSize.add(currentBoard.size());
                    }
                    if (Collections.max(everyBoardSize) == board.size()) {
                        potentialTotalIncome += 20;
                    }
                }
                case 26 -> // Potion de vie
                    potentialTotalIncome += 4 * facadeIA.getPlayersEnergyStock(playerName).size() - currentCard.getPrestigePointValue(); // elle est sacrifiée si utilisée
            }
        }
        return potentialTotalIncome;
    }

    private int calculateLeftNbOfSeasons() {
        int yearsLeft = facadeIA.getYear().getNbMaxYears() - facadeIA.getNbYear();
        int numberSeasonsLeft = yearsLeft * 4;
        return numberSeasonsLeft + 3 - facadeIA.getSeason().getSeasonNumber(); //3 because we don't count the end of the last season
    }

    public void setStrategiesToAdaptative() {
        setChooseBonus(new ChooseBonusAdaptative(this));
        setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetAdaptative(this));
        setChooseCardComeBackInHand(new ChooseCardComeBackInHandAdaptative(this));
        setChooseCardForInitHand(new ChooseCardForInitHandAdaptative(this));
        setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetesseAdaptative(this));
        setChooseCardToDelete(new ChooseCardToDeleteAdaptative(this));
        setChooseCardToSummon(new ChooseCardToSummonAdaptative(this));
        setChooseCardToSummonForFree(new ChooseCardToSummonForFreeAdaptative(this));
        setChooseCardsToActivate(new ChooseCardsToActivateAdaptative(this));
        setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyAdaptative(this));
        setChooseDice(new ChooseDiceAdaptative(this));
        setChooseEnergyToCrystallize(new ChooseEnergyToCrystalizeAdaptative(this));
        setChooseEnergyToGet(new ChooseEnergyToGetAdaptative(this));
        setChooseEnergyToReduce(new ChooseEnergyToReduceAdaptative(this));
        setChooseEnergyToThrow(new ChooseEnergyToThrowAdaptative(this));
        setChooseGoToTheNextSeason(new ChooseGoToTheNextSeasonAdaptative(this));
        setChooseGoToThePreviousSeason(new ChooseGoToThePreviousSeasonAdaptative(this));
        setChooseNbDeplacementSeason(new ChooseNbDeplacementSeasonAdaptative(this));
        setChoosePlayerAction(new ChoosePlayerActionAdaptative(this));
        setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeleteAdaptive(this));
        setChooseStayInTheSeason(new ChooseStayInTheSeasonAdaptative(this));
        setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardAdaptative(this));
        setChooseUseBonusCard(new ChooseUseBonusCardAdaptative(this));
        setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliveAdaptative(this));
    }
}
