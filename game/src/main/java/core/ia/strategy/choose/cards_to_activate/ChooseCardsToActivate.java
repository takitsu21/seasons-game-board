package core.ia.strategy.choose.cards_to_activate;

import core.cards.Card;
import core.cards.effects.DeDeLaMaliceEffect;
import core.ia.Player;
import core.ia.strategy.choose.AbstractStrategy;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.Strategy;

import java.util.List;

public abstract class ChooseCardsToActivate extends AbstractStrategy implements StrategyChooseCardsToActivate {
    private IContext context;
    private StrategyChooseCardsToActivate nextStrategy;

    public ChooseCardsToActivate(IContext context, StrategyChooseCardsToActivate nextStrategy) {
        super(context, (Strategy) nextStrategy);
    }

    public ChooseCardsToActivate() {
        super(Context.everyTime, null);
    }

    @Override
    public Card doChoose(Player player) {
        if (player.getFacadeIA().getActivableCard().isEmpty()){
            return null;
        }
        return (Card) doTheChoose(player);
    }

    @Override
    public abstract Card choose(Player player);

}
