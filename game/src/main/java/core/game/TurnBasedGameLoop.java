package core.game;

import core.board.Board;
import core.cards.Card;
import core.cards.effects.EffectType;
import core.client.data.Data;
import core.dice.Dice;
import core.ia.*;
import core.ia.inventory.Inventory;
import core.util.Config;
import core.util.Renderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurnBasedGameLoop extends GameLoop {
    private final Map<String, Double> winPercentage = new HashMap<>();
    private final Map<String, Double> drawPercentage = new HashMap<>();
    private Player[] playerList;
    private int nbGamePlayed;

    public TurnBasedGameLoop(Player[] playerList, Config config) {
        // Initialisation with nothing
        super(config);
        this.playerList = playerList;
        this.nbGamePlayed = 0;
        for (Player p : playerList) {
            winPercentage.put(p.getName(), 0.0);
            drawPercentage.put(p.getName(), 0.0);
        }
    }

    public TurnBasedGameLoop(GameController controller, Config config) {
        super(config);
        this.controller = controller;
        this.playerList = controller.getBoard().getPlayers();
        this.nbGamePlayed = 0;
        for (Player p : playerList) {
            winPercentage.put(p.getName(), 0.0);
            drawPercentage.put(p.getName(), 0.0);
        }
    }

    protected void processAllGameLoop() {

        // Loop of all games
        while (isGameRunning()) {
            Renderer.add(String.format("La partie numéro %d/%d commence %s", nbGamePlayed + 1, super.config.getNbGame(), System.lineSeparator()));
            // Initialize game
//            controller.setBoard(new Board(playerList, config));
            controller.initGame(playerList, config);

            // Play the game
            initHands();
            processOneGameLoop();
            if (config.getPrint() == 1) {
                Renderer.print(config.getPrint(), config.getNbGame(), nbGamePlayed + 1);
            }
            Renderer.clear();

            // Send stats to the server


            // Verify X games were played
            nbGamePlayed += 1;
            if (nbGamePlayed >= config.getNbGame()) {
                stop();
            }
        }


        // Print the games (or not)
        if (config.getPrint() == 0) {
            Renderer.print(config.getPrint(), config.getNbGame(), nbGamePlayed);
        }
        Renderer.clear();

        if (config.getStats() == 1) {
            // Envoie une instruction au server pour qu'il sauvegarde les statistiques
            client.sendGameFinishedEvent();
        }
        printWinPercentage();

        // Modify to wait the server response
//        System.exit(0);

    }

    public void sendStatsToServer(List<Player> winners) {
        for (Player p : controller.getBoard().getPlayers()) {
            Inventory inventory = controller.getBoard().getInventories().get(p);
            PlayerStats ps = controller.getBoard().getPlayersStats().get(p);
            ps.setPrestigePoints(inventory.getPrestigePoints());
            ps.setFinalInvocationGauge(inventory.getInvocation().getInvocationPoints());
            ps.setPointsCrystal(inventory.getCrystals());
            ps.setAiType(p.getTypeAIPlayer());
            ps.setWinner(winners.contains(p));
        }
    }

//    private void initHands(){
//        // Choose of the card
//        initHands(null);
//    }

    public void initHands(){
        // Choose of the card
//        Renderer.add("--> Choix des cartes par années:");
//        Card[][] hand = new Card[0][];
//        //for (Player player : playerList) {
//        for (int i=0; i<playerList.length; i++){
//            Renderer.add(String.format("Joueur %s:", playerList[i].getName()));
//            if (playerList[i]==playerMonteCarlo) {
//                hand = controller.getBoard().initHand(playerList[i], controller, i);
//            }
//            else{
//                controller.getBoard().initHand(playerList[i], controller, i);
//            }
//            Renderer.add(System.lineSeparator());
//        }
//        return hand;

        // Choose of the card
        Renderer.add("--> Choix des cartes par années:");
        Board b=controller.getBoard();

        for (int i=0; i<playerList.length; i++){
            Renderer.add(String.format("Joueur %s:", playerList[i].getName()));
            controller.getBoard().initHand(playerList[i]);
            Renderer.add(System.lineSeparator());

        }
    }

    public void oneLoop(){
        // Initialize the dices
        controller.getBoard().initDiceSet();

        Renderer.add(String.format("----> Année: %d Saison: %s Case: %d",
                controller.getBoard().getYear().getNbYear(),
                controller.getBoard().getYear().getCurrentSeason(),
                controller.getBoard().getCurrentCursor()));

        // Choice of the dice
        Renderer.add("Choix des dés:");
        playersChoosesDices(controller.getBoard().getRotation());
        Renderer.add(System.lineSeparator());


        // Turns of the players
        turnsOfThePLayers(controller.getBoard().getRotation());

        // Advance on the controller.getBoard()
        controller.getBoard().setCurrentCursor(controller.getBoard().getCurrentCursor() +
                controller.getBoard().getDices().getDice(0).getCurrentFace().getNbAdvance());
        Renderer.add(String.format("Le pion de saison avance de %s %s",
                controller.getBoard().getDices().getDice(0).getCurrentFace().getNbAdvance(),
                System.lineSeparator()));


        // Rotation of the players
        controller.getBoard().setRotation((controller.getBoard().getRotation() + 1) % config.getNbPlayer());
    }

    public void processOneGameLoop() {
        // Turn of the game
        while (controller.getBoard().getYear().updateYearAndSeason(controller.getBoard())) {
            oneLoop();
        }


        checkEndGamePermanentCard();

        // End of the game
        Renderer.add(String.format("La partie est finie %s", System.lineSeparator()));
        List<Player> winner = controller.getBoard().findWinner();
        if (config.getStats() == 1) {
            sendStatsToServer(winner);

            Data data = new Data(controller.getBoard().getPlayersStats());
            data.sendPlayersData(client);
        }
        for (Player p : winner) {
            if (winner.size() > 1) {
                drawPercentage.put(p.getName(), drawPercentage.get(p.getName()) + 1);
            } else {
                winPercentage.put(p.getName(), winPercentage.get(p.getName()) + 1);
            }

        }
        Renderer.add(controller.getBoard().getPlayersStats().toString());
        Renderer.add(String.format("%s ###################################################", System.lineSeparator()));
    }

    /**
     * Check weather or not an end game card effect should be used
     */
    public void checkEndGamePermanentCard() {
        for (Player player : controller.getBoard().getPlayers()) {
            for (Card card : controller.getBoard().getInventories().get(player).getInvocation().getCardsOnBoard()) {
                if (card.hasPermanentEffect() && card.getEffectType() == EffectType.END_GAME) {
                    card.use(controller.getBoard(), player);
                }
            }
        }
    }

    private void playersChoosesDices(int rotation) {

        for (int i = rotation; i < config.getNbPlayer() + rotation; i++) {
            int indexPlayer=i % controller.getBoard().getPlayers().length;
            Player player = controller.getBoard().getPlayers()[indexPlayer];

            if (player instanceof MonteCarlo && !controller.getBoard().equals(((MonteCarlo) player).getBoard()) ) {
                ((MonteCarlo) player).setBoard(controller.getBoard());
            }

            Inventory inventory = controller.getBoard().getInventories().get(player);

            Dice chosenDice = player.chooseDice();

            inventory.setCurrentDice(chosenDice);
            controller.getBoard().getDices().removeDice(chosenDice);
            Renderer.add(String.format("Joueur %s a pris : %s ", player.getName(), chosenDice.toString()));

        }
    }

    public void turnsOfThePLayers(int rotation) {

        for (int i = rotation; i < config.getNbPlayer() + rotation; i++) {

            Player player = controller.getBoard().getPlayers()[i % controller.getBoard().getPlayers().length];

            PlayerTurnLoop playerTurnLoop;
            // Let the player choose his actions
            if (player instanceof MonteCarlo && ((MonteCarlo) player).isUseMonteCarlo()) {
                playerTurnLoop = new MonteCarloTurnLoop(player, controller);

            } else {
                playerTurnLoop = new PlayerTurnLoop(player, controller);
            }
            playerTurnLoop.processPlayerTurnLoop();
            Renderer.add(System.lineSeparator());

        }
        useEndOfTurnPermanentCard();
        Renderer.add("Fin du tour de jeu ");
    }

    /**
     * Reset activated cards
     */
    public void resetActivatedCard() {
        for (Player p : playerList) {
            for (Card card : getController().getBoard().getInventories().get(p).getInvocation().getCardsOnBoard()) {
                card.setActivated(false);
            }
        }
    }

    /**
     * Use every permanent card effect that are triggered at the end of a turn
     */
    public void useEndOfTurnPermanentCard() {
        for (Player p : controller.getBoard().getPlayers()) {
            Inventory inventory = controller.getBoard().getInventories().get(p);
            for (Card card : inventory.getInvocation().getCardsOnBoard()) {
                if (card.hasPermanentEffect()
                        && card.getEffectType() == EffectType.END_TURN) {
                    card.use(controller.getBoard(), p);
                }
            }
        }
        resetActivatedCard();
    }

    private void printWinPercentage() {
        for (Map.Entry<String, Double> entry : winPercentage.entrySet()) {
            System.out.printf("Joueur %s à gagné %d parties avec un taux de victoire de %.2f%% et un taux de match nul de %.2f%%\n", entry.getKey(),
                    entry.getValue().intValue(),
                    (entry.getValue() / (double) nbGamePlayed) * 100,
                    (drawPercentage.get(entry.getKey()) / (double) nbGamePlayed) * 100);
        }
    }

}
