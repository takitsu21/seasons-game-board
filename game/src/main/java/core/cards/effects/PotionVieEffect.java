package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Renderer;

import java.util.List;

public class PotionVieEffect extends AbstractCard {
    public PotionVieEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    /**
     * @param board  Game board
     * @param player Player that the card will be used on
     */
    @Override
    public void use(Board board, Player player) {

        //Pour activer la Potion de vie, sacrifiez-la afin de cristalliser
        //chacune des énergies de votre réserve en 4 cristaux. Avancez
        //votre pion du sorcier d’autant de cases que nécessaire sur
        //la piste des cristaux.
        //On peut activer la Potion de vie sans posséder l’action de cristallisation.
        //Les énergies placées sur l’Amulette d’eau ou le Chaudron glouton ne sont
        //pas affectées par l’effet de la Potion de vie, au contraire des énergies
        //placées sur le Grimoire ensorcelé.
        //On ne peut pas continuer à cristalliser après avoir activé la Potion de vie.
        //La Potion de vie est affectée par l’effet de la Bourse d’Io.

        Renderer.add(String.format("- Sacrifie %s", getName()));
        Inventory inventory = board.getInventories().get(player);
        int crystals = inventory.getEnergyStock().size() * 4;
        inventory.getEnergyStock().getEnergyStock().removeAll(inventory.getEnergyStock().getEnergyStock());
        inventory.setCrystals(inventory.getCrystals() + crystals);
        inventory.getInvocation().removeCardFromBoard(this);
        inventory.discard(board, this);
        Renderer.add(String.format("-- Cristallise ses énergies pour %d cristaux", crystals));
        setActivated(true); //pas nécessaire je pense vu qu'on la jette -l'Archange
    }

    @Override
    public boolean isActivable() {
        return true;
    }

    @Override
    public boolean isMagic() {
        return true;
    }
}
