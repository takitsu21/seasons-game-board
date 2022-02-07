package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Renderer;

import java.util.List;

public class HeaumeRagfieldEffect extends AbstractCard {
    public HeaumeRagfieldEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    @Override
    public void use(Board board, Player player) {

        //Si vous êtes le joueur ayant en jeu strictement le plus de cartes
        //pouvoir  à  la  fin  de  la  partie,  avancez  votre  pion  du  sorcier  de  20
        //cases sur la piste des cristaux.

        Inventory inventory = board.getInventories().get(player);
        int referencePlayer = inventory.getInvocation().getCurrentInvocations() - player.getFacadeIA().getMagicCardsOnBoard().size();
        boolean isGreater = true;
        for (Player p : board.getPlayers()) {
            if (!p.equals(player)
                    && board.getInventories().get(p).getInvocation().getCurrentInvocations() - p.getFacadeIA().getMagicCardsOnBoard().size() >= referencePlayer) {
                isGreater = false;
                break;
            }
        }
        if (isGreater) {
            inventory.setCrystals(inventory.getCrystals() + 20);
            Renderer.add(String.format("Joueur %s gagne 20 cristaux grâce à la carte %s", player.getName(), getName()));
        }
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
