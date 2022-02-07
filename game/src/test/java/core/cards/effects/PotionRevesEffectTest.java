package core.cards.effects;

import core.board.Year;
import core.board.enums.Energy;
import core.cards.Card;
import core.cards.Deck;
import core.cards.EffectFrequency;
import core.dice.Dice;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.ia.strategy.choose.card_to_summon.StrategyChooseCardToSummon;
import core.ia.strategy.choose.card_to_summon_for_free.StrategyChooseCardToSummonForFree;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PotionRevesEffectTest {
    private Deck deck;
    private Player p1;
    private Inventory inventory1;
    private Card potionReves;
    private Card card;

    private GameController controller;

    @BeforeEach
    public void setUp() {
        Config config = new Config();
        controller = new GameController();
        Player[] playerList = App.initPlayer(2, config);
        p1 = playerList[0];

        controller.initGame(playerList, config);
        controller.getBoard().initPLayersInventory();
        for (Player player : playerList) {
            controller.getBoard().initHand(player);
        }
        controller.getBoard().getInventories().get(p1).setCurrentDice(new Dice(0, new Year(config.getNbYears())));
        inventory1 = controller.getBoard().getInventories().get(p1);


        deck = new Deck();
        potionReves = deck.findCard(24);

        card = Mockito.mock(Card.class);
        when(card.getEffectFrequency()).thenReturn(EffectFrequency.EACH_TURN);

        StrategyChooseCardToSummonForFree chooseCardToSummonForFree = Mockito.mock(StrategyChooseCardToSummonForFree.class);

        when(chooseCardToSummonForFree.doChoose(p1)).thenReturn(card);
        p1.setChooseCardToSummonForFree(chooseCardToSummonForFree);
    }

    @Test
    void useTest() {
        inventory1.getHand().setCardsInHand(new ArrayList<>());
        inventory1.getHand().addCard(potionReves);
        inventory1.getHand().addCard(card);
        assertEquals(2, inventory1.getCardsInHand().size());

        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);

        inventory1.getEnergyStock().addEnergy(Energy.WIND, null);
        inventory1.getInvocation().setInvocationPoints(2);

        assertTrue(inventory1.summonCard(potionReves, controller.getBoard()));
        assertEquals(1, inventory1.getCardsInHand().size());
        potionReves.use(controller.getBoard(), p1);
        assertEquals(0, inventory1.getCardsInHand().size());
    }

    @Test
    void isMagic() {
        assertTrue(potionReves.isMagic());
    }

    @Test
    void isActivable() {
        assertTrue(potionReves.isActivable());
    }

    @Test
    void isActivatedTest() {
        assertFalse(potionReves.isActivated());
    }

    @Test
    void effectTypeTest() {
        assertEquals(EffectType.NONE, potionReves.getEffectType());
    }
}
