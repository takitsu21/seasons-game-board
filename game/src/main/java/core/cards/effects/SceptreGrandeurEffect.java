package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.Card;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Renderer;

import java.util.List;

public class SceptreGrandeurEffect extends AbstractCard {
    public SceptreGrandeurEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    /**
     * @param board  Game board
     * @param player Player that the card will be used on
     */
    @Override
    public void use(Board board, Player player) {

        //Au moment où vous mettez en jeu le Sceptre de grandeur,
        //avancez de 3 cases votre pion du sorcier sur la piste des cristaux pour
        //chaque autre objet magique en jeu que vous possédez.

        Renderer.add(String.format("- Utilise %s", getName()));
        Inventory inventory = board.getInventories().get(player);
        int count = 0;
        for (Card card : inventory.getInvocation().getCardsOnBoard()) {
            if (card.isMagic() && !card.equals(this)) {
                count += 3;
            }
        }
        inventory.setCrystals(inventory.getCrystals() + count);
        Renderer.add(String.format("-- Obtient %d cristaux", count));
    }

    @Override
    public boolean isMagic() {
        return true;
    }
}
