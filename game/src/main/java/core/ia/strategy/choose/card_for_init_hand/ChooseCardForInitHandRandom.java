package core.ia.strategy.choose.card_for_init_hand;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseRandom;
import core.ia.strategy.choose.IContext;
import core.util.Config;

import java.util.List;

public class ChooseCardForInitHandRandom extends ChooseCardForInitHand {

    public ChooseCardForInitHandRandom(IContext context, StrategyChooseCardForInitHand nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardForInitHandRandom(StrategyChooseCardForInitHand nextStrategy){ super(null, nextStrategy);}
    public ChooseCardForInitHandRandom() {
        super();
    }

    public Card[][] choose(Player player, List<Card> deck, Config config) {
        Card[][] cards = new Card[config.getNbYears()][config.getNbCardsPerYear()];
        Card card;
        for (int i = 0; i < config.getNbYears(); i++) {
            for (int j = 0; j < config.getNbCardsPerYear(); j++) {
                card = (Card) ChooseRandom.getInstance().chooseRandom(deck);
                deck.remove(card);
                cards[i][j] = card;
            }
        }
        return cards;
    }
}
