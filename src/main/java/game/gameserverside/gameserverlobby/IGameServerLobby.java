package game.gameserverside.gameserverlobby;


public interface IGameServerLobby {

    int getLobbyId();
    String getLobbyName();
    String[] getSessionIds();
    String[] getPlayerNames();
    void fireShot(String playerId);
    void moveSubmarine(String playerId, int rotate, int accelerate);
    void joinLobby(String playerId, int userId, String playerName, String password);
    void startRound(String playerId);
    void leaveLobby(String playerId);
}
