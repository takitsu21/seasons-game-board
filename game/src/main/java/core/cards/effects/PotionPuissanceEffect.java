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

public class PotionPuissanceEffect extends AbstractCard {
    public PotionPuissanceEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    @Override
    public void use(Board board, Player player) {

        //Pour activer la Potion de puissance, sacrifiez-la afin de piocher
        //une carte pouvoir et d’avancer de 2 cases votre pion du sorcier
        //sur votre jauge d’invocation.
        //Vous êtes obligé de conserver en main la carte pouvoir
        //piochée. En aucun cas vous ne pouvez la défausser

        Renderer.add(String.format("- Sacrifie %s", getName()));
        Inventory inventory = board.getInventories().get(player);
        inventory.getInvocation().setInvocationPoints(inventory.getInvocation().getInvocationPoints() + 2);
        inventory.getInvocation().removeCardFromBoard(this);
        inventory.discard(board, this);
        Card drawnCard = board.getDeck().drawCard(); //J'ai remplacé drawCard(inventory) pour le renderer, du coup la méthode sert plus à rien
        inventory.getHand().addCard(drawnCard);
        Renderer.add(String.format("-- Pioche %s et obtient 2 places d'invocation", drawnCard.getName()));
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
