package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.Card;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.ia.PlayerStats;
import core.ia.inventory.Inventory;
import core.util.Renderer;

import java.util.List;

public class PotionRevesEffect extends AbstractCard {

    public PotionRevesEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    @Override
    public void use(Board board, Player player) {
        Renderer.add(String.format("- Sacrifie %s", this.getName()));

//        Pour activer la Potion de rêves, sacrifiez-la et défaussez toutes
//        les énergies de votre réserve afin d’invoquer gratuitement une
//        carte pouvoir de votre main.
//                Si un joueur ne possède aucune énergie, il peut utiliser la
//        Potion de rêves.
//                Si un joueur active la Potion de rêves, les énergies placées sur l’Amulette
//        d’eau ou sur le Chaudron glouton ne sont pas défaussées, au contraire
//        des énergies placées sur le Grimoire ensorcelé.
//                La carte invoquée par la Potion de rêves n’est pas affectée par les effets
//        de l’Arcano Sangsue, du Bâton de printemps et du Vase oublié d’Yjang.


        Inventory inventory = board.getInventories().get(player);
        inventory.getEnergyStock().getEnergyStock().removeAll(inventory.getEnergyStock().getEnergyStock());
        inventory.getInvocation().removeCardFromBoard(this);
        inventory.discard(board, this);
        Renderer.add(String.format("-- Sacrifie %s et toutes les énergies de sa réserve", this.getName()));

        Card card = player.chooseCardToSummonForFree();

        if (card != null) {
            if (inventory.summonForFree(card, board)) {
                if (card.getEffectFrequency() == EffectFrequency.ON_PLAY) {
                    card.use(board, player);
                }
                PlayerStats ps = board.getPlayersStats().get(player);
                ps.setCardInvoked(ps.getCardInvoked() + 1);
            } else {
                Renderer.add(String.format("-- N'a pas pu invoqué %s par manque de place d'invocation", card.getName()));
            }
        } else {
            Renderer.add("-- N'a aucune carte à invoquer");
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
