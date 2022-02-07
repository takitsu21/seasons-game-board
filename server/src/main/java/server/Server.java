package server;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import id.GameIdentificateur;
import org.json.JSONObject;
import stats.DataStorage;
import stats.Stats;
import util.Util;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;

/**
 * attend une connexion, on envoie une question puis on attend une réponse, jusqu'à la découverte de la bonne réponse
 * le core.client s'identifie (som, niveau)
 */
public class Server {

    final Object waitingConnection = new Object();
    final Object waitingDataSent = new Object();
    final Object waitingGameFinished = new Object();


    SocketIOServer theServer;
    GameIdentificateur gameId;


    public Server(Configuration config) {
        // creation du server
        theServer = new SocketIOServer(config);

        // on accepte une connexion
        theServer.addConnectListener(socketIOClient -> Util.logger.info("[SERVER] connexion de " + socketIOClient.getRemoteAddress()));
        theServer.addDisconnectListener(socketIOClient -> {
            Util.logger.info("[SERVER] deconnexion de " + socketIOClient.getRemoteAddress());
            synchronized (waitingConnection) {
                waitingConnection.notifyAll();
            }
        });

        // réception d'une identification
        theServer.addEventListener("identification", GameIdentificateur.class, new DataListener<GameIdentificateur>() {
            @Override
            public void onData(SocketIOClient socketIOClient, GameIdentificateur gameIdentificateur, AckRequest ackRequest) throws Exception {
                gameId = gameIdentificateur;
                Util.logger.info("[SERVER] Identification " + gameIdentificateur.getGameId());
            }
        });

        theServer.addEventListener("game_data", Stats[].class, (socketIOClient, data, ackRequest) -> {
            Util.logger.info("[SERVER] data received");
            socketIOClient.sendEvent("data_received");
            DataStorage.appendStats(data);


            synchronized (waitingDataSent) {
                waitingDataSent.notifyAll();
            }
        });

        theServer.addEventListener("game_finished", String.class, (socketIOClient, data, ackRequest) -> {
            Util.logger.info("[SERVER] Saving data...");
            DataStorage.saveStats(data);
            DataStorage.dataGame = new JSONObject();
            DataStorage.gameNumber = 1;
            socketIOClient.sendEvent("game_finished");
            synchronized (waitingGameFinished) {
                waitingGameFinished.notifyAll();
            }
        });
    }

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            Util.logger.log(Level.SEVERE, "context", e);
        }

        Configuration config = new Configuration();
        config.setHostname("127.0.0.1");
        config.setPort(10101);


        Server server = new Server(config);
        server.start();
    }

    private void start() {

        theServer.start();

        Util.logger.info("[SERVER] En attente de connexion...");
        synchronized (waitingConnection) {
            try {
                waitingConnection.wait();
            } catch (InterruptedException e) {
                Util.logger.log(Level.SEVERE, "Starting server", e);
                Thread.currentThread().interrupt();
            }
        }

    }
}
