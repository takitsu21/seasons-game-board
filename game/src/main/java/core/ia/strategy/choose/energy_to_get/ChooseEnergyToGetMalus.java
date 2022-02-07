package core.ia.strategy.choose.energy_to_get;

import core.board.enums.Energy;
import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

import java.util.ArrayList;
import java.util.List;

public class ChooseEnergyToGetMalus extends ChooseEnergyToGet {

    public ChooseEnergyToGetMalus(IContext context, StrategyChooseEnergyToGet nextStrategy) {
        super(context, nextStrategy);
    }
    public ChooseEnergyToGetMalus(StrategyChooseEnergyToGet nextStrategy){ super(null, nextStrategy);}
    public ChooseEnergyToGetMalus() {
        super(Context.everyTime, new ChooseEnergyToGetRandom());
    }

    public Energy choose(Player player) {
        List<Card> cardInHand = new ArrayList<>(player.getFacadeIA().getCardInHand());
        List<Class<?>> malusCards = CardUtil.getMalusCards();
        List<Card> cardsToCheck = new ArrayList<>();
        for (Card card : cardInHand) {
            if (malusCards.contains(card.getClass())) {
                cardsToCheck.add(card);
            }
        }
        if (!cardsToCheck.isEmpty()) {
            return player.getFacadeIA().missingEnergy(cardsToCheck);
        }
        return player.getFacadeIA().missingEnergy(cardInHand);
    }
}
