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

public class ChooseCardForInitHandCombosTest {
    private Config config;
    private GameController controller;
    private Board board;
    private Deck deck;
    private Player[] players;
    private ChooseCardForInitHandCombos chooseCardForInitHandCombos;

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
        chooseCardForInitHandCombos = new ChooseCardForInitHandCombos();
        botteTemporelle = deck.findCard(7);
        amsug = deck.findCard(17);

        card3 = deck.findCard(1);
        card4 = deck.findCard(2);
        card5 = deck.findCard(3);
        card6 = deck.findCard(4);
        card7 = deck.findCard(5);
        card8 = deck.findCard(6);
        card9 = deck.findCard(9);
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
        Card[][] hand = chooseCardForInitHandCombos.choose(players[0], cardToChoose, config);
        assertTrue(isCardInsideHand(hand));
    }

    private boolean isCardInsideHand(Card[][] hand) {
        for (int i = 0; i < config.getNbYears(); i++) {
            for (int j = 0; j < config.getNbCardsPerYear(); j++) {
                if (i == 2 && hand[i][j].equals(botteTemporelle)) {
                    return true;
                }
            }
        }
        return false;
    }
}
