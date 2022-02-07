package core.ia.strategy.choose.card_come_back_in_hand;

import core.cards.Card;
import core.ia.Player;

import java.util.ArrayList;

public interface StrategyChooseCardComeBackInHand {

    Card doChoose(Player player);

    Card choose(Player player);
}
