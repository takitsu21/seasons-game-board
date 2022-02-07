package core.ia.strategy.choose.cards_to_activate;

import core.cards.Card;
import core.ia.Player;

public interface StrategyChooseCardsToActivate {

    Card doChoose(Player player);

    Card choose(Player player);

}
