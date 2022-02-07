package core.ia.strategy.choose.to_keep_drawn_card;

import core.cards.Card;
import core.ia.Player;

public interface StrategyChooseToKeepDrawnCard {

    Boolean doChoose(Player player, Card card);

    Boolean choose(Player player, Card card);

}
