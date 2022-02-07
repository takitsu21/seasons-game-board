package core.cards;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.effects.EffectType;
import core.ia.inventory.EnergyStock;

import java.util.List;

public abstract class AbstractCard implements Card, Cloneable {
    private final EffectFrequency effectFrequency;
    private final String name;
    private final int prestigePointValue;
    private EnergyStock energyStock;
    private int id;
    private int crystalCost;
    private List<Energy> energyCost;
    private boolean players;
    private boolean isActivated = false;
    private boolean isActivable = false;

    protected AbstractCard(Integer id,
                           Boolean players,
                           Integer crystalCost,
                           List<Energy> energyCost,
                           EffectFrequency effectFrequency,
                           String name,
                           Integer prestigePointValue) {
        this.id = id;
        this.players = players;
        this.crystalCost = crystalCost;
        this.energyCost = energyCost;
        this.effectFrequency = effectFrequency;
        this.prestigePointValue = prestigePointValue;
        this.name = name;
        energyStock = new EnergyStock();
    }

    @Override
    public int getCrystalCost() {
        return crystalCost;
    }

    @Override
    public void setCrystalCost(int crystalCost) {
        this.crystalCost = crystalCost;
    }

    public List<Energy> getEnergyCost() {
        return energyCost;
    }

    public void setEnergyCost(List<Energy> energyCost) {
        this.energyCost = energyCost;
    }

    public EffectFrequency getEffectFrequency() {
        return effectFrequency;
    }

    public int getPrestigePointValue() {
        return prestigePointValue;
    }

    public String getName() {
        return name;
    }

    public boolean isPlayers() {
        return players;
    }

    public void setPlayers(boolean players) {
        this.players = players;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnergyStock getEnergyStock() {
        return energyStock;
    }

    @Override
    public boolean hasPermanentEffect() {
        return effectFrequency == EffectFrequency.EACH_TURN
                || effectFrequency == EffectFrequency.ON_PLAY_AND_EACH_TURN;
    }

    @Override
    public EffectType getEffectType() {
        return EffectType.NONE;
    }

    @Override
    public void prepare(Board board) {
    }

    @Override
    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public boolean isActivable() {
        return isActivable;
    }

    @Override
    public String toString() {
        return "AbstractCard{" +
                "effectFrequency=" + effectFrequency +
                ", name='" + name + '\'' +
                ", prestigePointValue=" + prestigePointValue +
                ", energyStock=" + energyStock +
                ", id=" + id +
                ", crystalCost=" + crystalCost +
                ", energyCost=" + energyCost +
                ", isActivated=" + isActivated +
                ", isActivable=" + isActivable() +
                "}\n";
    }

    public void setEnergyStock(EnergyStock energyStock) {
        this.energyStock = energyStock;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        AbstractCard cloneAbstractCard= (AbstractCard) super.clone();
        cloneAbstractCard.setEnergyStock((EnergyStock) energyStock.clone());
        return cloneAbstractCard;
    }
}
