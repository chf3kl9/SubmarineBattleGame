package game.gameserverside.serverhandler;

import game.models.Settings;

public interface IServerHandlerMessenger {
    void fireShot(String playerId, int lobbyId);
    void moveSubmarine(String playerId, int lobbyId, int rotate, int accelerate);
    void joinLobby(String playerId, int userId, String playerName, String lobby, String password);
    void startRound(String playerId, int lobbyId);
    void openLobby(String playerId, int userId, String playerName, String lobby, String password, Settings settings);
    void lobbyRefresh(String playerId);
    void lobbyLeave(String playerId, int lobbyId);
    void validationFinished(String playerId, boolean success);
    boolean validate(int userId, String validationKey);
}
