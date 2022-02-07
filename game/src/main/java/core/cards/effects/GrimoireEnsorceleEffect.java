package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.ia.inventory.PlayerEnergyStock;
import core.util.Renderer;

import java.util.List;

public class GrimoireEnsorceleEffect extends AbstractCard {

    public GrimoireEnsorceleEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    @Override
    public void use(Board board, Player player) {
        Renderer.add("- Utilise Grimoire ensorcelé");

//        Au moment où vous mettez en jeu le Grimoire ensorcelé, recevez 2 énergies de votre choix en provenance du stock, que vous
//        placez dans votre réserve.
//        Tant que le Grimoire ensorcelé est en jeu, il augmente la
//        capacité de votre réserve, lui permettant de stocker jusqu’à
//        10 énergies au lieu de 7.
//        Les énergies placées sur le Grimoire ensorcelé sont considérées comme
//        faisant partie de votre réserve. Elles sont donc affectées par l’effet du
//        Coffret merveilleux, de la Corne du mendiant, de l’Elémentaire d’air, de la
//        Lanterne de Xidit, de Lewis Grisemine, de la Potion de rêves, de la Potion
//        de vie et du Traité maudit d’Arus.
//                Il est possible à tout moment de déplacer les énergies présentes sur
//        votre Grimpoire ensorcelé vers votre réserve et vice-versa.
//                Posséder deux Grimoires ensorcelés ne permet pas d’avoir jusqu’à
//        13 énergies dans sa réserve. La limite est de 10 énergies maximum. Un
//        joueur ne peut donc pas entreposer d’autres énergies sur le deuxième
//        Grimoire ensorcelé.

        PlayerEnergyStock playerEnergyStock = board.getInventories().get(player).getEnergyStock();
        if (playerEnergyStock.getNbMaxEnergy() == 7) {
            playerEnergyStock.modifyNbMaxEnergy(10);

            Renderer.add("-- Sa réserve d'énergies peut maintenant stocker 10 énergies");

            Energy choosenEnergy = player.chooseEnergyToGet();
            playerEnergyStock.addEnergy(choosenEnergy);
            Renderer.add(String.format("-- Obtient une énergie %s", choosenEnergy.name()));

            choosenEnergy = player.chooseEnergyToGet();
            playerEnergyStock.addEnergy(choosenEnergy);
            Renderer.add(String.format("-- Obtient une énergie %s", choosenEnergy.name()));

        } else {
            Renderer.add("-- Sa réserve d'énergie ne peut pas stocker plus de 10 énergies");

            Energy choosenEnergy = player.chooseEnergyToGet();
            Energy energytoRemove = player.chooseEnergyToThrow();
            playerEnergyStock.addEnergy(choosenEnergy, energytoRemove);
            if (energytoRemove != null) {
                Renderer.add(String.format("-- Echange son énergie %s contre une énergie %s", energytoRemove.name(), choosenEnergy.name()));
            }

            choosenEnergy = player.chooseEnergyToGet();
            energytoRemove = player.chooseEnergyToThrow();
            playerEnergyStock.addEnergy(choosenEnergy, energytoRemove);
            if (energytoRemove != null) {
                Renderer.add(String.format("-- Echange son énergie %s contre une énergie %s", energytoRemove.name(), choosenEnergy.name()));
            }
        }
    }

    @Override
    public boolean isMagic() {
        return true;
    }
}
