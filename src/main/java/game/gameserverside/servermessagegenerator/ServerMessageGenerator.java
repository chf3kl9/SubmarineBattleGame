package game.gameserverside.servermessagegenerator;

import game.models.GameObject;
import game.models.interfaces.ISubmarine;
import game.models.Settings;
import game.gameserverside.serverwebsockets.IServerWebsocket;
import game.models.Submarine;
import game.models.Torpedo;
import game.shared.messages.inlobbymessages.ingamemessages.*;
import game.shared.messages.inlobbymessages.MessageLobbyClosed;
import game.shared.messages.inlobbymessages.MessageLobbyJoined;
import game.shared.messages.inlobbymessages.MessageRoundStarted;
import game.shared.messages.lobbymessages.MessageLobbies;
import game.shared.messages.lobbymessages.MessageLobbyCreated;
import game.shared.messages.lobbymessages.MessageLobbyJoinFailed;
import game.shared.messages.lobbymessages.MessagePlayersInLobbyUpdated;
import game.shared.messages.validationmessages.MessageValidationFinish;

import java.util.List;

public class ServerMessageGenerator implements IServerMessageGenerator {

    private IServerWebsocket serverSocket;

    public ServerMessageGenerator(IServerWebsocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void sendKill(String playerId) {
        MessageKill msg = new MessageKill();
        serverSocket.sendTo(playerId, msg);
    }

    public void sendMoveConfirm(String playerId, ISubmarine submarine) {
        MessageMovedSubmarine msg = new MessageMovedSubmarine((Submarine)submarine);
        serverSocket.sendTo(playerId, msg);
    }

    public void sendSonar(String playerId, List<GameObject> visibleObjects) {
        MessageSonar msg = new MessageSonar(visibleObjects);
        serverSocket.sendTo(playerId, msg);
    }

    public void sendDeath(String playerId) {
        MessageDeath msg = new MessageDeath();
        serverSocket.sendTo(playerId, msg);
    }

    public void sendFiredShot(String playerId, List<Torpedo> torpedoes) {
        MessageFiredTorpedo msg = new MessageFiredTorpedo(torpedoes);
        serverSocket.sendTo(playerId, msg);
    }

    public void sendIncorrectLogin(String playerId) {
        MessageLobbyJoinFailed msg = new MessageLobbyJoinFailed();
        serverSocket.sendTo(playerId, msg);
    }

    public void sendLobbyClosed(String[] playerIds) {
        MessageLobbyClosed msg = new MessageLobbyClosed();
        serverSocket.sendToGroup(playerIds, msg);
    }

    public void sendLobbyJoined(String playerId, int lobbyId, Settings settings) {
        MessageLobbyJoined msg = new MessageLobbyJoined(lobbyId, settings);
        serverSocket.sendTo(playerId, msg);
    }

    public void sendRoundStarted(String playerId, ISubmarine submarine) {
        MessageRoundStarted msg = new MessageRoundStarted((Submarine)submarine);
        serverSocket.sendTo(playerId, msg);
    }

    public void sendLobbies(String playerId, List<String> lobbies) {
        MessageLobbies msg = new MessageLobbies(lobbies);
        serverSocket.sendTo(playerId, msg);
    }

    public void sendLobbyCreated(String playerId, int lobbyId) {
        MessageLobbyCreated msg = new MessageLobbyCreated(lobbyId);
        serverSocket.sendTo(playerId, msg);
    }

    public void sendValidation(String playerId, boolean success){
        MessageValidationFinish msg = new MessageValidationFinish(success);
        serverSocket.sendTo(playerId, msg);
    }

    public void sendPlayersInLobbyUpdated(String[] sessionIds, String[] playerNames){
        MessagePlayersInLobbyUpdated msg = new MessagePlayersInLobbyUpdated(playerNames);
        serverSocket.sendToGroup(sessionIds, msg);
    }
}
