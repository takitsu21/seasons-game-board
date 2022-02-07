package core.ia.strategy.choose.card_to_delete;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.List;

public class ChooseCardToDeleteAdaptative extends ChooseCardToDelete {
    Adaptative self;

    public ChooseCardToDeleteAdaptative(IContext context, StrategyChooseCardToDelete nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseCardToDeleteAdaptative(StrategyChooseCardToDelete nextStrategy){ super(null,nextStrategy);}

    public ChooseCardToDeleteAdaptative(Adaptative player) {
        super(Context.everyTime, new ChooseCardToDeleteRandom());
        self = player;
    }

    public Card choose(Player player) {
        self.analyzeOpponentStrategy();
        List<Card> cards = self.getFacadeIA().getCardOnBoard();
        Card result = self.chooseCardToDelete(cards);
        self.setStrategiesToAdaptative();
        return result;
    }
}