package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Renderer;

import java.util.List;

public class KairnDestructeurEffect extends AbstractCard {

    public KairnDestructeurEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    @Override
    public void use(Board board, Player player) {
        Renderer.add(String.format("- Utilise %s", getName()));

//        Pour activer Kairn le destructeur, défaussez une énergie de votre
//        choix en provenance de votre réserve. Chaque adversaire recule
//        alors de 4 cases son pion du sorcier sur la piste des cristaux.

        Inventory inventory = board.getInventories().get(player);
        if (inventory.getEnergyStock().size() > 0) {
            Energy energy = player.chooseEnergyToThrow();
            Renderer.add(String.format("-- %s choisi l'energie %s à défausser", player.getName(), energy));
            inventory.getEnergyStock().removeEnergy(energy);
            for (Player p : board.getPlayers()) {
                if (!p.equals(player)) {
                    Inventory currentInventory = board.getInventories().get(p);
                    currentInventory.setCrystals(currentInventory.getCrystals() - 4);
                    Renderer.add(String.format("\t- %s perd 4 cristaux à cause de la carte %s", p.getName(), getName()));
                }
            }
        } else {
            Renderer.add("-- Rien ne se passe");
        }
        setActivated(true);
    }

    @Override
    public boolean isActivable() {
        return true;
    }
}
