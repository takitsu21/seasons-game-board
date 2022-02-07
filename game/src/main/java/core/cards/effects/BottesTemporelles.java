package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.Card;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Renderer;

import java.util.List;

public class BottesTemporelles extends AbstractCard {
    public BottesTemporelles(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    @Override
    public void use(Board board, Player player) {
//        Les Bottes temporelles ne nécessitent aucun cristal et aucune
//        énergie pour être invoquées.
//        Au moment où vous mettez en jeu les Bottes temporelles,
//        avancez ou reculez d’une à trois cases le marqueur saison sur
//        la roue des saisons.
//        Si les Bottes temporelles font reculer le marqueur saison de l’hiver à
//        l’automne :
//        - reculez le marqueur année d’une case.
//        - conservez toutes les cartes pouvoir que vous avez en main.
//                Si les Bottes temporelles vous amènent à changer de saison (en avançant
//                ou en reculant le marqueur saison), les cartes affectées par le changement de saison comme Figrim l’avaricieux ou le Sablier du temps font
//        immédiatement effet.

        Renderer.add("Bottes");
        int deplacement = player.chooseNbDeplacementSeason(-3, 3);
        Renderer.add("deplacement :"+deplacement);
        Renderer.add("cursor :"+board.getCurrentCursor());
        Renderer.add(String.format("-- Le curseur se déplace de %d cases", deplacement));

        board.setCurrentCursor(board.getCurrentCursor() + deplacement);
        board.getYear().updateYearAndSeason(board);

        Inventory inventory = board.getInventories().get(player);
        for (Card card : inventory.getInvocation().getCardsOnBoard()) {
            if (card instanceof FigrimAvaricieuxEffect) {
                card.use(board, player);
            }

            if (card instanceof SablierTempsEffect) {
                card.use(board, player);
            }
        }
    }

    @Override
    public boolean isMagic() {
        return true;
    }
}
