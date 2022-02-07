package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Renderer;

import java.util.List;

public class PotionSavoirEffect extends AbstractCard {
    public PotionSavoirEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    /**
     * @param board  Game board
     * @param player Player that the card will be used on
     */
    @Override
    public void use(Board board, Player player) {

        //Pour activer la Potion de savoir, sacrifiez-la afin de recevoir
        //5 énergies de votre choix en provenance du stock. Placez ces
        //énergies dans votre réserve.

        Renderer.add(String.format("- Sacrifie %s", getName()));
        Inventory inventory = board.getInventories().get(player);
        inventory.getInvocation().removeCardFromBoard(this);
        inventory.discard(board, this);
        for (int i = 0; i < 5; i++) {
            Energy energyToGet = player.chooseEnergyToGet();
            inventory.getEnergyStock().addEnergy(energyToGet, null);
            Renderer.add(String.format("-- Obtient une énergie %s", energyToGet.name())); //on pourrait faire 2 boucle pour avoir tout sur une ligne
        }
    }

    @Override
    public boolean isMagic() {
        return true;
    }

    @Override
    public boolean isActivable() {
        return true;
    }
}
