package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Renderer;

import java.util.List;

public class VaseOublieYjangEffect extends AbstractCard {
    public VaseOublieYjangEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    /**
     * @param board  Game board
     * @param player Player that the card will be used on
     */
    @Override
    public void use(Board board, Player player) {
        Renderer.add(String.format("-- Son %s est activé", getName()));

//        À chaque fois que vous mettez en jeu une carte pouvoir, recevez une énergie de votre choix en provenance du stock. Placez
//        cette énergie dans votre réserve.
//                L’effet du Vase oublié d’Yjang n’affecte que les cartes pouvoir
//        invoquées depuis la main du joueur. Les cartes pouvoir mises en jeu
//        gratuitement par l’intermédiaire d’autres cartes comme le Calice
//        divin, l’Orbe de cristal ou la Potion de rêves ne vous rapportent donc
//        aucune énergie.

        Inventory inventory = board.getInventories().get(player);
        Energy energyToGet = player.chooseEnergyToGet();
        Renderer.add(String.format("--- Il obtient une énergie %s", energyToGet.name()));
        if (inventory.getEnergyStock().getNbMaxEnergy() == inventory.getEnergyStock().size()) {
            Energy energyToThrow = player.chooseEnergyToThrow();
            Renderer.add(String.format("--- Et doit jeter %s", energyToThrow.name()));
            inventory.getEnergyStock().addEnergy(energyToGet, energyToThrow);
        } else {
            inventory.getEnergyStock().addEnergy(energyToGet, null);
        }
    }

    @Override
    public EffectType getEffectType() {
        return EffectType.ON_SUMMON_CARD;
    }

    @Override
    public boolean isMagic() {
        return true;
    }
}
