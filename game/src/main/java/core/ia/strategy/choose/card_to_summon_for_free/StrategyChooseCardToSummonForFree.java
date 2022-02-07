package core.ia.strategy.choose.card_to_summon_for_free;

import core.cards.Card;
import core.ia.Player;

public interface StrategyChooseCardToSummonForFree {

    Card doChoose(Player player);

    Card choose(Player player);
}
