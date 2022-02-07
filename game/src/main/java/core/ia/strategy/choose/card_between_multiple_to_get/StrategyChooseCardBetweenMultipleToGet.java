package core.ia.strategy.choose.card_between_multiple_to_get;

import core.cards.Card;
import core.ia.Player;

import java.util.List;


public interface StrategyChooseCardBetweenMultipleToGet {

    Card doChoose(Player player, List<Card> cardList);

    Card choose(Player player, List<Card> cardList);

}
