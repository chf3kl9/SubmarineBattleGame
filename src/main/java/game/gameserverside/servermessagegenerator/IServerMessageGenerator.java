package game.gameserverside.servermessagegenerator;

import game.models.GameObject;
import game.models.interfaces.ISubmarine;
import game.models.Settings;
import game.models.Torpedo;

import java.util.List;

public interface IServerMessageGenerator {
    void sendKill(String playerId);
    void sendMoveConfirm(String playerId, ISubmarine submarine);
    void sendSonar(String playerId, List<GameObject> visibleObjects);
    void sendDeath(String playerId);
    void sendFiredShot(String playerId, List<Torpedo> torpedo);
    void sendIncorrectLogin(String playerId);
    void sendLobbyClosed(String[] playerIds);
    void sendLobbyJoined(String playerId, int lobbyId, Settings settings);
    void sendRoundStarted(String playerId, ISubmarine submarine);
    void sendLobbies(String playerId, List<String> lobbies);
    void sendLobbyCreated(String playerId, int lobbyId);
    void sendValidation(String playerId, boolean success);
    void sendPlayersInLobbyUpdated(String[] sessionIds, String[] playerNames);
}
