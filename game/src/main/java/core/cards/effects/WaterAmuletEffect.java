package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.util.Renderer;

import java.util.ArrayList;
import java.util.List;

public class WaterAmuletEffect extends AbstractCard {
    public WaterAmuletEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    @Override
    public void use(Board board, Player player) {

//        Au moment où vous mettez en jeu l’Amulette d’eau, recevez
//        4 énergies de votre choix en provenance du stock et placez-les
//        sur l’Amulette d’eau.
//                Les énergies placées sur l’Amulette d’eau ne sont pas
//        considérées comme faisant partie de votre réserve. Elles ne
//        sont donc pas affectées par les effets du Coffret merveilleux,
//        de la Corne du mendiant, de l’Elémentaire d’air, de Lewis Grisemine,
//        de la Potion de rêves et de la Potion de vie.

        Renderer.add(String.format("- Utilise %s", getName()));
        List<Energy> listForToString = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Energy choosenEnergy = player.chooseEnergyToGet();
            listForToString.add(choosenEnergy);
            getEnergyStock().addEnergy(choosenEnergy);
        }
        Renderer.add(String.format("-- Choisi les énergies: %s", listForToString));
        Renderer.add(String.format("-- Les places sur %s", getName()));
    }

    @Override
    public boolean isMagic() {
        return true;
    }
}
