package core.client;

import io.socket.client.IO;
import io.socket.client.Socket;
import org.json.JSONArray;
import org.json.JSONObject;
import stats.DataStorage;
import util.Util;

import java.net.URISyntaxException;
import java.util.logging.Level;

public class Client {
    // Objet de synchro
    final Object waitingConnection = new Object();
    final Object waitingDataSent = new Object();
    final Object waitingDeconnection = new Object();
    final Object waitingGameFinished = new Object();


    private Socket connexion;

    public Client(String urlServer) {

        try {
            connexion = IO.socket(urlServer);

            Util.logger.log(Level.INFO, String.format("[CLIENT] Connexion au server %s", urlServer));
            connexion.on("connect", objects -> {
                Util.logger.log(Level.INFO, "[CLIENT] GameID: " + DataStorage.gameId.getGameId() + " on est connecté ! et on s'identifie ");

                // on s'identifie
                JSONObject id = new JSONObject(DataStorage.gameId);
                connexion.emit("identification", id);
                synchronized (waitingConnection) {
                    waitingConnection.notifyAll();
                }
            });

            connexion.on("disconnect", objects -> {
                Util.logger.log(Level.INFO, "[CLIENT] " + DataStorage.gameId.getGameId() + " est déconnecté");
                connexion.disconnect();
                connexion.close();

                synchronized (waitingDeconnection) {
                    waitingDeconnection.notifyAll();
                }
            });

            connexion.on("data_received", objects -> {
                synchronized (waitingDataSent) {
                    waitingDataSent.notifyAll();
                }
            });

            connexion.on("game_finished", objects -> {
                synchronized (waitingGameFinished) {
                    waitingGameFinished.notifyAll();
                }
            });
        } catch (URISyntaxException e) {
            Util.logger.log(Level.SEVERE, "Creation of client", e);
        }

    }

    public void connect() {
        // on se connecte
        connexion.connect();

        while (true) {
            synchronized (waitingConnection) {
                try {
                    waitingConnection.wait();
                    break;
                } catch (InterruptedException e) {
                    Util.logger.log(Level.SEVERE, "Connexion of client", e);
                    // Restore interrupted state...
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void disconnect() {
        connexion.disconnect();

        Util.logger.log(Level.INFO, "[CLIENT] déconnexion du client " + DataStorage.gameId.getGameId());
        synchronized (waitingDeconnection) {
            while (true) {
                try {
                    waitingDeconnection.wait();
                    break;
                } catch (InterruptedException e) {
                    Util.logger.log(Level.SEVERE, "Disconnection of client", e);
                    // Restore interrupted state...
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void sendData(JSONArray data) {
        connexion.emit("game_data", data);
        synchronized (waitingDataSent) {
            while (true) {
                try {
                    waitingDataSent.wait();
                    break;
                } catch (InterruptedException e) {
                    Util.logger.log(Level.SEVERE, "Disconnection of client", e);
                    // Restore interrupted state...
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void sendGameFinishedEvent() {
//        connexion.emit("game_finished", DataStorage.gameId.getGameId());
        connexion.emit("game_finished", DataStorage.filePath);
        synchronized (waitingGameFinished) {
            try {
                while (true) {
                    waitingGameFinished.wait();
                    break;
                }
            } catch (InterruptedException e) {
                Util.logger.log(Level.SEVERE, "Game finished.", e);
                // Restore interrupted state...
                Thread.currentThread().interrupt();
            }

        }
    }

    public Socket getConnexion() {
        return connexion;
    }
}
