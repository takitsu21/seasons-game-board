package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Renderer;

import java.util.List;

public class CoffretMerveilleuxEffect extends AbstractCard {
    public CoffretMerveilleuxEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    @Override
    public void use(Board board, Player player) {
        Renderer.add(String.format("- %s de %s est activée", getName(), player.getName()));

        //À chaque fois que vous possédez 4 énergies ou plus dans votre
        //réserve à la fin de la manche, recevez 3 cristaux. Avancez alors
        //de 3 cases votre pion du sorcier sur la piste des cristaux.
        //   L’effet  du  Coffret  merveilleux  prend  en  compte  les  énergies
        //placées  sur  le  Grimoire  ensorcelé  qui  font  partie  de  votre
        //réserve. A l’inverse, les énergies présentes sur l’Amulette d’eau
        //ne font pas partie de votre réserve et n’affectent donc pas le Coffret merveilleux.

        Inventory inventory = board.getInventories().get(player);
        if (inventory.getEnergyStock().getEnergyStock().size() >= 4) {
            inventory.setCrystals(inventory.getCrystals() + 3);
            Renderer.add("-- Il reçoit 3 cristaux.");
        } else {
            Renderer.add("-- Rien ne se passe");
        }
    }

    @Override
    public boolean isMagic() {
        return true;
    }

    @Override
    public EffectType getEffectType() {
        return EffectType.END_TURN;
    }
}
