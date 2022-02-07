package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.Card;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Renderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BalanceIshtarEffect extends AbstractCard {
    public BalanceIshtarEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    @Override
    public void use(Board board, Player player) {
        // Pour  activer  la  Balance  d’Ishtar,  défaussez 3 énergies identiques
        //en  provenance  de  votre  réserve, afin de les cristalliser
        //en 9 cristaux. Avancez alors votre pion du sorcier de 9 cases sur
        //la piste des cristaux.
        //  La Balance d’Ishtar peut être activée même si vous ne disposez pas de
        //l’action de cristallisation.
        //  Si  vous  possédez  une  Bourse  d’Io,  recevez  12  cristaux  au  lieu  de  9  à
        //chaque fois que vous activez la Balance d’Ishtar.


        Inventory inventory = board.getInventories().get(player);
        boolean found = false;
        for (Card card : inventory.getInvocation().getCardsOnBoard()) {
            if (card.getId() == 8) { //s'il y a une Bourse d'Io
                found = true;
                break;
            }
        }

        Renderer.add("Energy Stock "+ player.getFacadeIA().getEnergyStock());
        List<Energy> e = player.chooseSimilarEnergiesToDelete(3);
        Renderer.add("Energy to delete "+e);
        if (!e.isEmpty()) {
            inventory.getEnergyStock().removeEnergy(e);
            int crystalGained = (found ? 12 : 9);
            inventory.setCrystals(inventory.getCrystals() + crystalGained);
            Renderer.add(String.format("-- %s vous fait gagner %d cristaux en défaussant %s", getName(), crystalGained, e));
            Renderer.add(inventory.getEnergyStock().toString());
        } else {
            Renderer.add("-- Rien ne se passe");
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
