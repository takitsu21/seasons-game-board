package core.util;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;

public class Config {

    @Argument
    private static List<String> arguments = new ArrayList<>();
    @Option(name = "-nbPlayer", usage = "Number of player")
    private int nbPlayer = 4;
    @Option(name = "-nbGame", usage = "Number of games (> 0)")
    private int nbGame = 1;
    @Option(name = "-print", usage = "Print details of games")
    private int print = 1;
    @Option(name = "-years", usage = "Number of year per game")
    private int nbYears = 3;
    @Option(name = "-nbCaseByYear", usage = "Number of Case by year")
    private int nbCaseBySeason = 3;
    @Option(name = "-nbCaseBySeason", usage = "Number of cards per year")
    private int nbCardsPerYear = 3;
    @Option(name = "-stats", usage = "Collect statistics about games")
    private int stats = 0;
    @Option(name = "-MCNbBranch", usage = "Number of branch for Monte Carlo")
    private int monteCarloNbBranch = 6;
    @Option(name = "-MCDepth", usage = "Depth for Monte Carlo")
    private int monteCarloDepth = 12;
    @Option(name = "-MCNbAction", usage = "Number of action for Monte Carlo")
    private int monteCarloNbAction = 17;
    @Option(name = "-logger", usage = "Game logger")
    private int logger = 0;

    public int getNbPlayer() {
        return nbPlayer;
    }

    public int getNbGame() {
        return nbGame;
    }

    public int getPrint() {
        return print;
    }

    public int getNbYears() {
        return nbYears;
    }

    public int getNbCaseBySeason(){return nbCaseBySeason;}

    public int getNbCardsPerYear() {
        return nbCardsPerYear;
    }

    public int getStats() {
        return stats;
    }

    public int getMonteCarloNbBranch() {
        return monteCarloNbBranch;
    }

    public int getMonteCarloDepth() {
        return monteCarloDepth;
    }

    public int getMonteCarloNbAction() {
        return monteCarloNbAction;
    }

    public int getLogger() {
        return logger;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
