package core.ia;

import core.ia.strategy.choose.Context;
import core.ia.strategy.choose.bonus.ChooseBonusFirst;
import core.ia.strategy.choose.bonus.ChooseBonusPrefCrystallize;
import core.ia.strategy.choose.bonus.ChooseBonusRandom;
import core.ia.strategy.choose.bonus.StrategyChooseBonus;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerFactoryTest {
    PlayerFactory playerFactory;
    Config config = new Config();

    @BeforeEach
    void setUp() {
        playerFactory = new PlayerFactory(config);
    }

    @Test
    void getPlayerTest() {
        Player p1 = playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA1");
        Player p2 = playerFactory.getPlayer(TypeAIPlayer.CHOOSE_FIRST, "IA2");
        Player p3 = playerFactory.getPlayer(TypeAIPlayer.PREF_CRYSTALLIZE, "IA3");

        assertEquals("IA1", p1.name);

        StrategyChooseBonus strategyChooseRandom = new ChooseBonusRandom(Context.everyTime, null);
        StrategyChooseBonus strategyChooseFirst = new ChooseBonusFirst(Context.everyTime, new ChooseBonusRandom(Context.everyTime, null));
        StrategyChooseBonus strategyChooseCrystallizePref = new ChooseBonusPrefCrystallize(Context.everyTime, new ChooseBonusRandom(Context.everyTime, null));

        assertEquals(getClass(strategyChooseRandom), getClass(p1.chooseBonus));
        assertEquals(getClass(strategyChooseFirst), getClass(p2.chooseBonus));
        assertEquals(getClass(strategyChooseCrystallizePref), getClass(p3.chooseBonus));
    }

    private Class<? extends StrategyChooseBonus> getClass(StrategyChooseBonus strategyChooseBonus) {
        return strategyChooseBonus.getClass();
    }
}