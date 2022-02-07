package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.util.Renderer;

import java.util.List;

public class BourseIoEffect extends AbstractCard {
    public BourseIoEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    @Override
    public void use(Board board, Player player) {

//        À chaque fois que vous cristallisez une énergie,
//        avancez votre pion du sorcier d'une case sur
//        la piste des cristaux et ce, en plus du gain de
//        cristaux obtenu grâce à la cristallisation.
//        L'effet de la Bourse d'Io affecte les effets de la
//        Balance d'Ishtar et de la Potion de vie.

        Renderer.add(String.format("- Utilise %s", getName()));
    }

    @Override
    public boolean isMagic() {
        return true;
    }

    @Override
    public EffectType getEffectType() {
        return EffectType.CRYSTAL;
    }
}
