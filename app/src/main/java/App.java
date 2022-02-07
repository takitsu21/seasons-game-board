import core.game.TurnBasedGameLoop;
import core.ia.Player;
import core.ia.PlayerFactory;
import core.ia.TypeAIPlayer;
import core.util.Config;
import core.util.ConfigMonteCarlo;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.OptionHandlerFilter;
import server.Server;
import stats.DataStorage;
import util.Util;

import java.util.logging.Level;

public class App {
    private static Config config;
    private static ConfigMonteCarlo configMonteCarlo;

    public static void main(String[] args) throws InterruptedException {
        config = new Config();

        Util.setupLogger();
        if (config.getLogger() == 1) {
            Util.logger.setLevel(Level.ALL);
        }
        if (!parseOptions(args)) {
            return;
        }

        configMonteCarlo = new ConfigMonteCarlo(config);

        Thread server = null;
        if (config.getStats() == 1) {
            server = new Thread(() -> Server.main(null));
            server.start();
        }

        //testMonteCarlo();
        runGame();

        if (config.getStats() == 1) {
            assert server != null;
            server.join();
        }
        System.exit(0);
    }

    public static void runGame() throws InterruptedException {
        Player[] playerList = initPlayer(config.getNbPlayer(), config, configMonteCarlo);

        final TurnBasedGameLoop gameLoop = new TurnBasedGameLoop(playerList, config);


        gameLoop.run();

    }

    public static void testMonteCarlo() throws InterruptedException {
        int nbBranch = 20;
        int depth = 15;
        int nbAction = 20;

        for (int NB = 1; NB < nbBranch; NB++) {
            configMonteCarlo.setNbBranch(NB);
            for (int DE = 1; DE < depth; DE++) {
                configMonteCarlo.setDepth(DE);
                for (int NA = 1; NA < nbAction; NA++) {
                    configMonteCarlo.setNbAction(NA);
                    configMonteCarlo.setFileName("MonteCarlo_"+NB+"-"+DE+"-"+NA);
                    DataStorage.filePath = configMonteCarlo.getFileName();
                    runGame();
                }
            }
        }
    }


    public static boolean parseOptions(String[] args) {
        CmdLineParser parser = new CmdLineParser(config);
        try {
            // parse the arguments.
            parser.parseArgument(args);

            if (checkConfiguration()) {
                Util.logger.config("Parse options [OK]");
                return true;
            }
        } catch (CmdLineException e) {
            Util.logger.log(Level.SEVERE, "Parsing of option", e);
        }

        // if there's a problem in the command line,
        // you'll get this exception. this will report
        // an error message.
        Util.logger.log(Level.SEVERE, "java Game [options...]");
        // print the list of available options
        parser.printUsage(System.err);

        // print option sample. This is useful some time
        Util.logger.log(Level.INFO, String.format(" Example: java Game %s", parser.printExample(OptionHandlerFilter.ALL)));
        Util.logger.log(Level.SEVERE, "Parse options [FAIL]");
        return false;
    }

    protected static boolean checkConfiguration() {
        if (config.getNbPlayer() < 2 || config.getNbPlayer() > 4) {
            Util.logger.log(Level.SEVERE, "Invalid number of player option: less than 2 or more than 4");
            return false;
        } else if ((config.getPrint() != 0 && config.getPrint() != 1) || (config.getStats() != 0 && config.getStats() != 1)) {
            Util.logger.log(Level.SEVERE, "Invalid value of game option: different of 0 and 1");
            return false;
        } else if (config.getNbGame() < 1 || config.getNbYears() < 1 || config.getNbCardsPerYear() < 1) {
            Util.logger.log(Level.SEVERE, "Invalid number of game option: less than 1");
            return false;
        } else return true;
    }

    public static Player[] initPlayer(int nbPlayer, Config config) {
        return initPlayer(nbPlayer, config, new ConfigMonteCarlo(config));
    }

    public static Player[] initPlayer(int nbPlayer, Config config, ConfigMonteCarlo configMonteCarlo) {
        // Initialise players
        Player[] playerList = new Player[nbPlayer];
        Player aI;
        PlayerFactory playerFactory = new PlayerFactory(config, configMonteCarlo);
        for (int i = 0; i < nbPlayer; i++) {
            int iaType = Util.getNextInt(TypeAIPlayer.values().length - 1);
            String name = String.valueOf(i + 1);
            aI = playerFactory.getPlayer(TypeAIPlayer.values()[iaType], name);
            playerList[i] = aI;
        }
//        playerList[0] = playerFactory.getPlayer(TypeAIPlayer.MONTECARLO_COMPOSE, "1");
//        playerList[1] = playerFactory.getPlayer(TypeAIPlayer.PREF_CARD_POINTS, "2");
//        playerList[2] = playerFactory.getPlayer(TypeAIPlayer.RANDOM, "3");
//        playerList[3] = playerFactory.getPlayer(TypeAIPlayer.RANDOM, "4");
        return playerList;
    }

    public static void setConfig(Config config) {
        App.config = config;
    }
}

