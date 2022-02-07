package core.ia.strategy.choose.card_for_init_hand;

import core.board.Board;
import core.cards.Card;
import core.cards.Deck;
import core.game.App;
import core.game.GameController;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChooseCardForInitHandPrefActivateTest {
    private Config config;
    private GameController controller;
    private Board board;
    private Deck deck;
    private Player[] players;
    private ChooseCardForInitHandPrefActivate chooseCardForInitHandPrefActivate;

    private Card botteTemporelle;
    private Card amsug;
    private Card card3;
    private Card card4;
    private Card card5;
    private Card card6;
    private Card card7;
    private Card card8;
    private Card card9;

    @BeforeEach
    public void setup() {
        controller = new GameController();
        config = new Config();

        players = App.initPlayer(1, config);
        PlayerFactory playerFactory=new PlayerFactory(config);
        players[0]=playerFactory.getPlayer(TypeAIPlayer.RANDOM, "IA");
        controller.initGame(players, config);

        board = controller.getBoard();
        deck = new Deck();
        chooseCardForInitHandPrefActivate = new ChooseCardForInitHandPrefActivate();
        botteTemporelle = deck.findCard(7);
        amsug = deck.findCard(17);

        card3 = deck.findCard(1);
        card4 = deck.findCard(2);
        card5 = deck.findCard(3);
        card6 = deck.findCard(24);
        card7 = deck.findCard(4);
        card8 = deck.findCard(25);
        card9 = deck.findCard(23);
    }

    @Test
    public void initHand() {
        List<Card> cardToChoose = new ArrayList<>(List.of(
                botteTemporelle,
                amsug,
                card3,
                card4,
                card5,
                card6,
                card7,
                card8,
                card9));
        Card[][] hand = chooseCardForInitHandPrefActivate.choose(players[0], cardToChoose, config);
        assertTrue(isSortedForQuickActivation(hand));
    }

    private boolean isSortedForQuickActivation(Card[][] hand) {
        int nbActivateCard = 0;
        for (int i = 0; i < config.getNbYears(); i++) {
            for (int j = 0; j < config.getNbCardsPerYear(); j++) {
                if (i == 0 && hand[i][j].isActivable()) { // si année 1 et la carte est activable
                    nbActivateCard++;
                }
            }
        }
        return nbActivateCard == 3;
    }

}
