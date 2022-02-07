package core.ia.strategy.choose.cards_to_activate;

import core.cards.Card;
import core.cards.effects.BalanceIshtarEffect;
import core.cards.effects.DeDeLaMaliceEffect;
import core.cards.effects.PotionVieEffect;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class  ChooseCardsToActivatePrefCrystallize extends ChooseCardsToActivate {

    public ChooseCardsToActivatePrefCrystallize(IContext context, StrategyChooseCardsToActivate nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardsToActivatePrefCrystallize() {
        super(Context.everyTime, new ChooseCardsToActivateRandom());
    }

    @Override
    public Card choose(Player player) {
        List<Card> cards =new ArrayList<>(player.getFacadeIA().getActivableCard());
        Collections.shuffle(cards); //on shuffle pour ne pas toujours activer la même carte
        List<Class<?>> activableCrystalCards = new ArrayList<>(Arrays.asList(
                PotionVieEffect.class,
                DeDeLaMaliceEffect.class,
                BalanceIshtarEffect.class));
        for (Card card : cards) {
            if (activableCrystalCards.contains(card.getClass())) {
                return card;
            }
        }
        return null;
    }
}
