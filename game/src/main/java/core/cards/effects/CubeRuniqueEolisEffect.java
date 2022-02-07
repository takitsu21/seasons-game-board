package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.util.Renderer;

import java.util.List;

public class CubeRuniqueEolisEffect extends AbstractCard {
    public CubeRuniqueEolisEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    @Override
    public void use(Board board, Player player) {
        // Le Cube runique d’Eolis n’a aucun effet mais rapporte 30 points
        // de prestige en fin de partie.
        Renderer.add(String.format("- %s de %s lui rapporte 30 points de prestige", getName(), player.getName()));
    }

    @Override
    public boolean isMagic() {
        return true;
    }

    @Override
    public EffectType getEffectType() {
        return EffectType.END_GAME;
    }
}
