package core.ia.strategy.choose.copy_energy_from_player;

import core.cards.Card;
import core.cards.comparator.PrestigePointsCardComparator;
import core.ia.Player;
import core.ia.strategy.choose.ChooseCardComparator;
import core.ia.strategy.choose.ChooseFromPrefList;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.util.CardUtil;
import core.util.ChooseUtil;

import java.util.Collections;
import java.util.List;

//TODO Test
public class ChoosePlayerEnergyToCopyPrefPermanent extends ChoosePlayerEnergyToCopy {
    public ChoosePlayerEnergyToCopyPrefPermanent(IContext context, StrategyChoosePlayerEnergyToCopy nextStrategy) {
        super(context, nextStrategy);
    }

    public ChoosePlayerEnergyToCopyPrefPermanent(StrategyChoosePlayerEnergyToCopy nextStrategy) {
        super(null, nextStrategy);
    }

    public ChoosePlayerEnergyToCopyPrefPermanent() {
        super(Context.everyTime, new ChoosePlayerEnergyToCopyRandom());
    }

    @Override
    public Player choose(Player player) {
        //La carte choisie sera une carte qu'on ne peut pas invoquer et qui est une permanente
        List<Card> summonableCards = player.getFacadeIA().getSummonableCards();
        List<Card> unsummonableCards = player.getFacadeIA().getCardInHand();
        unsummonableCards.removeAll(summonableCards);
        List<Card> unsummonablePermanentCards = CardUtil.getPermanentEffectCardsIn(unsummonableCards);
        Collections.shuffle(unsummonablePermanentCards);
        Card card = null;
        if (!unsummonablePermanentCards.isEmpty()) {
            card = unsummonablePermanentCards.get(0);
        }
        return ChooseUtil.getInstance().getBestSimilaritiesPlayerEnergies(
                player.getFacadeIA().getOtherPLayers(), card);
    }
}
