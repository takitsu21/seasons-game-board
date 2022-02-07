package core.cards.effects;

import core.board.Board;
import core.board.enums.Energy;
import core.cards.AbstractCard;
import core.cards.Card;
import core.cards.EffectFrequency;
import core.ia.Player;
import core.ia.inventory.Inventory;
import core.util.Renderer;

import java.util.ArrayList;
import java.util.List;

public class AmsugLongcoupEffect extends AbstractCard {

    public AmsugLongcoupEffect(Integer id, Boolean players, Integer crystalCost, List<Energy> energyCost, EffectFrequency effectFrequency, String name, Integer prestigePointValue) {
        super(id, players, crystalCost, energyCost, effectFrequency, name, prestigePointValue);
    }

    @Override
    public void use(Board board, Player player) {
        Renderer.add(String.format("- Utilise %s", getName()));

//        Au moment où vous mettez en jeu Amsug Longcoup, chaque
//        joueur (vous y compris) renvoie dans sa main l’une de ses
//        cartes objet magique déjà en jeu.
//        Un joueur ne possédant pas d’objet magique invoqué n’est pas
//        concerné par l’effet d’Amsug Longcoup.

        for (Player currentPlayer : board.getPlayers()) {
            Inventory inventory = board.getInventories().get(currentPlayer);
            ArrayList<Card> playersBoard = (ArrayList<Card>) inventory.getInvocation().getCardsOnBoard();

            Card choosenCard = currentPlayer.chooseCardComeBackInHand();
            if (choosenCard != null) {

                inventory.retireCardFromBoard(choosenCard);
                Renderer.add(String.format("-- %s retire %s de son plateau", currentPlayer.getName(), choosenCard.getName()));

                if (choosenCard.getId() == 18 && !hasSecondGrimoire(playersBoard)) { //si la carte retirée est un grimoire et s'il n'y a pas un second grimoire
                    inventory.getEnergyStock().modifyNbMaxEnergy(7); //on réduit sa réserve à 7 énergies max
                    Renderer.add("--- Sa réserve d'énergie a été réduite de 3");
                }
            } else {
                Renderer.add(String.format("-- %s n'a pas d'objet magique à retirer de son plateau", currentPlayer.getName()));
            }
        }
    }

    private boolean hasSecondGrimoire(ArrayList<Card> cardsOnBoard) {
        for (Card card : cardsOnBoard) {
            if (card.getId() == 18) { //on vérifie s'il n'a pas un autre grimoire
                return true;
            }
        }
        return false;
    }
}
