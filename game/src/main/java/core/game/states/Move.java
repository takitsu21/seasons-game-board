package core.game.states;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Move implements Serializable {
    private final List<Action> moves = new ArrayList<>();

    public void addAction(Action action) {
        moves.add(action);
    }

    public List<Action> getMoves() {
        return moves;
    }

    @Override
    public String toString() {
        return "Move{" +
                "moves=" + moves +
                "}\n";
    }
}
