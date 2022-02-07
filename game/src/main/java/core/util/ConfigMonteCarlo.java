package core.util;

public class ConfigMonteCarlo {
    private int nbBranch;
    private int depth;
    private int nbAction;
    private String fillName;

    public ConfigMonteCarlo(Config config) {
        this.nbBranch = config.getMonteCarloNbBranch();
        this.depth = config.getMonteCarloDepth();
        this.nbAction = config.getMonteCarloNbAction();
    }

    public int getNbBranch(){
        return nbBranch;
    }

    public void setNbBranch(int nbBranch) {
        this.nbBranch = nbBranch;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getNbAction() {
        return nbAction;
    }

    public void setNbAction(int nbAction) {
        this.nbAction = nbAction;
    }

    public String getFileName() {
        return fillName;
    }

    public void setFileName(String fillName) {
        this.fillName = fillName;
    }
}
