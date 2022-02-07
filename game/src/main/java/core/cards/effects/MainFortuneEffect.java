package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.util.Renderer;

import java.util.List;

public class MainFortuneEffect extends AbstractCard {
    public MainFortuneEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    @Override
    public void use(Board board, Player player) {

//        À  chaque  fois  que  vous  invoquez  une  carte  pouvoir,  le  coût
//        de  cette  dernière  est  réduit  d'1  énergie  de  votre  choix.  Le
//        coût des prochaines cartes invoquées ne peut néanmoins être
//        inférieur à 1 énergie.
//                La Main de la fortune ne diminue en aucun cas les coûts d'activation de
//        vos cartes pouvoir (comme celui de l'Orbe de cristal par exemple).
//        La Main de la fortune affecte le coût d'invocation de l'Amulette élémentaire.

        Renderer.add(String.format("- Utilise %s", getName()));
    }

    @Override
    public boolean isMagic() {
        return true;
    }

    @Override
    public EffectType getEffectType() {
        return EffectType.ON_SUMMON_CARD;
    }
}
