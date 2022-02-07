package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Renderer;

import java.util.List;

public class EarthAmuletEffect extends AbstractCard {
    public EarthAmuletEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    public void use(Board board, Player player) {

        //Au moment où vous mettez en jeu l’Amulette de terre, avancez
        //votre pion du sorcier de 9 cases sur la piste des cristaux.

        Renderer.add(String.format("- Utilise la carte %s", getName()));
        Inventory inventory = board.getInventories().get(player);
        inventory.addCrystals(9);
        Renderer.add("-- Obtient 9 cristaux");
    }

    @Override
    public boolean isMagic() {
        return true;
    }

}
