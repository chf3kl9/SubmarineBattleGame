package game.gameserverside.serverhandler;

import api.IRestWebsocket;
import game.gameserverside.gameserverlobby.GameServerLobbyEnd;
import game.models.GameObject;
import game.models.interfaces.ISubmarine;
import game.models.Settings;
import game.gameserverside.gameserverlobby.GameServerLobby;
import game.gameserverside.gameserverlobby.IGameServerLobby;
import game.gameserverside.servermessagegenerator.IServerMessageGenerator;
import game.models.Torpedo;
import game.shared.logger.LogLevel;
import game.shared.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class ServerHandler implements IServerHandlerGame, IServerHandlerMessenger {

    IServerMessageGenerator messageGenerator;
    ArrayList<IGameServerLobby> lobbies = new ArrayList<>();
    IRestWebsocket RESTHandler;

    public ServerHandler(IServerMessageGenerator messageGenerator, IRestWebsocket RESTHandler){
        this.messageGenerator = messageGenerator;
        this.RESTHandler = RESTHandler;
    }

    //region gameserverlobby --> serverhandler --> servermessagegenerator
    public void submarineDeath(String playerId) {
        messageGenerator.sendDeath(playerId);
    }

    public void sonarPing(String playerId, List<GameObject> visibleObjects) {
        messageGenerator.sendSonar(playerId, visibleObjects);
    }

    public void torpedoConfirm(String playerId, List<Torpedo> torpedoes) {
        messageGenerator.sendFiredShot(playerId, torpedoes);
    }

    public void moveConfirm(String playerId, ISubmarine submarine) {
        messageGenerator.sendMoveConfirm(playerId, submarine);
    }

    public void submarineKill(String playerId, int userId) {
        RESTHandler.updateScore(userId);
        messageGenerator.sendKill(playerId);
    }

    public void roundStarted(String playerId, ISubmarine submarine) {
        messageGenerator.sendRoundStarted(playerId, submarine);
    }

    public void joinFailed(String playerId){
        messageGenerator.sendIncorrectLogin(playerId);
    }

    public void joinSuccess(String playerId, int lobbyId, Settings settings){
        messageGenerator.sendLobbyJoined(playerId, lobbyId, settings);
    }
    //endregion

    //region MessageHandler --> serverhandler --> gameserverlobby
    public void fireShot(String playerId, int lobbyId) {
        findLobby(lobbyId).fireShot(playerId);
    }

    public void moveSubmarine(String playerId, int lobbyId, int rotate, int accelerate) {
        findLobby(lobbyId).moveSubmarine(playerId, rotate, accelerate);
    }

    public void joinLobby(String playerId, int userId, String playerName, String lobby, String password) {
        findLobby(lobby).joinLobby(playerId, userId, playerName, password);
    }

    public void startRound(String playerId, int lobbyId) {
        findLobby(lobbyId).startRound(playerId);
    }

    public void openLobby(String playerId, int userId, String playerName, String lobby, String password, Settings settings) {
        int lobbyId = 0;
        for (IGameServerLobby gameServerLobby : lobbies) {
            if (gameServerLobby.getLobbyId() > lobbyId)
                lobbyId = gameServerLobby.getLobbyId();
        }
        lobbyId++;
        IGameServerLobby gameServerLobby = new GameServerLobby(lobbyId, playerId, userId, playerName, lobby, password, settings, this);
        lobbies.add(gameServerLobby);
        messageGenerator.sendLobbyCreated(playerId, lobbyId);
        messageGenerator.sendPlayersInLobbyUpdated(gameServerLobby.getSessionIds(), gameServerLobby.getPlayerNames());
    }

    public void lobbyRefresh(String playerId) {
        ArrayList<String> lobbiesName = new ArrayList<>();
        for (IGameServerLobby lobby : lobbies)
            lobbiesName.add(lobby.getLobbyName());
        messageGenerator.sendLobbies(playerId, lobbiesName);
    }

    public void lobbyClosed(int lobbyId){
        IGameServerLobby lobby = findLobby(lobbyId);
        messageGenerator.sendLobbyClosed(lobby.getSessionIds());
        lobbies.remove(findLobby(lobbyId));
    }

    public void playersInLobbyUpdated(int lobbyId){
        IGameServerLobby lobby = findLobby(lobbyId);
        messageGenerator.sendPlayersInLobbyUpdated(lobby.getSessionIds(), lobby.getPlayerNames());
    }

    public void lobbyLeave(String playerId, int lobbyId){
        findLobby(lobbyId).leaveLobby(playerId);
    }

    public void validationFinished(String playerId, boolean success) {
        messageGenerator.sendValidation(playerId, success);
    }

    @Override
    public boolean validate(int userId, String validationKey) {
        return RESTHandler.validateLogin(userId, validationKey);
    }

    //endregion

    private IGameServerLobby findLobby(int lobbyId){
        for (IGameServerLobby lobby : lobbies){
            if (lobby.getLobbyId() == lobbyId)
                return lobby;
        }
        Logger.getInstance().log("Lobby not found!", LogLevel.WARNING);
        return new GameServerLobbyEnd();
    }

    private IGameServerLobby findLobby(String lobbyName){
        for (IGameServerLobby lobby : lobbies){
            if (lobby.getLobbyName().equals(lobbyName))
                return lobby;
        }
        Logger.getInstance().log("lobby not found", LogLevel.WARNING);
        return new GameServerLobbyEnd();
    }
}
