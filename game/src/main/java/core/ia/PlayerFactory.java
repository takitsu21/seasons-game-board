package core.ia;

import core.exception.PlayerException;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.Context;
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
import core.ia.strategy.choose.energy_to_crystalize.ChooseEnergyToCrystalizeFirst;
import core.ia.strategy.choose.energy_to_crystalize.ChooseEnergyToCrystallizeCrystallizePref;
import core.ia.strategy.choose.energy_to_get.*;
import core.ia.strategy.choose.energy_to_reduce.ChooseEnergyToReduceFirst;
import core.ia.strategy.choose.energy_to_reduce.ChooseEnergyToReducePrefInvoc;
import core.ia.strategy.choose.energy_to_throw.*;
import core.ia.strategy.choose.go_to_the_next_season.ChooseGoToTheNextSeasonFirst;
import core.ia.strategy.choose.go_to_the_next_season.ChooseGoToTheNextSeasonPrefCrystallize;
import core.ia.strategy.choose.go_to_the_next_season.ChooseGoToTheNextSeasonPrefInvoc;
import core.ia.strategy.choose.go_to_the_previous_season.ChooseGoToThePreviousSeasonFirst;
import core.ia.strategy.choose.go_to_the_previous_season.ChooseGoToThePreviousSeasonPrefCrystallize;
import core.ia.strategy.choose.go_to_the_previous_season.ChooseGoToThePreviousSeasonPrefInvoc;
import core.ia.strategy.choose.nb_deplacement_season.ChooseNbDeplacementSeasonFirst;
import core.ia.strategy.choose.nb_deplacement_season.ChooseNbDeplacementSeasonTime;
import core.ia.strategy.choose.player_action.ChoosePlayerActionCristallizePref;
import core.ia.strategy.choose.player_action.ChoosePlayerActionFirst;
import core.ia.strategy.choose.player_action.ChoosePlayerActionPrefActivate;
import core.ia.strategy.choose.player_action.ChoosePlayerActionPrefInvoc;
import core.ia.strategy.choose.similar_energy_to_delete.ChooseSimilarEnergiesToDeleteFirst;
import core.ia.strategy.choose.similar_energy_to_delete.ChooseSimilarEnergiesToDeletePrefInvoc;
import core.ia.strategy.choose.similar_energy_to_delete.ChooseSimilarEnergiesToDeleteTime;
import core.ia.strategy.choose.similar_energy_to_delete.ChooseSimilarEnergyToDeletePrefActivate;
import core.ia.strategy.choose.stay_in_the_season.ChooseStayInTheSeasonFirst;
import core.ia.strategy.choose.stay_in_the_season.ChooseStayInTheSeasonPrefCrystallize;
import core.ia.strategy.choose.stay_in_the_season.ChooseStayInTheSeasonPrefInvoc;
import core.ia.strategy.choose.stay_in_the_season.ChooseStayInTheSeasonRandom;
import core.ia.strategy.choose.to_keep_drawn_card.*;
import core.ia.strategy.choose.use_bonus_card.ChooseUseBonusCardFalse;
import core.ia.strategy.choose.use_bonus_card.ChooseUseBonusCardFirst;
import core.ia.strategy.choose.use_de_de_la_malice.ChooseUseDeDeLaMaliceFirst;
import core.ia.strategy.choose.use_de_de_la_malice.ChooseUseDeDeLaMaliceSmart;
import core.util.Config;
import core.util.ConfigMonteCarlo;

public class PlayerFactory {
    private Config config;
    private ConfigMonteCarlo configMonteCarlo;

    public PlayerFactory(Config config) {
        this.config = config;
    }

    public PlayerFactory(Config config, ConfigMonteCarlo configMonteCarlo) {
        this.config = config;
        this.configMonteCarlo = configMonteCarlo;
    }

    public Player getPlayer(TypeAIPlayer playerType, String name) {
        Player player = new Player(name, config, playerType);
        switch (playerType) {
            case CHOOSE_FIRST -> {
                player.setChooseBonus(new ChooseBonusFirst());
                player.setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetFirst());
                player.setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyFirst());
                player.setChooseCardForInitHand(new ChooseCardForInitHandFirst());
                player.setChooseDice(new ChooseDiceFirst());
                player.setChoosePlayerAction(new ChoosePlayerActionFirst());
                player.setChooseCardsToActivate(new ChooseCardsToActivateFirst());
                player.setChooseCardToSummon(new ChooseCardToSummonFirst());
                player.setChooseCardToSummonForFree(new ChooseCardToSummonForFreeFirst());
                player.setChooseCardsToActivate(new ChooseCardsToActivateFirst());
                player.setChooseEnergyToThrow(new ChooseEnergyToThrowFirst());
                player.setChooseCardComeBackInHand(new ChooseCardComeBackInHandFirst());
                player.setChooseEnergyToCrystallize(new ChooseEnergyToCrystalizeFirst());
                player.setChooseEnergyToGet(new ChooseEnergyToGetFirst());
                player.setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeleteFirst());
                player.setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardFirst());
                player.setChooseUseBonusCard(new ChooseUseBonusCardFirst());
                player.setChooseNbDeplacementSeason(new ChooseNbDeplacementSeasonFirst());
                player.setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetesseFirst());
                player.setChooseEnergyToReduce(new ChooseEnergyToReduceFirst());
                player.setChooseCardToDelete(new ChooseCardToDeleteFirst());
                player.setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceFirst());
                return player;
            }
            case RANDOM -> {
                return player;
            }
            case PREF_CRYSTALLIZE -> {
                player.setChooseBonus(new ChooseBonusPrefCrystallize());
                player.setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetPrefCrystallize());
                player.setChooseCardComeBackInHand(new ChooseCardComeBackInHandPrefCrystallize());
                player.setChooseCardForInitHand(new ChooseCardForInitHandPrefCrystallize());
                player.setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetessePrefCrystallize());
                player.setChooseCardToDelete(new ChooseCardToDeletePrefCrystallize());
                player.setChooseCardToSummon(new ChooseCardToSummonPrefCrystallize());
                player.setChooseCardToSummonForFree(new ChooseCardToSummonForFreePrefCrystallize());
                player.setChooseCardsToActivate(new ChooseCardsToActivatePrefCrystallize());
                player.setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardPrefCrystallize());
                player.setChooseDice(new ChooseDicePrefCrystallize());
                player.setChooseEnergyToCrystallize(new ChooseEnergyToCrystallizeCrystallizePref());
                player.setChooseEnergyToGet(new ChooseEnergyToGetCrystallizePref());
                player.setChooseEnergyToThrow(new ChooseEnergyToThrowCrystallizePref());
                player.setChooseGoToTheNextSeason(new ChooseGoToTheNextSeasonPrefCrystallize());
                player.setChooseGoToThePreviousSeason(new ChooseGoToThePreviousSeasonPrefCrystallize());
                player.setChoosePlayerAction(new ChoosePlayerActionCristallizePref());
                player.setChooseStayInTheSeason(new ChooseStayInTheSeasonPrefCrystallize());
                player.setChooseUseBonusCard(new ChooseUseBonusCardFalse());
                player.setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
            case PREF_CARD_POINTS -> {
                player.setChooseCardForInitHand(new ChooseCardForInitHandPrefCardPoints());
                player.setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetPrefCardPoints());
                player.setChooseCardToSummon(new ChooseCardToSummonPrefCardPoints());
                player.setChooseCardToSummonForFree(new ChooseCardToSummonForFreePrefCardPoints());
                player.setChooseCardToDelete(new ChooseCardToDeletePrefCardPoints());
                player.setChooseEnergyToGet(new ChooseEnergyToGetPrefCardPoint());
                player.setChooseEnergyToThrow(new ChooseEnergyToThrowPrefCardPoint());
                player.setChooseBonus(new ChooseBonusPrefCardPoints());
                player.setChooseCardComeBackInHand(new ChooseCardComeBackInHandCardPoints());
                player.setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetessePrefCardPoints());
                player.setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyPrefCardPoints());
                player.setChooseDice(new ChooseDicePrefCardPoints());
                player.setChoosePlayerAction(new ChoosePlayerActionPrefInvoc());
                player.setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeletePrefInvoc());
                player.setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardPrefCardPoints());
                player.setChooseEnergyToReduce(new ChooseEnergyToReducePrefInvoc());
                player.setChooseUseBonusCard(new ChooseUseBonusCardFalse());
                player.setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
            case PREF_INVOCATION -> {
                player.setChooseCardForInitHand(new ChooseCardForInitHandPrefInvocation());
                player.setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetPrefInvoc());
                player.setChooseDice(new ChooseDicePrefInvoc());
                player.setChooseCardToSummon(new ChooseCardToSummonPrefInvoc());
                player.setChooseCardToSummonForFree(new ChooseCardToSummonForFreePrefInvoc());
                player.setChooseCardComeBackInHand(new ChooseCardComeBackInHandPrefInvoc());
                player.setChooseEnergyToGet(new ChooseEnergyToGetPrefInvoc());
                player.setChooseEnergyToReduce(new ChooseEnergyToReducePrefInvoc());
                player.setChooseEnergyToThrow(new ChooseEnergyToThrowPrefInvoc());
                player.setChoosePlayerAction(new ChoosePlayerActionPrefInvoc());
                player.setChooseBonus(new ChooseBonusPrefInvoc());
                player.setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetessePrefInvoc());
                player.setChooseCardsToActivate(new ChooseCardsToActivatePrefInvoc());
                player.setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeletePrefInvoc());
                player.setChooseStayInTheSeason(new ChooseStayInTheSeasonPrefInvoc());
                player.setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardPrefInvoc());
                player.setChooseUseBonusCard(new ChooseUseBonusCardFalse());
                player.setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyPrefInvoc());
                player.setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
            case COMBOS -> {
                player.setChooseCardForInitHand(new ChooseCardForInitHandCombos());
                player.setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetCombos());
                player.setChooseCardToSummon(new ChooseCardToSummonCombos());
                player.setChooseCardToSummonForFree(new ChooseCardToSummonForFreeCombos());
                player.setChooseCardToDelete(new ChooseCardToDeleteCombos());
                player.setChooseCardComeBackInHand(new ChooseCardComeBackInHandCombos());
                player.setChooseCardsToActivate(new ChooseCardsToActivateCombos());
                player.setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeletePrefInvoc());
                player.setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyCombos());
                player.setChooseDice(new ChooseDiceCombos());
                player.setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetesseCombos());
                player.setChooseEnergyToThrow(new ChooseEnergyToThrowCombo());
                player.setChooseEnergyToGet(new ChooseEnergyToGetCombo());
                player.setChooseEnergyToReduce(new ChooseEnergyToReducePrefInvoc());
                player.setChooseUseBonusCard(new ChooseUseBonusCardFalse());
                player.setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
            case MALUS -> {
                player.setChooseBonus(new ChooseBonusMalus());
                player.setChooseCardForInitHand(new ChooseCardForInitHandPrefMalus());
                player.setChooseCardComeBackInHand(new ChooseCardComeBackInHandMalus());
                player.setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetMalus());
                player.setChooseCardToDelete(new ChooseCardToDeleteMalus());
                player.setChooseCardToSummon(new ChooseCardToSummonMalus());
                player.setChooseCardToSummonForFree(new ChooseCardToSummonForFreeMalus());
                player.setChooseCardsToActivate(new ChooseCardsToActivateMalus());
                player.setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardMalus());
                player.setChooseEnergyToGet(new ChooseEnergyToGetMalus());
                player.setChooseUseBonusCard(new ChooseUseBonusCardFalse());
                player.setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
            case TIME_RANDOM -> {
                player = new Player(name, config, TypeAIPlayer.TIME_RANDOM);
                player.setChooseDice(new ChooseDiceTime());
                player.setChooseNbDeplacementSeason(new ChooseNbDeplacementSeasonTime());
                player.setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetTime());
                player.setChooseCardComeBackInHand(new ChooseCardComeBackInHandTime());
                player.setChooseCardForInitHand(new ChooseCardForInitHandTime());
                player.setChooseCardToDelete(new ChooseCardToDeleteTime());
                player.setChooseCardToSummon(new ChooseCardToSummonTime());
                player.setChooseCardToSummonForFree(new ChooseCardToSummonForFreeTime());
                player.setChooseCardsToActivate(new ChooseCardsToActivateTime());
                player.setChooseEnergyToGet(new ChooseEnergyToGetTime());
                player.setChooseEnergyToReduce(new ChooseEnergyToReducePrefInvoc());
                player.setChooseEnergyToThrow(new ChooseEnergyToThrowTime());
                player.setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeleteTime());
                player.setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardTime());
                player.setChooseUseBonusCard(new ChooseUseBonusCardFalse());
                player.setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyTime());
                player.setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetesseTime());
                player.setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
            case TIME_FIRST -> {
                player = new Player(name, config, TypeAIPlayer.TIME_FIRST);
                player.setChooseDice(new ChooseDiceTime());
                player.setChooseNbDeplacementSeason(new ChooseNbDeplacementSeasonTime());
                player.setChooseStayInTheSeason(new ChooseStayInTheSeasonFirst());
                player.setChooseGoToTheNextSeason(new ChooseGoToTheNextSeasonFirst());
                player.setChooseGoToThePreviousSeason(new ChooseGoToThePreviousSeasonFirst());
                player.setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetTime());
                player.setChooseCardComeBackInHand(new ChooseCardComeBackInHandTime());
                player.setChooseCardForInitHand(new ChooseCardForInitHandTime());
                player.setChooseCardToDelete(new ChooseCardToDeleteTime());
                player.setChooseCardToSummon(new ChooseCardToSummonTime());
                player.setChooseCardToSummonForFree(new ChooseCardToSummonForFreeTime());
                player.setChooseCardsToActivate(new ChooseCardsToActivateTime());
                player.setChooseEnergyToGet(new ChooseEnergyToGetTime());
                player.setChooseEnergyToReduce(new ChooseEnergyToReducePrefInvoc());
                player.setChooseEnergyToThrow(new ChooseEnergyToThrowTime());
                player.setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeleteTime());
                player.setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardTime());
                player.setChooseUseBonusCard(new ChooseUseBonusCardFalse());
                player.setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyTime());
                player.setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetesseTime());
                player.setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
            case TIME_CRYSTALLIZE -> {
                player = new Player(name, config, TypeAIPlayer.TIME_CRYSTALLIZE);
                player.setChooseDice(new ChooseDiceTime());
                player.setChooseNbDeplacementSeason(new ChooseNbDeplacementSeasonTime());
                player.setChooseStayInTheSeason(new ChooseStayInTheSeasonPrefCrystallize());
                player.setChooseGoToTheNextSeason(new ChooseGoToTheNextSeasonPrefCrystallize());
                player.setChooseGoToThePreviousSeason(new ChooseGoToThePreviousSeasonPrefCrystallize());
                player.setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetTime());
                player.setChooseCardComeBackInHand(new ChooseCardComeBackInHandTime());
                player.setChooseCardForInitHand(new ChooseCardForInitHandTime());
                player.setChooseCardToDelete(new ChooseCardToDeleteTime());
                player.setChooseCardToSummon(new ChooseCardToSummonTime());
                player.setChooseCardToSummonForFree(new ChooseCardToSummonForFreeTime());
                player.setChooseCardsToActivate(new ChooseCardsToActivateTime());
                player.setChooseEnergyToGet(new ChooseEnergyToGetTime());
                player.setChooseEnergyToReduce(new ChooseEnergyToReducePrefInvoc());
                player.setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeleteTime());
                player.setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardTime());
                player.setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyTime());
                player.setChooseUseBonusCard(new ChooseUseBonusCardFalse());
                player.setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetesseTime());
                player.setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
            case TIME_INVOCATION -> {
                player = new Player(name, config, TypeAIPlayer.TIME_INVOCATION);
                player.setChooseDice(new ChooseDiceTime());
                player.setChooseNbDeplacementSeason(new ChooseNbDeplacementSeasonTime());
                player.setChooseStayInTheSeason(new ChooseStayInTheSeasonPrefInvoc());
                player.setChooseGoToTheNextSeason(new ChooseGoToTheNextSeasonPrefInvoc());
                player.setChooseGoToThePreviousSeason(new ChooseGoToThePreviousSeasonPrefInvoc());
                player.setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetTime());
                player.setChooseCardComeBackInHand(new ChooseCardComeBackInHandTime());
                player.setChooseCardForInitHand(new ChooseCardForInitHandTime());
                player.setChooseCardToDelete(new ChooseCardToDeleteTime());
                player.setChooseCardToSummon(new ChooseCardToSummonTime());
                player.setChooseCardToSummonForFree(new ChooseCardToSummonForFreeTime());
                player.setChooseCardsToActivate(new ChooseCardsToActivateTime());
                player.setChooseEnergyToGet(new ChooseEnergyToGetTime());
                player.setChooseEnergyToReduce(new ChooseEnergyToReducePrefInvoc());
                player.setChooseEnergyToThrow(new ChooseEnergyToThrowTime());
                player.setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeleteTime());
                player.setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardTime());
                player.setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyTime());
                player.setChooseUseBonusCard(new ChooseUseBonusCardFalse());
                player.setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetesseTime());
                player.setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
            case COMPOSE1 -> {
                // Si le stock d'énergie est plein il choisi le bonus cristallisant sinon au hasard
                player.setChooseBonus(new ChooseBonusPrefCrystallize(Context.fullOfEnergy, new ChooseBonusRandom()));

                // Le joueur cherche si il y a une carte a summon interessante, si oui la summon, si non choisi une carte random
                player.setChooseCardToSummon(new ChooseCardToSummonCombos(new ChooseCardToSummonRandom()));

                // Le joueur choisi la cristallisation si il peut, sinon par défault il choisi random
                player.setChoosePlayerAction(new ChoosePlayerActionCristallizePref());

                // Le joueur active si c'est interessant pour un combos
                // Si non, si tous les autre joueurs on plus de crystals que le joueur il active un malus
                // Si c'est pas possible il choisi random
                player.setChooseCardsToActivate(new ChooseCardsToActivateCombos(
                        new ChooseCardsToActivateMalus(Context.allThePlayerHaveMoreCrystals,
                                new ChooseCardsToActivateRandom())));
            }
            case ACTIVATE -> {
                player.setChooseCardForInitHand(new ChooseCardForInitHandPrefActivate());
                player.setChooseCardToDelete(new ChooseCardToDeletePrefActivate());
                player.setChooseCardToSummonForFree(new ChooseCardToSummonForFreePrefActivate());
                player.setChooseCardToSummon(new ChooseCardToSummonPrefActivate());
                player.setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetPrefActivate());
                player.setChooseCardComeBackInHand(new ChooseCardComeBackInHandPrefActivate());
                player.setChoosePlayerAction(new ChoosePlayerActionPrefActivate());
                player.setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardPrefActivate());
                player.setChooseSimilarEnergyToDelete(new ChooseSimilarEnergyToDeletePrefActivate());
                player.setChooseEnergyToGet(new ChooseEnergyToGetPrefActivate());
                player.setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyPrefActivate());
                player.setChooseBonus(new ChooseBonusPrefActivate());
                player.setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetessePrefActivate());
                player.setChooseEnergyToThrow(new ChooseEnergyToThrowPrefInvoc());
                player.setChooseDice(new ChooseDicePrefActivate());
                player.setChooseEnergyToReduce(new ChooseEnergyToReducePrefInvoc());
                player.setChooseUseBonusCard(new ChooseUseBonusCardFalse());
                player.setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
            case PREF_PERMANENT -> {
                player.setChooseBonus(new ChooseBonusPrefPermanent());
                player.setChooseCardBetweenMultipleToGet(new ChooseCardBetweenMultipleToGetPrefPermanent());
                player.setChooseCardComeBackInHand(new ChooseCardComeBackInHandPrefPermanent());
                player.setChooseCardForInitHand(new ChooseCardForInitHandPrefPermanent());
                player.setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetessePrefPermanent());
                player.setChooseCardToDelete(new ChooseCardToDeletePrefPermanent());
                player.setChooseCardToSummon(new ChooseCardToSummonPrefPermanent());
                player.setChooseCardToSummonForFree(new ChooseCardToSummonForFreePrefPermanent());
                player.setChoosePlayerEnergyToCopy(new ChoosePlayerEnergyToCopyPrefPermanent());
                player.setChooseEnergyToGet(new ChooseEnergyToGetPrefPermanent());
                player.setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardPrefPermanent());
                player.setChooseUseBonusCard(new ChooseUseBonusCardFalse());
                player.setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
            case COMPOSE_MARG -> {
                player.setChooseBonus(new ChooseBonusPrefInvoc(
                        Context.needInvocationPoint,
                        new ChooseBonusPrefCrystallize(
                                Context.fullOfEnergy,
                                new ChooseNoBonus())));
                player.setChooseCardBetweenMultipleToGet(
                        new ChooseCardBetweenMultipleToGetPrefCardPoints(
                                Context.isFirstYear,
                                new ChooseCardBetweenMultipleToGetCombos(
                                        Context.isFirstYearOrSecondYear,
                                        new ChooseCardBetweenMultipleToGetPrefInvoc())));

                player.setChooseCardComeBackInHand(
                        new ChooseCardComeBackInHandPrefOnPlay(
                                new ChooseCardComeBackInHandPrefInvoc()));

                player.setChooseCardForInitHand(new ChooseCardForInitHandCombos());

                player.setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetessePrefInvoc());

                player.setChooseCardToDelete(
                        //new ChooseCardToDeleteCombos(
                        new ChooseCardToDeletePrefPermanent(
                                new ChooseCardToDeletePrefCardPoints(
                                        Context.bigCardPointOnBoard,
                                        new ChooseCardToDeletePrefActivate())));
                player.setChooseCardToSummon(
                        new ChooseCardToSummonPrefPermanent(
                                Context.isFirstYear,
                                new ChooseCardToSummonTime(
                                        Context.isFirstYear,
                                        new ChooseCardToSummonMalus(
                                                Context.isFirstYear,
                                                //new ChooseCardToSummonPrefCrystallize(
                                                //        Context.isSecondYear,
                                                new ChooseCardToSummonPrefCardPoints(
                                                        //Context.isLastYear,
                                                        //new ChooseCardToSummonCombos(
                                                        //    new ChooseCardToSummonPrefCardPoints())
                                                )))));

                player.setChooseCardToSummonForFree(
                        new ChooseCardToSummonForFreePrefPermanent(
                                Context.isFirstYear,
                                new ChooseCardToSummonForFreeTime(
                                        Context.isFirstYear,
                                        new ChooseCardToSummonForFreeMalus(
                                                Context.isFirstYear,
                                                //new ChooseCardToSummonForFreePrefCrystallize(
                                                //        Context.isSecondYear,
                                                new ChooseCardToSummonForFreePrefCardPoints(
                                                        //Context.isLastYear,
                                                        //new ChooseCardToSummonForFreeCombos(
                                                        //        new ChooseCardToSummonForFreePrefCardPoints())))
                                                )))));

                player.setChooseCardsToActivate(
                        new ChooseCardsToActivatePrefInvoc(
                                new ChooseCardsToActivateTime(
                                        Context.isLastYear,
                                        new ChooseCardsToActivateMalus(
                                                Context.isMiddleGame,
                                                new ChooseCardsToActivateCombos()))));

                player.setChoosePlayerEnergyToCopy(
                        new ChoosePlayerEnergyToCopyPrefCardPoints(
                                new ChoosePlayerEnergyToCopyCombos()));

                player.setChooseDice(
                        new ChooseDicePrefInvoc(Context.needInvoc1y2y,
                                new ChooseDicePrefCrystallize(Context.isLastYear,
                                        new ChooseDicePrefCardPoints(Context.everyTime,
                                                new ChooseDicePrefCardPoints()
                                        ))));

                player.setChooseEnergyToCrystallize(new ChooseEnergyToCrystallizeCrystallizePref());

                player.setChooseEnergyToGet(
                        //new ChooseEnergyToGetCombo(
                        new ChooseEnergyToGetPrefCardPoint());

                player.setChooseEnergyToReduce(new ChooseEnergyToReducePrefInvoc());

                player.setChooseEnergyToThrow(
                        //new ChooseEnergyToThrowCombo(
                        new ChooseEnergyToThrowPrefCardPoint());

                player.setChooseGoToTheNextSeason(
                        new ChooseGoToTheNextSeasonPrefInvoc());

                player.setChooseGoToThePreviousSeason(new ChooseGoToThePreviousSeasonPrefInvoc());

                player.setChooseNbDeplacementSeason(new ChooseNbDeplacementSeasonTime());

                player.setChoosePlayerAction(
                        new ChoosePlayerActionCristallizePref(
                                Context.isLastYearAndLastSeason,
                                new ChoosePlayerActionPrefInvoc()));

                player.setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeletePrefInvoc());

                player.setChooseStayInTheSeason(
                        new ChooseStayInTheSeasonPrefInvoc());

                player.setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardPrefInvoc());

                player.setChooseUseBonusCard(new ChooseUseBonusCardFalse());

                player.setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());

            }
            case DYL_COMPOSE -> {
                player.setChooseEnergyToCrystallize(
                        new ChooseEnergyToCrystallizeCrystallizePref(
                                Context.lastYearAndEmptyHand,
                                null));
                player.setChooseCardToSummonForFree(
                        new ChooseCardToSummonForFreePrefCardPoints(
                                Context.hasMinimumCardPoints,
                                new ChooseCardToSummonForFreePrefPermanent(
                                        Context.isMiddleGame,
                                        new ChooseCardToSummonForFreePrefActivate(
                                                new ChooseCardToSummonForFreeMalus(
                                                        new ChooseCardToSummonForFreeCombos(
                                                                new ChooseCardToSummonForFreeTime(
                                                                        Context.isLastYear,
                                                                        new ChooseCardToSummonForFreeRandom()
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )

                );
                player.setChooseCardToSummon(
                        new ChooseCardToSummonPrefCardPoints(
                                new ChooseCardToSummonPrefInvoc(
                                        new ChooseCardToSummonPrefPermanent(
                                                Context.isMiddleGame,
                                                new ChooseCardToSummonPrefActivate(
                                                        new ChooseCardToSummonMalus(
                                                                new ChooseCardToSummonCombos(
                                                                        new ChooseCardToSummonTime(

                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                );
                player.setChooseDice(
                        new ChooseDicePrefInvoc(Context.needInvoc1y2y,
                                new ChooseDicePrefCrystallize(Context.isLastYear,
                                        new ChooseDicePrefCardPoints(

                                        )
                                )
                        )
                );
                player.setChooseCardForInitHand(new ChooseCardForInitHandCombos(
                        new ChooseCardForInitHandPrefInvocation()
                ));
                player.setChoosePlayerAction(
                        new ChoosePlayerActionPrefInvoc(
                                new ChoosePlayerActionPrefActivate(

                                )
                        )
                );
                player.setChooseCardBetweenMultipleToGet(
                        new ChooseCardBetweenMultipleToGetCombos(
                                new ChooseCardBetweenMultipleToGetPrefCardPoints(
                                        new ChooseCardBetweenMultipleToGetPrefInvoc(
                                                new ChooseCardBetweenMultipleToGetPrefActivate()
                                        )
                                )
                        )
                );
                player.setChooseCardComeBackInHand(
                        new ChooseCardComeBackInHandTime(
                                Context.isLastYear,
                                new ChooseCardComeBackInHandMalus(
                                        new ChooseCardComeBackInHandCardPoints(

                                        )
                                )
                        )
                );
                player.setChooseCardNariaLaProphetesse(
                        new ChooseCardNariaLaProphetessePrefInvoc(
                                Context.isLastYear,
                                new ChooseCardNariaLaProphetesseCombos(
                                        new ChooseCardNariaLaProphetessePrefPermanent(
                                                Context.isFirstYear,
                                                new ChooseCardNariaLaProphetessePrefActivate(
                                                        Context.isMiddleGame,
                                                        new ChooseCardNariaLaProphetesseTime()
                                                )
                                        )
                                )
                        )
                );
                player.setChooseCardToDelete(
                        new ChooseCardToDeletePrefPermanent(
                                new ChooseCardToDeletePrefActivate(
                                        new ChooseCardToDeletePrefCardPoints(
                                                new ChooseCardToDeleteTime(
                                                        new ChooseCardToDeleteMalus(
                                                        )
                                                )
                                        )
                                )
                        )
                );
                player.setChoosePlayerEnergyToCopy(
                        new ChoosePlayerEnergyToCopyPrefPermanent(
                                Context.isFirstYear,
                                new ChoosePlayerEnergyToCopyPrefInvoc(
                                        new ChoosePlayerEnergyToCopyPrefCardPoints(
                                                new ChoosePlayerEnergyToCopyPrefActivate(
                                                )
                                        )
                                )
                        ));
                player.setChooseCardsToActivate(
                        new ChooseCardsToActivateTime(
                                Context.isLastYear,
                                new ChooseCardsToActivatePrefInvoc(
                                        new ChooseCardsToActivateCombos()
                                )
                        )
                );
                player.setChooseEnergyToReduce(
                        new ChooseEnergyToReducePrefInvoc()
                );
                player.setChooseEnergyToThrow(
                        new ChooseEnergyToThrowPrefInvoc(
                                new ChooseEnergyToThrowPrefCardPoint(
                                        new ChooseEnergyToThrowCombo(
                                                new ChooseEnergyToThrowCrystallizePref(
                                                        Context.isLastYear,
                                                        new ChooseEnergyToThrowRandom()
                                                )
                                        )
                                )
                        )
                );
                player.setChooseGoToTheNextSeason(
                        new ChooseGoToTheNextSeasonPrefInvoc()
                );
                player.setChooseGoToThePreviousSeason(
                        new ChooseGoToThePreviousSeasonPrefInvoc()
                );
                player.setChooseNbDeplacementSeason(
                        new ChooseNbDeplacementSeasonTime()
                );
                player.setChooseSimilarEnergyToDelete(
                        new ChooseSimilarEnergiesToDeletePrefInvoc(
                                new ChooseSimilarEnergyToDeletePrefActivate(
                                        new ChooseSimilarEnergiesToDeleteTime()
                                )
                        )
                );
                player.setChooseStayInTheSeason(
                        new ChooseStayInTheSeasonPrefInvoc(
                                Context.notEmptyHand,
                                new ChooseStayInTheSeasonPrefCrystallize(
                                        Context.isLastYear,
                                        new ChooseStayInTheSeasonRandom()
                                ))
                );
                player.setChooseToKeepDrawnCard(
                        new ChooseToKeepDrawnCardPrefPermanent(
                                new ChooseToKeepDrawnCardPrefActivate(
                                        new ChooseToKeepDrawnCardMalus(
                                                new ChooseToKeepDrawnCardCombo(
                                                        new ChooseToKeepDrawnCardTime()
                                                )
                                        )
                                )
                        )
                );
                player.setChooseUseDeDeLaMalice(
                        new ChooseUseDeDeLaMaliceSmart()
                );
                player.setChooseBonus(
                        new ChooseBonusPrefInvoc(
                                new ChooseBonusPrefActivate(
                                )
                        )
                );
            }
            case COMPOSE_RAPH -> {
                player.setChooseCardForInitHand(new ChooseCardForInitHandPrefInvocation());
                player.setChooseBonus(new ChooseBonusPrefInvoc(Context.needInvocationPoint,
                        new ChooseBonusPrefActivate(Context.needNewEnergy,
                                new ChooseBonusPrefCrystallize(Context.allThePlayerHaveMoreCrystals,
                                        new ChooseNoBonus()))));
                player.setChooseCardComeBackInHand(new ChooseCardComeBackInHandCardPoints(Context.fullOfEnergy,
                        new ChooseCardComeBackInHandCardPoints(Context.allThePlayerHaveMoreCrystals,
                                new ChooseCardComeBackInHandPrefInvoc(Context.cannotSummonAnything,
                                        new ChooseCardComeBackInHandPrefInvoc(Context.isLastYear,
                                                new ChooseCardComeBackInHandRandom())))));
                player.setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetessePrefCardPoints(Context.everyTime,
                        new ChooseCardNariaLaProphetesseRandom()));
                player.setChooseCardToDelete(new ChooseCardToDeleteMalus(
                        new ChooseCardToDeletePrefCardPoints(
                                new ChooseCardToDeleteCombos(
                                        new ChooseCardToDeleteRandom()))));
                player.setChooseCardToSummon(new ChooseCardToSummonPrefCardPoints(Context.needCrystalsEndGame,
                        new ChooseCardToSummonMalus(Context.isFirstYear,
                                new ChooseCardToSummonMalus(Context.allThePlayerHaveMoreCrystals,
                                        new ChooseCardToSummonPrefPermanent(Context.isMiddleGame,
                                                new ChooseCardToSummonPrefActivate(
                                                        new ChooseCardToSummonPrefInvoc(
                                                                new ChooseCardToSummonRandom())))))));
                player.setChooseCardToSummonForFree(new ChooseCardToSummonForFreePrefCardPoints(Context.needCrystalsEndGame,
                        new ChooseCardToSummonForFreeMalus(Context.isFirstYear,
                                new ChooseCardToSummonForFreeMalus(Context.allThePlayerHaveMoreCrystals,
                                        new ChooseCardToSummonForFreePrefPermanent(Context.isMiddleGame,
                                                new ChooseCardToSummonForFreePrefActivate(
                                                        new ChooseCardToSummonForFreePrefInvoc(
                                                                new ChooseCardToSummonForFreeRandom())))))));
                player.setChooseCardsToActivate(new ChooseCardsToActivatePrefInvoc(Context.shouldSummon,
                        new ChooseCardsToActivateMalus(Context.needToUseKairn,
                                new ChooseCardsToActivatePrefCrystallize(Context.shouldCrystallize,
                                        new ChooseCardsToActivatePrefInvoc(
                                                new ChooseCardsToActivateCombos(
                                                        new ChooseCardsToActivateRandom()))))));

            }
            case MONTECARLO_COMPOSE -> {
                player = new MonteCarlo(name, config, configMonteCarlo, playerType);
                player.setChooseBonus(new ChooseBonusPrefInvoc(
                        Context.needInvocationPoint,
                        new ChooseBonusPrefCrystallize(
                                Context.fullOfEnergy,
                                new ChooseNoBonus())));
                player.setChooseCardBetweenMultipleToGet(
                        new ChooseCardBetweenMultipleToGetPrefCardPoints(
                                Context.isFirstYear,
                                new ChooseCardBetweenMultipleToGetCombos(
                                        Context.isFirstYearOrSecondYear,
                                        new ChooseCardBetweenMultipleToGetPrefInvoc())));

                player.setChooseCardComeBackInHand(
                        new ChooseCardComeBackInHandPrefOnPlay(
                                new ChooseCardComeBackInHandPrefInvoc()));

//                player.setChooseCardForInitHand(new ChooseCardForInitHandCombos());

                player.setChooseCardNariaLaProphetesse(new ChooseCardNariaLaProphetessePrefInvoc());

                player.setChooseCardToDelete(
                        //new ChooseCardToDeleteCombos(
                        new ChooseCardToDeletePrefPermanent(
                                new ChooseCardToDeletePrefCardPoints(
                                        Context.bigCardPointOnBoard,
                                        new ChooseCardToDeletePrefActivate())));
                player.setChooseCardToSummon(
                        new ChooseCardToSummonPrefPermanent(
                                Context.isFirstYear,
                                new ChooseCardToSummonTime(
                                        Context.isFirstYear,
                                        new ChooseCardToSummonMalus(
                                                Context.isFirstYear,
                                                //new ChooseCardToSummonPrefCrystallize(
                                                //        Context.isSecondYear,
                                                new ChooseCardToSummonPrefCardPoints(
                                                        //Context.isLastYear,
                                                        //new ChooseCardToSummonCombos(
                                                        //    new ChooseCardToSummonPrefCardPoints())
                                                )))));

                player.setChooseCardToSummonForFree(
                        new ChooseCardToSummonForFreePrefPermanent(
                                Context.isFirstYear,
                                new ChooseCardToSummonForFreeTime(
                                        Context.isFirstYear,
                                        new ChooseCardToSummonForFreeMalus(
                                                Context.isFirstYear,
                                                //new ChooseCardToSummonForFreePrefCrystallize(
                                                //        Context.isSecondYear,
                                                new ChooseCardToSummonForFreePrefCardPoints(
                                                        //Context.isLastYear,
                                                        //new ChooseCardToSummonForFreeCombos(
                                                        //        new ChooseCardToSummonForFreePrefCardPoints())))
                                                )))));

                player.setChooseCardsToActivate(
                        new ChooseCardsToActivatePrefInvoc(
                                new ChooseCardsToActivateTime(
                                        Context.isLastYear,
                                        new ChooseCardsToActivateMalus(
                                                Context.isMiddleGame,
                                                new ChooseCardsToActivateCombos()))));

                player.setChoosePlayerEnergyToCopy(
                        new ChoosePlayerEnergyToCopyPrefCardPoints(
                                new ChoosePlayerEnergyToCopyCombos()));


//                player.setChooseDice(
//                        new ChooseDicePrefInvoc(Context.needInvoc1y2y,
//                                new ChooseDicePrefCrystallize(Context.isLastYear,
//                                        new ChooseDicePrefCardPoints(Context.everyTime,
//                                                new ChooseDicePrefCardPoints()
//                                        ))));

                player.setChooseEnergyToCrystallize(new ChooseEnergyToCrystallizeCrystallizePref());

                player.setChooseEnergyToGet(
                        //new ChooseEnergyToGetCombo(
                        new ChooseEnergyToGetPrefCardPoint());

                player.setChooseEnergyToReduce(new ChooseEnergyToReducePrefInvoc());

                player.setChooseEnergyToThrow(
                        //new ChooseEnergyToThrowCombo(
                        new ChooseEnergyToThrowPrefCardPoint());

                player.setChooseGoToTheNextSeason(
                        new ChooseGoToTheNextSeasonPrefInvoc());

                player.setChooseGoToThePreviousSeason(new ChooseGoToThePreviousSeasonPrefInvoc());

                player.setChooseNbDeplacementSeason(new ChooseNbDeplacementSeasonTime());

//                player.setChoosePlayerAction(
//                        new ChoosePlayerActionCristallizePref(
//                                Context.isLastYearAndLastSeason,
//                                new ChoosePlayerActionPrefInvoc()));

                player.setChooseSimilarEnergyToDelete(new ChooseSimilarEnergiesToDeletePrefInvoc());

                player.setChooseStayInTheSeason(
                        new ChooseStayInTheSeasonPrefInvoc());

                player.setChooseToKeepDrawnCard(new ChooseToKeepDrawnCardPrefInvoc());

                player.setChooseUseBonusCard(new ChooseUseBonusCardFalse());

                player.setChooseUseDeDeLaMalice(new ChooseUseDeDeLaMaliceSmart());
            }
            case MONTECARLO -> {
                player = new MonteCarlo(name, config, configMonteCarlo, playerType);
            }
            case ADAPTATIVE -> {
                Adaptative adaptative_player = new Adaptative(name, config, playerType);
                adaptative_player.setStrategiesToAdaptative();
                player = adaptative_player; //Obligé de le faire en deux étapes, sinon il reconnait pas le bon type dans la strategie
            }
            default -> throw new PlayerException("Unrecognized type of player");
        }
        return player;
    }
}


//////// How to make a Strategy

// new Strategy1( context1, new Srategy2( context2, new Strategy3() ) )

// Si le context1 est respecté le player utilise la strategy 1
// Si le context1 = false et context 2 = true, le player utilise la strategy2
// Si le context1 = false et context2 = false et context3 = true, la player utilise la strategy 3