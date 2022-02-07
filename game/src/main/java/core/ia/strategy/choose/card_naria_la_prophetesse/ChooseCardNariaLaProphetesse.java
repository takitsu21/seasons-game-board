package core.ia.strategy.choose.card_naria_la_prophetesse;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;

import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.util.*;

public abstract class ChooseCardNariaLaProphetesse implements StrategyChooseCardNariaLaProphetesse {
    private IContext context;
    private StrategyChooseCardNariaLaProphetesse nextStrategy;

    public ChooseCardNariaLaProphetesse(IContext context, StrategyChooseCardNariaLaProphetesse nextStrategy) {
        this.context = context;
        this.nextStrategy = nextStrategy;
    }

    public ChooseCardNariaLaProphetesse() {
        this.nextStrategy = null;
        this.context = Context.everyTime;
    }

    @Override
    public Map<Player, Card> doChoose(Player player, List<Card> cards) {
        if (cards.isEmpty()){
            return new HashMap<>();
        }
        if (context == null || context.isVerified(player)){
            Map<Player, Card> res = choose(player,cards);
            if (Objects.equals(res, new HashMap<Player, Card>())){
                return nextStrategy.doChoose(player,cards);
            }
            else {
                return res;
            }
        }
        else{
            return nextStrategy.doChoose(player,cards);
        }
    }

    @Override
    public abstract Map<Player, Card> choose(Player player, List<Card> cards);
}
