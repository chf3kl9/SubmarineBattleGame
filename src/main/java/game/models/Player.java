package game.models;

public class Player {
    String sessionId;
    int userId;
    String playerName;

    public Player(String sessionId, int userId, String playerName) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.playerName = playerName;
    }

    public int getUserId() {
        return userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getPlayerName(){return playerName; }
}
