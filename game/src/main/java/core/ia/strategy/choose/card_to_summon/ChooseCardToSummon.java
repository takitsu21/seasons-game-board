package core.ia.strategy.choose.card_to_summon;

import core.board.enums.Energy;
import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.AbstractStrategy;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.Strategy;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class ChooseCardToSummon extends AbstractStrategy implements StrategyChooseCardToSummon {

    public ChooseCardToSummon(IContext context, StrategyChooseCardToSummon nextStrategy) {
        super(context,(Strategy) nextStrategy);
    }

    public ChooseCardToSummon() {
        super(Context.everyTime, null);
    }


    @Override
    public Card doChoose(Player player) {
        List<Card> summonableCards = player.getFacadeIA().getSummonableCards();
        if (summonableCards.isEmpty()){
            return null;
        }
        else{
            return (Card) doTheChoose(player);
        }
    }

    @Override
    public abstract Card choose(Player player);

    @Nullable
    public static Card getCardToSummon(Player player, List<Card> cardInHand) {
        for (Card card : cardInHand) {
            List<Energy> copyOfEnergyCost = player.getFacadeIA().getTriggerMainFortuneIfPossible(card);
            if (player.getFacadeIA().isSummonable(card, copyOfEnergyCost)) {
                return card;
            }
        }
        return null;
    }
}
