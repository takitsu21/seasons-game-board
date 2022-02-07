package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Renderer;

import java.util.List;

public class SablierTempsEffect extends AbstractCard {
    public SablierTempsEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    /**
     * @param board  Game board
     * @param player Player that the card will be used on
     */
    @Override
    public void use(Board board, Player player) {

        //À chaque fois que l’on change de saison, recevez une énergie
        //de votre choix en provenance du stock. Placez l’énergie dans
        //votre réserve.

        Renderer.add(String.format("- Le %s de %s est activé", getName(), player.getName()));
        Inventory inventory = board.getInventories().get(player);
        Energy energyToGet = player.chooseEnergyToGet();
        inventory.getEnergyStock().addEnergy(energyToGet, null);
        Renderer.add(String.format("-- Il obtient une énergie %s", energyToGet.name()));
        setActivated(true); //pas sûr de celle ligne -l'Archange encore et toujours
    }

    @Override
    public EffectType getEffectType() {
        return EffectType.SEASON_CHANGE;
    }

    @Override
    public boolean isMagic() {
        return true;
    }
}
