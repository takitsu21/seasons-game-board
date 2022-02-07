package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Renderer;

import java.util.List;

public class DeDeLaMaliceEffect extends AbstractCard {

    public DeDeLaMaliceEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    public void use(Board board, Player player) {
        Renderer.add("- Utilise Dé de la malice");

//        Le dé de la malice ne nécessite aucun cristal et aucune énergie
//        pour être invoqué.
//                À chaque fois que vous activez le Dé de la malice, relancez le dé
//        des saisons que vous avez choisi et ce, avant d’avoir effectué
//        ses actions. Ne prenez en compte que le nouveau dé pour
//        effectuer vos actions du tour.
//                Vous recevez 2 cristaux chaque fois que vous activez l’effet de votre Dé
//        de la malice.
//                Si vous possédez 2 Dés de la malice, vous pouvez utiliser les deux à
//        la suite si les actions de vos 2 premiers dés ne vous conviennent pas.
//        Recevez alors 4 cristaux.

        if (!this.isActivated()) {
            Inventory inventory = board.getInventories().get(player);
            inventory.getCurrentDice().reRollDice();
            inventory.addCrystals(2);
            setActivated(true);
            Renderer.add("-- Relance son dé et obtient 2 cristaux");
        } else {
            Renderer.add("-- La carte à déjà été utilisée, rien ne se passe");
        }
        setActivated(true);
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
