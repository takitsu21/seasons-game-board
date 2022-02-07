package core.game;


import core.client.Client;
import core.util.Config;

public abstract class GameLoop {

    protected GameController controller;
    protected volatile GameStatus status;
    protected Client client;
    protected Config config;

    /**
     * Initialize game status to be stopped.
     *
     * @param config
     */

    protected GameLoop(Config config) {
        controller = new GameController();
        status = GameStatus.STOPPED;
        this.config = config;
    }


    /**
     * Run game loop.
     */
    public void run() {
        status = GameStatus.RUNNING;
        if (config.getStats() == 1) {
            client = new Client("http://127.0.0.1:10101");
            client.connect();
        }
        processAllGameLoop();
        if (config.getStats() == 1) {
            client.disconnect();
        }
//        System.exit(0);
    }

    /**
     * Stop game loop.
     */
    public void stop() {
        status = GameStatus.STOPPED;
    }

    /**
     * execute game loop logic.
     */
    protected abstract void processAllGameLoop();

    /**
     * Check if game is running or not.
     *
     * @return {@code true} if the game is running.
     */
    public boolean isGameRunning() {
        return status == GameStatus.RUNNING;
    }

    public GameController getController() {
        return controller;
    }
}
