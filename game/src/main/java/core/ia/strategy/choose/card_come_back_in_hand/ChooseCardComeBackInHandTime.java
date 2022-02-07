package core.ia.strategy.choose.card_come_back_in_hand;

import core.cards.Card;
import core.cards.effects.BottesTemporelles;
import core.ia.Player;
import core.ia.strategy.choose.ChooseFromPrefList;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;

import java.util.List;

public class ChooseCardComeBackInHandTime extends ChooseCardComeBackInHand {

    public ChooseCardComeBackInHandTime(IContext context, StrategyChooseCardComeBackInHand nextStrategy) {
        super(context, nextStrategy);
    }

    public ChooseCardComeBackInHandTime(StrategyChooseCardComeBackInHand nextStrategy){ super(null,nextStrategy);}

    public ChooseCardComeBackInHandTime() {
        super(Context.everyTime, new ChooseCardComeBackInHandRandom());
    }

    @Override
    public Card choose(Player player) {
        // Si le joueur possède la carte Bottes Temporelles dans son board il la reprend dans sa main
        // cela lui permettra de la rejouer plus tard et donc rejouer avec le temps (c'est une carte qui s'active uniquement
        // a l'invocation)
        List<Card> cards = player.getFacadeIA().getMagicCardsOnBoard();
        for (Card card : cards){
            if (card instanceof BottesTemporelles){
                return card;
            }
        }
        return null;
        //return ChooseFromPrefList.getInstance().chooseCardToRemoveFromBoard(cards, CardUtil.getCardsPlayedWithTime());
    }
}
