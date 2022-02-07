package core.ia.strategy.choose.card_to_summon_for_free;

import core.cards.AbstractCard;
import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.AbstractStrategy;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.Strategy;

import java.util.List;

public abstract class ChooseCardToSummonForFree extends AbstractStrategy implements StrategyChooseCardToSummonForFree {

    public ChooseCardToSummonForFree(IContext context, StrategyChooseCardToSummonForFree nextStrategy) {
        super(context, (Strategy) nextStrategy);
    }

    public ChooseCardToSummonForFree() {
        super(Context.everyTime, null);
    }


    @Override
    public Card doChoose(Player player) {
        return (Card) doTheChoose(player);
    }

    @Override
    public abstract Card choose(Player player);
}
