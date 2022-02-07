package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.util.Renderer;

import java.util.List;

public class BatonDuPrintempsEffect extends AbstractCard {
    public BatonDuPrintempsEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    @Override
    public void use(Board board, Player player) {
        Renderer.add(String.format("- %s est activé", getName()));

//        À chaque fois que vous invoquez une carte pouvoir, avancez
//        votre pion du sorcier de 3 cases sur la piste des cristaux.
//          L’effet du Bâton du printemps n’affecte que les cartes pouvoir invoquées
//        depuis la main du joueur. Les cartes pouvoir mises en jeu gratuitement
//        par l’intermédiaire d’autres cartes comme le Calice divin, l’Orbe de cristal
//        ou la Potion de rêves ne rapportent donc aucun cristal au possesseur du
//        Bâton de printemps

        Renderer.add(String.format("-- %s reçoit 3 cristaux", player.getName()));
        board.getInventories().get(player).addCrystals(3);
    }

    @Override
    public EffectType getEffectType() {
        return EffectType.ON_SUMMON_CARD;
    }

    @Override
    public boolean isMagic() {
        return true;
    }
}
