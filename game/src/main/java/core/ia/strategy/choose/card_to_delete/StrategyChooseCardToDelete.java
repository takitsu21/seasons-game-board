package core.ia.strategy.choose.card_to_delete;

import core.cards.Card;
import core.ia.Player;

import java.util.List;

public interface StrategyChooseCardToDelete {

    Card choose(Player player);

    Card doChoose(Player player);
}
