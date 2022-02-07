package id;

import java.util.UUID;

public class GameIdentificateur {
    private final UUID gameId;

    public GameIdentificateur() {
        this.gameId = UUID.randomUUID();
    }

    public UUID getGameId() {
        return gameId;
    }

    @Override
    public String toString() {
        return gameId.toString();
    }
}
