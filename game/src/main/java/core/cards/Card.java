package core.cards;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.effects.EffectType;
import core.ia.Player;
import core.ia.inventory.EnergyStock;

import java.util.List;

public interface Card {

    EffectFrequency getEffectFrequency();

    int getCrystalCost();

    void setCrystalCost(int crystalCost); //à voir si on garde les setters

    List<Energy> getEnergyCost();

    void setEnergyCost(List<Energy> energyCost);

    int getPrestigePointValue();

    boolean isPlayers();

    void setPlayers(boolean players);

    int getId();

    void setId(int id);

    EnergyStock getEnergyStock();

    /**
     * Use the current card
     *
     * @param board  Game board
     * @param player Player that the card will be used on
     */
    void use(Board board, Player player);

    /**
     * @return true if the card has a permanent effect
     */
    boolean hasPermanentEffect();

    /**
     * @return EffectType for the permanent effect
     */
    EffectType getEffectType();

    void prepare(Board board);

    default boolean isMagic() {
        return false;
    }

    boolean isActivated();

    void setActivated(boolean activated);

    boolean isActivable();

    String getName();

}
