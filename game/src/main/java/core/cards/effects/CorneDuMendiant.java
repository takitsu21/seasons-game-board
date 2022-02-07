package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.util.Renderer;

import java.util.List;

public class CorneDuMendiant extends AbstractCard {

    public CorneDuMendiant(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    public void use(Board board, Player player) {
        Renderer.add(String.format("- %s de %s est activée", getName(), player.getName()));

//        À chaque fois que vous possédez 1 énergie ou moins dans votre
//        réserve à la fin de la manche, recevez 1 énergie de votre choix
//        en provenance du stock et placez-la dans votre réserve.
//                L’effet de la Corne du mendiant prend en compte les énergies
//        placées sur le Grimoire ensorcelé, qui font partie de votre
//        réserve. À l’inverse, les énergies présentes sur l’Amulette d’eau
//        ne font pas partie de votre réserve et n’affectent pas la Corne du mendiant.

        List<Energy> playerEnergyStock = board.getInventories().get(player).getEnergyStock().getEnergyStock();
        if (playerEnergyStock.size() <= 1) {
            Energy choosenEnergy = player.chooseEnergyToGet();
            playerEnergyStock.add(choosenEnergy);
            Renderer.add(String.format("-- Il reçoit une energie %s", choosenEnergy.toString()));
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
