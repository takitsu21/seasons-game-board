package core.ia;

import core.board.enums.Energy;
import core.cards.Card;
import core.dice.Dice;
import core.ia.inventory.BonusType;

import java.util.ArrayList;
import java.util.List;

public interface IPlayer {

    ////////// Player related
    EnumPlayerAction choosePlayerAction();

    Player choosePlayer();

    ///////// Energy
    Energy chooseEnergyToThrow();

    Energy chooseEnergyToGet();

    Energy chooseEnergyToCrystalize();

    List<Energy> chooseSimilarEnergiesToDelete(int nb);

    ///// Dice
    Dice chooseDice();

    ///// Bonus
    boolean chooseUseBonusCard();

    BonusType chooseBonus();

    // Card
    Card chooseCardToActivate();

    Card chooseCardBetweenMultipleToGet(List<Card> cards);

    boolean chooseToKeepDrawnCard(Card card);

    Card chooseCardToSummon();

    Card[][] chooseCardForInitHand(List<Card> deck);

    Card chooseCardComeBackInHand();


}
