package core.ia.strategy.choose.energy_to_get;

import core.board.enums.Energy;
import core.cards.Card;
import core.cards.comparator.CostCardComparator;
import core.ia.Player;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

import java.util.ArrayList;
import java.util.List;

public class ChooseEnergyToGetPrefPermanent extends ChooseEnergyToGet {

    public ChooseEnergyToGetPrefPermanent(IContext context, StrategyChooseEnergyToGet nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseEnergyToGetPrefPermanent(StrategyChooseEnergyToGet nextStrategy){ super(null, nextStrategy);}
    public ChooseEnergyToGetPrefPermanent() {
        super(Context.everyTime, new ChooseEnergyToGetRandom());
    }

    public Energy choose(Player player) {
        List<Card> cardInHand = new ArrayList<>(player.getFacadeIA().getCardInHand());
        cardInHand = CardUtil.getPermanentEffectCardsIn(cardInHand);

        return player.getFacadeIA().missingEnergy(cardInHand);
    }
}
