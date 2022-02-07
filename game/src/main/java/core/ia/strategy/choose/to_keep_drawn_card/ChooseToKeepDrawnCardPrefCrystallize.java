package core.ia.strategy.choose.to_keep_drawn_card;

import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFromPrefList;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

public class ChooseToKeepDrawnCardPrefCrystallize extends ChooseToKeepDrawnCard {

    public ChooseToKeepDrawnCardPrefCrystallize(IContext context, StrategyChooseToKeepDrawnCard nextStrategy){
        super(context, nextStrategy);
    }

    public ChooseToKeepDrawnCardPrefCrystallize(){
        super();
    }

    public Boolean choose(Player player, Card card) {
        return ChooseFromPrefList.getInstance().chooseToKeepCard(card, CardUtil.getCrystalCards());
    }
}
