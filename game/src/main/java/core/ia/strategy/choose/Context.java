package core.ia.strategy.choose;

import core.board.enums.Seasons;
import core.cards.Card;
import core.ia.Player;

public class Context {
    public static IContext everyTime = (player) -> true;


    //Contextes liés au temps
    public static IContext isFirstYear = player -> player.getFacadeIA().getNbYear() == 1;

    public static IContext isMiddleGame = player -> player.getFacadeIA().getNbYear() > 1
            && player.getFacadeIA().getNbYear() < player.getFacadeIA().getConfig().getNbYears();

    public static IContext isLastYear = (player) -> player.getFacadeIA().getConfig().getNbYears() == player.getFacadeIA().getNbYear();

    public static IContext isFirstYearOrSecondYear = (player) -> player.getFacadeIA().getNbYear()==1
            || player.getFacadeIA().getNbYear()==2;

    public static IContext isLastYearAndLastSeason = (player) -> player.getFacadeIA().getNbYear() == player.getFacadeIA().getConfig().getNbYears()
            && player.getFacadeIA().getSeason() == Seasons.AUTUMN;


    //Contextes liés à la main/aux invocations
    public static IContext emptyHand = (player) -> player.getFacadeIA().getCardInHand().isEmpty();

    public static IContext notEmptyHand = (player) -> !player.getFacadeIA().getCardInHand().isEmpty();

    public static IContext emptyHandAndNotLastSeason = (player) -> emptyHand.isVerified(player) && player.getFacadeIA().getSeason() != Seasons.AUTUMN;

    public static IContext lastYearAndEmptyHand = (player) -> emptyHand.isVerified(player) && isLastYear.isVerified(player);

    public static IContext needInvocationPoint = (player) -> player.getFacadeIA().getCurrentInvocationPoint() == player.getFacadeIA().getInvocationPoint()
            && player.getFacadeIA().getCardInHand().size() != 0;

    public static IContext cannotSummonAnything = player -> player.getFacadeIA().getSummonableCards().isEmpty();

    public static IContext tooManyCardsInHand = player -> player.getFacadeIA().getCardInHand().size() > 3; //nombre arbitraire

    public static IContext badHandForEndGame = player -> isLastYear.isVerified(player) && tooManyCardsInHand.isVerified(player);

    public static IContext shouldSummon = player -> tooManyCardsInHand.isVerified(player) && !needInvocationPoint.isVerified(player) && !cannotSummonAnything.isVerified(player);

    public static IContext bigCardPointInHand = (player) ->{
        for (Card card: player.getFacadeIA().getCardInHand()){
            if(card.getPrestigePointValue()>=10){
                return true;
            }
        }
        return false;
    };

    public static IContext bigCardPointOnBoard = (player) ->{
        for (Card card: player.getFacadeIA().getCardOnBoard()){
            if(card.getPrestigePointValue()>=10){
                return true;
            }
        }
        return false;
    };


    //Contextes liés aux énergies
    public static IContext fullOfEnergy = (player) -> player.getFacadeIA().getEnergyStock(player).size() == player.getFacadeIA().getMaxSizeEnergyStock(player);

    public static IContext emptyEnergy = (player) -> player.getFacadeIA().getEnergyStock().isEmpty();

    public static IContext hasEnergy = (player) -> !player.getFacadeIA().getEnergyStock(player).isEmpty();

    public static IContext needNewEnergy = player -> fullOfEnergy.isVerified(player) && cannotSummonAnything.isVerified(player);

    public static IContext uselessEnergy = (player) -> player.getFacadeIA().uselessEnergy(player.getFacadeIA().getCardInHand()) == null;


    //Contextes liés aux cristaux/points de prestiges
    public static IContext allThePlayerHaveMoreCrystals = (player) -> {
        boolean bool = true;
        for (Player otherPlayer : player.getFacadeIA().getOtherPLayers()){
            if (player.getFacadeIA().getNbCrystals(otherPlayer) < player.getFacadeIA().getNbCrystals(player)){
                bool = false;
                break;
            }
        }
        return bool;
    };

    public static IContext needCrystalsEndGame = player -> isLastYear.isVerified(player) && allThePlayerHaveMoreCrystals.isVerified(player);

    public static IContext shouldCrystallize = player -> fullOfEnergy.isVerified(player) && needCrystalsEndGame.isVerified(player);

    public static IContext hasMinimumCardPoints = (player) -> {
        for (Card card : player.getFacadeIA().getCardInHand()) {
            if (card.getPrestigePointValue() > 0) {
                return true;
            }
        }
        return false;
    };

    public static IContext firstYear = (player) ->{
        return player.getFacadeIA().getNbYear() == 1;
    };

    public static IContext secondYear = (player) ->{
        return player.getFacadeIA().getNbYear() == 2;
    };

    public static IContext thirdYear = (player) ->{
        return player.getFacadeIA().getNbYear() == 3;
    };

    public static IContext notThirdYear = (player) -> {
        return !thirdYear.isVerified(player);
    };

    public static IContext never = (player) -> {
        return false;
    };

    public static IContext needInvoc1y2y = (player) -> {
        return (Context.firstYear.isVerified(player) || Context.secondYear.isVerified(player))
                && Context.needInvocationPoint.isVerified(player);
    };

    public static IContext needInvocDiceNoInvoc = (player) -> {
        if (Context.needInvocationPoint.isVerified(player) &&
                !player.getFacadeIA().getDiceCurrentFace().isInvocationSup()){
            return true;
        }
        else{
            return false;
        }
    };

    public static IContext needEnergyDiceNoEnergy = (player) -> {
        if (player.getFacadeIA().getDiceCurrentFace().getEnergy() == null
                && player.getFacadeIA().getEnergyStock().size() < 2){
            return true;
        }
        else{
            return false;
        }
    };

    public static IContext dontHaveWhatHeWant = (player) -> {
        if (Context.needInvocDiceNoInvoc.isVerified(player) ||
                Context.needEnergyDiceNoEnergy.isVerified(player)){
            return true;
        }
        else{
            return false;
        }
    };


    //Contextes liés aux cartes elles-mêmes
    public static IContext needToUseKairn = player -> allThePlayerHaveMoreCrystals.isVerified(player) && hasEnergy.isVerified(player);
}
