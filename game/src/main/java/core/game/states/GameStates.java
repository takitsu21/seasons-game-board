package core.game.states;

import core.ia.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameStates implements Serializable, Cloneable {
    private HashMap<Player, List<Move>> playersMoves = new HashMap<>();
    private Player[] playerList;

    public GameStates(Player[] playerList) {
        this.playerList = playerList;
        for (Player p : playerList) {
            playersMoves.put(p, new ArrayList<>());
        }

    }

    public boolean store(File f) {
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(playersMoves);
            oos.close();
            fos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean load(File f) {
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.playersMoves = (HashMap<Player, List<Move>>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public HashMap<Player, List<Move>> getPlayersMoves() {
        return playersMoves;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
