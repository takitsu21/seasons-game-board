package core.ia.strategy.choose.card_for_init_hand;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFirst;
import core.ia.strategy.choose.IContext;
import core.util.Config;

import java.util.List;

public class ChooseCardForInitHandFirst extends ChooseCardForInitHand {

    public ChooseCardForInitHandFirst(IContext context, StrategyChooseCardForInitHand nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardForInitHandFirst(StrategyChooseCardForInitHand nextStrategy){ super(null, nextStrategy);}
    public ChooseCardForInitHandFirst() {
        super();
    }

    public Card[][] choose(Player player, List<Card> deck, Config config) {
        Card[][] cards = new Card[config.getNbYears()][config.getNbCardsPerYear()];
        Card card;
        for (int i = 0; i < config.getNbYears(); i++) {
            for (int j = 0; j < config.getNbCardsPerYear(); j++) {
                card = (Card) ChooseFirst.getInstance().chooseFirst(deck);
                deck.remove(card);
                cards[i][j] = card;
            }
        }
        return cards;
    }
}



