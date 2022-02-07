package core.ia.strategy.choose.energy_to_throw;

import core.board.enums.Energy;
import core.cards.Card;
import core.ia.Player;
import core.ia.strategy.choose.AbstractStrategy;
import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.IContext;
import core.ia.strategy.choose.Strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ChooseEnergyToThrow extends AbstractStrategy implements StrategyChooseEnergyToThrow {

    public ChooseEnergyToThrow(IContext context, StrategyChooseEnergyToThrow nextStrategy) {
        super(context, (Strategy) nextStrategy);
    }

    public ChooseEnergyToThrow() {
        super(Context.everyTime, null);
    }

    @Override
    public Energy doChoose(Player player) {
        if (player.getFacadeIA().getEnergyStock().isEmpty()){
            return null;
        }
        else{
            return (Energy) doTheChoose(player);
        }
    }

    @Override
    public abstract Energy choose(Player player);

    public Energy getEnergyToThrow(Player player, List<Card> cardInHand) {
        if (cardInHand == null) {
            return null;
        }
        ArrayList<Energy> energiesNeed;
        ArrayList<Energy> energyStock = new ArrayList<>(player.getFacadeIA().getEnergyStock(player));

        int nbWaterStock = Collections.frequency(energyStock, Energy.WATER);
        int nbFireStock = Collections.frequency(energyStock, Energy.FIRE);
        int nbEarthStock = Collections.frequency(energyStock, Energy.EARTH);
        int nbWindStock = Collections.frequency(energyStock, Energy.WIND);

        for (Card card : cardInHand) {
            if (player.getFacadeIA().hasEnoughEnergy(card)) {
                energiesNeed = new ArrayList<>(card.getEnergyCost());
                for (Energy energyNeed : energiesNeed) {
                    if (energyNeed == Energy.WATER) {
                        nbWaterStock -= 1;
                    } else if (energyNeed == Energy.FIRE) {
                        nbFireStock -= 1;
                    } else if (energyNeed == Energy.WIND) {
                        nbWindStock -= 1;
                    } else if (energyNeed == Energy.EARTH) {
                        nbEarthStock -= 1;
                    }
                }
            } else {
                if (nbWaterStock > 0 && nbWaterStock > nbFireStock && nbWaterStock > nbWindStock && nbWaterStock > nbEarthStock) {
                    return Energy.WATER;
                }
                if (nbFireStock > 0 && nbFireStock > nbWaterStock && nbFireStock > nbWindStock && nbFireStock > nbEarthStock) {
                    return Energy.FIRE;
                }
                if (nbWindStock > 0 && nbWindStock > nbFireStock && nbWindStock > nbWaterStock && nbWindStock > nbEarthStock) {
                    return Energy.WIND;
                }

                if (nbEarthStock > 0 && nbEarthStock > nbFireStock && nbEarthStock > nbWaterStock && nbEarthStock > nbWindStock) {
                    return Energy.WIND;
                }
                break;
            }
        }

        return null;
    }
}
