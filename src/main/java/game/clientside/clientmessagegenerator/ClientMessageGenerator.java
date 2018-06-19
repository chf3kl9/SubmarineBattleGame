package game.clientside.clientmessagegenerator;

import game.clientside.clientwebsockets.IClientWebsocket;
import game.models.Settings;
import game.shared.messages.inlobbymessages.ingamemessages.MessageFire;
import game.shared.messages.inlobbymessages.ingamemessages.MessageMove;
import game.shared.messages.inlobbymessages.MessageLobbyClose;
import game.shared.messages.inlobbymessages.MessageLobbyLeave;
import game.shared.messages.inlobbymessages.MessageRoundStart;
import game.shared.messages.lobbymessages.MessageLobbyCreate;
import game.shared.messages.lobbymessages.MessageLobbyJoin;
import game.shared.messages.lobbymessages.MessageLobbyRefresh;
import game.shared.messages.validationmessages.MessageValidate;

public class ClientMessageGenerator implements IClientMessageGenerator {

    private IClientWebsocket clientSocket;

    public ClientMessageGenerator(IClientWebsocket clientSocket){
        this.clientSocket = clientSocket;
    }

    public void sendFireShot(int lobbyId) {
        MessageFire msg = new MessageFire(lobbyId);
        clientSocket.send(msg);
    }

    public void sendMove(int lobbyId, int rotate, int accelerate) {
        MessageMove msg = new MessageMove(lobbyId, rotate, accelerate);
        clientSocket.send(msg);
    }

    public void sendJoinLobby(String lobby, String playerName, String password, int userId) {
        MessageLobbyJoin msg = new MessageLobbyJoin(lobby, playerName, password, userId);
        clientSocket.send(msg);
    }

    public void sendStartRound(int lobbyId) {
        MessageRoundStart msg = new MessageRoundStart(lobbyId);
        clientSocket.send(msg);
    }

    public void sendOpenLobby(String lobby, String playerName, String password, Settings settings, int userId) {
        MessageLobbyCreate msg = new MessageLobbyCreate(lobby, playerName, password, settings, userId);
        clientSocket.send(msg);
    }

    public void sendLobbyRefresh() {
        MessageLobbyRefresh msg = new MessageLobbyRefresh();
        clientSocket.send(msg);
    }

    public void sendValidate(int userId, String validationKey) {
        MessageValidate msg = new MessageValidate(validationKey, userId);
        clientSocket.send(msg);
    }

    public void sendLobbyLeave(int lobbyId) {
        MessageLobbyLeave msg = new MessageLobbyLeave(lobbyId);
        clientSocket.send(msg);
    }
}
