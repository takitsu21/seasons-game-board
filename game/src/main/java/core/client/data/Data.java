package core.client.data;


import core.client.Client;
import core.ia.Player;
import core.ia.PlayerStats;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Data {
    private final JSONArray data = new JSONArray();
    private final HashMap<Player, PlayerStats> players;

    public Data(Map<Player, PlayerStats> players) {
        this.players = (HashMap<Player, PlayerStats>) players;
    }

    public JSONArray getData() {
        return data;
    }

    public void sendPlayersData(Client client) {
        for (Map.Entry<Player, PlayerStats> set : players.entrySet()) {
            data.put(new JSONObject(set.getValue().exportStats()));
        }
        client.sendData(data);
    }
}
