package core.ia.strategy.choose.card_naria_la_prophetesse;

import core.cards.Card;
import core.ia.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface StrategyChooseCardNariaLaProphetesse {

    Map<Player, Card> doChoose(Player player, List<Card> cards);

    Map<Player, Card> choose(Player player, List<Card> cards);

}
