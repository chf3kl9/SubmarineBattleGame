package game.gameserverside.serverhandler;

import game.models.GameObject;
import game.models.interfaces.ISubmarine;
import game.models.Settings;
import game.models.Torpedo;

import java.util.List;

public interface IServerHandlerGame {
    void submarineDeath(String playerId);
    void sonarPing(String playerId, List<GameObject> visibleObjects);
    void torpedoConfirm(String playerId, List<Torpedo> torpedoes);
    void moveConfirm(String playerId, ISubmarine submarine);
    void submarineKill(String playerId, int userId);
    void roundStarted(String playerId, ISubmarine submarine);
    void joinFailed(String playerId);
    void joinSuccess(String playerId, int lobbyId, Settings settings);
    void playersInLobbyUpdated(int lobbyId);
    void lobbyClosed(int lobbyId);
}
