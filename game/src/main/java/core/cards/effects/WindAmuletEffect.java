package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Renderer;

import java.util.List;

public class WindAmuletEffect extends AbstractCard {

    public WindAmuletEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    @Override
    public void use(Board board, Player player) {

//        Au moment où vous mettez en jeu l’Amulette d’air, avancez votre
//        pion du sorcier de 2 cases sur votre jauge d’invocation.

        Renderer.add(String.format("- Utilise %s", getName()));
        Inventory inventory = board.getInventories().get(player);
        inventory.addInvocationPoints(2);
        Renderer.add("-- Gagne 2 places d'invocations");
    }

    @Override
    public boolean isMagic() {
        return true;
    }


}
