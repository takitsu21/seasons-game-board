package core.ia;

import core.board.Board;
import stats.Stats;

public class PlayerStats implements Cloneable{
    private Player player;
    private int penaltyCard = 0;
    private int penaltyBonusStats = 0;
    private int pointsCristal = 0;
    private int pointsCard = 0;
    private int cardDrawn = 0;
    private int cardInvoked = 0;
    private int cardActivated = 0;
    private int finalInvocationGauge = 0;
    private int finalCardGameInGame = 0;
    private int cristalsGainedByCristalization = 0;
    private int prestigePoints = 0;
    private TypeAIPlayer aiType;
    private boolean isWinner;


    public PlayerStats(Player player) {
        this.player = player;
    }

    public Stats exportStats() {
        return new Stats(
                player.getName(),
                getPenaltyCard(),
                getPenaltyBonusStats(),
                getPointsCristal(),
                getPointsCard(),
                getCardDrawn(),
                getCardInvoked(),
                getCardActivated(),
                getFinalInvocationGauge(),
                getFinalCardGameInGame(),
                getCristalsGainedByCristalization(),
                getPrestigePoints(),
                player.typeAIPlayer.ordinal(),
                isWinner()
        );
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public int getPrestigePoints() {
        return prestigePoints;
    }

    public void setPrestigePoints(int prestigePoints) {
        this.prestigePoints = prestigePoints;
    }

    public int getPenaltyCard() {
        return penaltyCard;
    }

    public void setPenaltyCard(int penaltyCard) {
        this.penaltyCard = penaltyCard;
    }

    public int getPenaltyBonusStats() {
        return penaltyBonusStats;
    }

    public void setPenaltyBonusStats(int penaltyBonusStats) {
        this.penaltyBonusStats = penaltyBonusStats;
    }

    public int getPointsCristal() {
        return pointsCristal;
    }

    public void setPointsCrystal(int pointsCristal) {
        this.pointsCristal = pointsCristal;
    }

    public int getCardDrawn() {
        return cardDrawn;
    }

    public void setCardDrawn(int cardDrawn) {
        this.cardDrawn = cardDrawn;
    }

    public int getPointsCard() {
        return pointsCard;
    }

    public void setPointsCard(int pointsCard) {
        this.pointsCard = pointsCard;
    }

    public int getCardInvoked() {
        return cardInvoked;
    }

    public void setCardInvoked(int cardInvoked) {
        this.cardInvoked = cardInvoked;
    }

    public int getCardActivated() {
        return cardActivated;
    }

    public void setCardActivated(int cardActivated) {
        this.cardActivated = cardActivated;
    }

    public int getFinalInvocationGauge() {
        return finalInvocationGauge;
    }

    public void setFinalInvocationGauge(int finalInvocationGauge) {
        this.finalInvocationGauge = finalInvocationGauge;
    }

    public int getFinalCardGameInGame() {
        return finalCardGameInGame;
    }

    public void setFinalCardGameInGame(int finalCardGameInGame) {
        this.finalCardGameInGame = finalCardGameInGame;
    }

    public int getCristalsGainedByCristalization() {
        return cristalsGainedByCristalization;
    }

    public void setCristalsGainedByCristalization(int cristalsGainedByCristalization) {
        this.cristalsGainedByCristalization = cristalsGainedByCristalization;
    }

    public void setAiType(TypeAIPlayer aIType) {
        this.aiType = aIType;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "PlayerStats{" +
                "player=" + player +
                ", penaltyCard=" + penaltyCard +
                ", penaltyBonusStats=" + penaltyBonusStats +
                ", pointsCristal=" + pointsCristal +
                ", pointsCard=" + pointsCard +
                ", cardDrawn=" + cardDrawn +
                ", cardInvoked=" + cardInvoked +
                ", cardActivated=" + cardActivated +
                ", finalInvocationGauge=" + finalInvocationGauge +
                ", finalCardGameInGame=" + finalCardGameInGame +
                ", cristalsGainedByCristalization=" + cristalsGainedByCristalization +
                ", prestigePoints=" + prestigePoints +
                '}' + System.lineSeparator();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        PlayerStats clonePlayerStats = (PlayerStats) super.clone();
        return clonePlayerStats;
    }
}
