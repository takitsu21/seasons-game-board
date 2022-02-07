package core.game.states;

import core.board.enums.Energy;
import core.board.enums.Seasons;
import core.cards.Card;
import core.dice.Dice;
import core.ia.EnumPlayerAction;
import core.ia.inventory.BonusType;

public class Action {
    private EnumPlayerAction action;
    //    private List<?> turnActions;
    private Energy crystallizedEnergy;
    private int crystalGained;
    private Card usedCard;
    private BonusType choosenBonus;
    private Dice choosenDice;
    private int currentYear;
    private Seasons currentSeason;


//    public Action(EnumPlayerAction action, Object... turnActions) {
//        this.action = action;
//        this.turnActions = List.of(turnActions);
//    }

    public Action(EnumPlayerAction action, int year, Seasons currentSeason) {
        this.action = action;
        this.currentYear = year;
        this.currentSeason = currentSeason;
    }

    public Action(EnumPlayerAction action, Dice choosenDice, int year, Seasons currentSeason) {
        this.action = action;
        this.choosenDice = choosenDice;
        this.currentYear = year;
        this.currentSeason = currentSeason;
    }

    public Action(EnumPlayerAction action, Card usedCard, int year, Seasons currentSeason) {
        this.action = action;
        this.usedCard = usedCard;
        this.currentYear = year;
        this.currentSeason = currentSeason;
    }

    public Action(EnumPlayerAction action, BonusType choosenBonus, int year, Seasons currentSeason) {
        this.action = action;
        this.choosenBonus = choosenBonus;
        this.currentYear = year;
        this.currentSeason = currentSeason;
    }

    public Action(EnumPlayerAction action, Energy crystallizedEnergy, int crystalGained, int year, Seasons currentSeason) {
        this.action = action;
        this.crystallizedEnergy = crystallizedEnergy;
        this.crystalGained = crystalGained;
        this.currentYear = year;
        this.currentSeason = currentSeason;
    }

//    @Override
//    public String toString() {
//        return "Action{" +
//                "action=" + action +
//                ", turnActions=" + turnActions +
//                "}\n";
//    }


    @Override
    public String toString() {
        return "Action{" +
                "action=" + action +
                ", crystallizedEnergy=" + crystallizedEnergy +
                ", crystalGained=" + crystalGained +
                ", usedCard=" + usedCard +
                ", choosenBonus=" + choosenBonus +
                ", choosenDice=" + choosenDice +
                ", currentYear=" + currentYear +
                ", currentSeason=" + currentSeason +
                "}\n";
    }
}
