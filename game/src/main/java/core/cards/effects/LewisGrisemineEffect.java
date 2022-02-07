package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Renderer;

import java.util.List;

public class LewisGrisemineEffect extends AbstractCard {
    public LewisGrisemineEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    @Override
    public void use(Board board, Player player) {

        //Au moment où vous mettez en jeu Lewis Grisemine, recevez en
        //provenance du stock exactement le même nombre et le même
        //type d’énergies que l’adversaire de votre choix possède dans
        //sa réserve. Placez les énergies ainsi reçues dans votre réserve.
        //Les énergies copiées chez un adversaire ne sont pas volées, ce dernier
        //les conserve.
        //Lewis Grisemine ne copie pas les énergies placées sur l’Amulette d’eau.
        //En revanche, il peut copier les énergies placées sur un Grimoire ensorcelé.

        Renderer.add(String.format("- Utilise %s", getName()));
        Player playerSelected = player.choosePlayer();
        Inventory inventory = board.getInventories().get(player);

        inventory.getEnergyStock().getEnergyStock().clear();
        Renderer.add(String.format("-- Copie les énergies de %s", playerSelected.getName()));
        for (Energy e : board.getInventories().get(playerSelected).getEnergyStock().getEnergyStock()) {
            inventory.getEnergyStock().addEnergy(e);
        }
    }
}
