package core.ia.strategy.choose.card_for_init_hand;

import core.cards.Card;
import core.ia.Player;
import core.util.Config;

import java.util.List;

public interface StrategyChooseCardForInitHand {

    Card[][] doChoose(Player player, List<Card> deck, Config config);

    Card[][] choose(Player player, List<Card> deck, Config config);
}
