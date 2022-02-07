package core.ia.strategy.choose.card_naria_la_prophetesse;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Adaptative;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.List;
import java.util.Map;

public class ChooseCardNariaLaProphetesseAdaptative extends ChooseCardNariaLaProphetesse {
    Adaptative self;

    public ChooseCardNariaLaProphetesseAdaptative(IContext context, StrategyChooseCardNariaLaProphetesse nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardNariaLaProphetesseAdaptative(StrategyChooseCardNariaLaProphetesse nextStrategy) {
        super(null, nextStrategy);
    }

    public ChooseCardNariaLaProphetesseAdaptative(Adaptative player) {
        super(Context.everyTime, new ChooseCardNariaLaProphetesseRandom());
        self = player;
    }

    @Override
    public Map<Player, Card> choose(Player player, List<Card> cards) {
        self.analyzeOpponentStrategy();
        Map<Player, Card> result = self.chooseCardNariaLaProfetesse(cards);
        self.setStrategiesToAdaptative();
        return result;
    }
}