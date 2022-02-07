package core.game;

import core.board.Board;
import core.board.FacadeIA;
import core.game.states.GameStates;
import core.ia.MonteCarlo;
import core.ia.Player;
import core.util.Config;


public class GameController implements Cloneable{
    private Board board;
    private GameStates gameStates;

    public GameController() {
    }

    public GameController(Board board) {
        gameStates = new GameStates(board.getPlayers());
        this.board = board;
    }

    public GameController(Board board, GameStates gameStates) {
        this.board = board;
        this.gameStates = gameStates;
    }

    public void initGame(Player[] playerList, Config config) {
        gameStates = new GameStates(playerList);
        board = new Board(playerList, config);
        for (Player player : playerList) {
            player.setFacadeIA(new FacadeIA(board, player, config));
            if (player instanceof MonteCarlo){
                ((MonteCarlo) player).setBoard(board);
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public GameStates getGameStates() {
        return gameStates;
    }

    public void setGameStates(GameStates gameStates) {
        this.gameStates = gameStates;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        GameController cloneGameController= (GameController) super.clone();
        cloneGameController.setBoard((Board) board.clone());
        cloneGameController.setGameStates((GameStates) gameStates.clone());

        return cloneGameController;
    }
}
