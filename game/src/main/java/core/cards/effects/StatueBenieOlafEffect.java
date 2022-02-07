package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Renderer;

import java.util.List;

public class StatueBenieOlafEffect extends AbstractCard {
    public StatueBenieOlafEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    /**
     * @param board  Game board
     * @param player Player that the card will be used on
     */
    @Override
    public void use(Board board, Player player) {

//        Au moment où vous mettez en jeu la Statue bénie d’Olaf, avancez
//        votre pion du sorcier de 20 cases sur la piste des cristaux.

        Renderer.add(String.format("- Utilise %s", getName()));
        Inventory inventory = board.getInventories().get(player);
        Renderer.add("-- Gagne 20 cristaux");
        inventory.setCrystals(inventory.getCrystals() + 20);
    }

    @Override
    public boolean isMagic() {
        return true;
    }
}
