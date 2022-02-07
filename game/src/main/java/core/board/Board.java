package core.board;

import core.cards.Card;
import core.cards.Deck;
import core.dice.DiceSet;
import core.ia.Player;
import core.ia.PlayerStats;
import core.ia.inventory.Hand;
import core.ia.inventory.Inventory;
import core.util.Config;
import core.util.Renderer;

import java.util.*;


public class Board implements Cloneable {
    private Year year;
    private Map<Player, Inventory> inventories = new HashMap<>();
    private Map<Player, PlayerStats> playersStats = new HashMap<>();
    private Deck deck;
    private Player[] players;
    private DiceSet dices;
    private int currentCursor = 0;
    private Config config;
    private int rotation=0;




    public Board(Config config) {
        this.year = new Year(config.getNbYears());
        this.deck = new Deck();
        this.config = config;
    }

    public Board(Player[] playerList, Config config) {
        this.year = new Year(config.getNbYears());
        this.deck = new Deck();
        this.players = playerList;
        this.config = config;
        initPLayersInventory();
    }

    public Board(Year year, Map inventories, Map playersStats, Deck deck, Player[] players, DiceSet dices, Config config, int currentCursor) {
        this.year = year;
        this.inventories = inventories;
        this.playersStats = playersStats;
        this.deck = deck;
        this.players = players;
        this.dices = dices;
        this.currentCursor = currentCursor;
        this.config = config;
    }

    public void initPLayersInventory() {
        for (Player player : players) {
            Inventory inventory = new Inventory();
            inventories.put(player, inventory);
            playersStats.put(player, new PlayerStats(player));
        }
    }

//    public Card[][] initHand(Player player, GameController controller, int indexPlayer) {
//        Inventory inventory = (Inventory) getInventories().values().toArray()[indexPlayer];
//
////        if (player.getMonteCarlo().isUseMonteCarlo()){
////            player.getMonteCarlo().monteCarloInitHand(controller, indexPlayer);
////        }
//        Card[][] hand = player.chooseCardForInitHand(
//                deck.sampleCard(config.getNbYears() * config.getNbCardsPerYear()));
//        inventory.setHand(new Hand(hand));
//        for (int i = 1; i <= 3; i++) {
//            Renderer.add("- année " + i + ": " + hand[i - 1][0].getName() + " / " + hand[i - 1][1].getName() + " / " + hand[i - 1][2].getName());
//        }
//        return hand;
//    }

    public Card[][] initHand(Player player) {
        Inventory inventory = getInventories().get(player);
        Card[][] hand = player.chooseCardForInitHand(
                deck.sampleCard(config.getNbYears() * config.getNbCardsPerYear()));
        inventory.setHand(new Hand(hand));
        for (int i = 1; i <= config.getNbYears(); i++) {
            Renderer.add("- année " + i + ": " + hand[i - 1][0].getName() + " / " + hand[i - 1][1].getName() + " / " + hand[i - 1][2].getName());
        }
        return hand;
    }

//    public void initHand(Player player) {
//        Inventory inventory = getInventories().get(player);
//        Card[][] hand = player.chooseCardForInitHand(
//                deck.sampleCard(config.getNbYears() * config.getNbCardsPerYear()));
//        inventory.setHand(new Hand(hand));
//        for (int i = 1; i <= 3; i++) {
//            Renderer.add("- année " + i + ": " + hand[i - 1][0].getName() + " / " + hand[i - 1][1].getName() + " / " + hand[i - 1][2].getName());
//        }
//
//    }

    public Card findCard(String name, List<Card> deck) {
        for (Card card : deck) {
            if (card.getName().equalsIgnoreCase(name)) {
                return card;
            }
        }
        return null;
    }

    public void initDiceSet() {
        dices = new DiceSet(config.getNbPlayer(), year);
    }

    public List<Player> findWinner() {
        // Calculate prestige points
        for (Player player : players) {
            PlayerStats ps = getPlayersStats().get(player);
            ps.setFinalCardGameInGame(ps.getFinalCardGameInGame()
                    + inventories.get(player).getInvocation().getCardsOnBoard().size());
            ps.setPenaltyCard(inventories.get(player).getPenaltyCardPoints());
            ps.setPenaltyBonusStats(inventories.get(player).getPenaltyBonusPoints());
            ps.setPointsCard(inventories.get(player).getPrestigePointsCard());
            inventories.get(player).calculatePrestigePoint();
            ps.setPrestigePoints(inventories.get(player).getPrestigePoints());
            Renderer.add("Joueur " + player.getName() + " a " + inventories.get(player).getPrestigePoints() + " points de prestiges");
        }

        // Find winner
        ArrayList<Player> winner = new ArrayList<>();
        winner.add(players[0]);

        int maxPrestigePoint = inventories.get(players[0]).getPrestigePoints();
        for (int i = 1; i < players.length; i++) {
            if (inventories.get(players[i]).getPrestigePoints() > maxPrestigePoint) {
                maxPrestigePoint = inventories.get(players[i]).getPrestigePoints();
                winner.clear();
                winner.add(players[i]);
            } else if (inventories.get(players[i]).getPrestigePoints() == maxPrestigePoint) {
                winner.add(players[i]);
            }
        }
        Renderer.add("\n");
        // Print winner
        StringBuilder sb = new StringBuilder();
        if (winner.size() == 1) {
            sb.append("le gagnant est le joueur ");
        } else {
            sb.append("les gagnants sont les joueurs ");
        }
        for (Player win : winner) {
            sb.append(" ");
            sb.append(win.getName());
        }
        sb.append(" !");
        Renderer.add(sb.toString());


        return winner;
    }


    public DiceSet getDices() {
        return dices;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Year getYear() {
        return year;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Map<Player, PlayerStats> getPlayersStats() {
        return playersStats;
    }

    public int getCurrentCursor() {
        return currentCursor;
    }

    public void setCurrentCursor(int currentCursor) {
        this.currentCursor = currentCursor;
    }

    public Map<Player, Inventory> getInventories() {
        return inventories;
    }

    public void setDices(DiceSet dices) {
        this.dices = dices;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public void setInventories(HashMap<Player, Inventory> inventories) {
        this.inventories = inventories;
    }

    public void setPlayersStats(HashMap<Player, PlayerStats> playersStats) {
        this.playersStats = playersStats;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        Board cloneBoard= (Board) super.clone();
        cloneBoard.setYear((Year) year.clone());
        cloneBoard.setDeck((Deck) deck.clone());
        if (dices!=null) {
            cloneBoard.setDices((DiceSet) dices.clone());
        }
        HashMap<Player, Inventory> cloneInventories = new HashMap<>();
        HashMap<Player, PlayerStats> clonePlayersStats = new HashMap<>();
        Player[] clonePlayers= new Player[players.length];
        for (int i =0; i<players.length;i++){
            Player p=(Player) players[i].clone();
            p.getFacadeIA().setBoard(cloneBoard);
            clonePlayers[i]= p;
            cloneInventories.put(p, (Inventory) inventories.get(players[i]).clone());
            PlayerStats clonePlayerStats =(PlayerStats) playersStats.get(players[i]).clone();
            clonePlayersStats.put(p, clonePlayerStats);
            clonePlayerStats.setPlayer(p);
            p.getFacadeIA().setPlayer(p);
            clonePlayerStats.getPlayer().getFacadeIA().setBoard(cloneBoard);

        }
        cloneBoard.setPlayers(clonePlayers);
        cloneBoard.setInventories(cloneInventories);
        cloneBoard.setPlayersStats(clonePlayersStats);
        return cloneBoard;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }
}
