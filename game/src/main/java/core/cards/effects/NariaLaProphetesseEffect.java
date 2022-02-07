package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.Card;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Renderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NariaLaProphetesseEffect extends AbstractCard {

    public NariaLaProphetesseEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    public void use(Board board, Player player) {
        Renderer.add("- Utilise la carte Naria la prophétesse");

//        Au moment où vous mettez en jeu Naria la prophétesse, piochez
//        autant de cartes que de joueurs (vous y compris). Choisissez-en une et
//        placez-la dans votre main. Ensuite, parmi les cartes restantes, distribuez-en une de votre choix à chacun de vos adversaires.

        List<Card> cards = board.getDeck().sampleCard(board.getPlayers().length);

        Map<Player, Card> map = player.chooseCardNariaLaProfetesse(cards);
        for (Player p : map.keySet()) {
            if(map.get(p)!=null) {
                board.getInventories().get(p).getHand().addCard(map.get(p));
                if (p == player) {
                    Renderer.add(String.format("-- Pioche %s grâce à Naria la prophétesse", map.get(p).getName()));
                } else {
                    Renderer.add(String.format("-- Donne %s à %s", map.get(p).getName(), p.getName()));
                }
            }
        }
    }
}
