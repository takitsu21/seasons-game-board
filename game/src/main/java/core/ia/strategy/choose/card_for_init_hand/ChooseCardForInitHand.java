package core.ia.strategy.choose.card_for_init_hand;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.Config;

import java.util.List;

public abstract class ChooseCardForInitHand implements StrategyChooseCardForInitHand {
    private StrategyChooseCardForInitHand nextChooseCardForInitHand;
    private IContext context;

    public ChooseCardForInitHand(IContext context, StrategyChooseCardForInitHand nextStrategy) {
        this.context = context;
        this.nextChooseCardForInitHand = nextStrategy;
    }

    public ChooseCardForInitHand() {
        this.context = Context.everyTime;
        this.nextChooseCardForInitHand = null;
    }

    @Override
    public Card[][] doChoose(Player player, List<Card> deck, Config config) {
        if (deck.isEmpty()){
            return null;
        }
        if (context == null || context.isVerified(player)){
            Card[][] res = choose(player,deck,config);
            if (res == null){
                return nextChooseCardForInitHand.doChoose(player,deck,config);
            }
            else {
                return res;
            }
        }
        else {
            return nextChooseCardForInitHand.doChoose(player, deck, config);
        }
    }

    @Override
    public abstract Card[][] choose(Player player, List<Card> deck, Config config);
}
