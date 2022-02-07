package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.EffectFrequency;
import core.exception.CardException;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Renderer;

import java.util.List;

public class FigrimAvaricieuxEffect extends AbstractCard {
    public FigrimAvaricieuxEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    @Override
    public void use(Board board, Player player) {
//        Suivant que vous jouez à 2, 3 ou 4 joueurs,
//        le coût d'invocation de Figrim l'avaricieux n'est pas
//        le même.À chaque fois que l'on change de saison, tous vos
//        adversaires doivent vous donner 1 cristal. Ils reculent d'une
//        case leur pion du  sorcier  sur  la  piste  des  cristaux.
//        Avancez  votre  pion  du sorcier  sur  la  même  piste  d'autant
//        de  cases  que  de  cristaux ainsi gagnés. Un  adversaire  n'ayant
//        aucun  cristal  n'est  pas  concerné  par  l'effet  de Figrim l'Avaricieux.
        Renderer.add(String.format("- %s de %s est activée", getName(), player.getName()));

        Inventory dstInventory = board.getInventories().get(player);
        for (Player currentPlayer : board.getPlayers()) {
            if (!currentPlayer.equals(player)) {
                Inventory inventory = board.getInventories().get(currentPlayer);
                int currentCrystals = inventory.getCrystals();

                if (currentCrystals > 0) {
                    inventory.setCrystals(currentCrystals - 1);
                    dstInventory.setCrystals(dstInventory.getCrystals() + 1);
                    Renderer.add(String.format("-- Joueur %s se fait voler un cristal", currentPlayer.getName()));
                }
            }
        }

    }


    @Override
    public EffectType getEffectType() {
        return EffectType.SEASON_CHANGE;
    }

    @Override
    public void prepare(Board board) {
        switch (board.getPlayers().length) {
            case 2 -> setCrystalCost(3);
            case 3 -> setCrystalCost(6);
            case 4 -> setCrystalCost(9);
            default -> throw new CardException("Figrim Avaricieux detecte un nombre de joueur défectueux");
        }
    }
}
