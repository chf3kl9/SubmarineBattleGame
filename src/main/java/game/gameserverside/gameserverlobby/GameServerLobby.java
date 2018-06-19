package game.gameserverside.gameserverlobby;

import game.models.interfaces.ISubmarine;
import game.models.Player;
import game.models.Settings;
import game.gameserverside.gamelogic.ServerGameLogic;
import game.gameserverside.gamelogic.IServerGameLogic;
import game.gameserverside.serverhandler.IServerHandlerGame;

public class GameServerLobby implements IGameServerLobby{

    int lobbyId;
    Player[] players;
    String lobbyName;
    String password;
    IServerGameLogic gameLogic;
    Settings settings;
    IServerHandlerGame serverHandler;

    Thread gameThread;

    public int getLobbyId() {
        return lobbyId;
    }

    public String[] getPlayerNames(){
        String[] playerNames = new String[settings.getMaxPlayers()];
        for(int i = 0; i<settings.getMaxPlayers(); i++){
            if (players[i] != null)
                playerNames[i] = players[i].getPlayerName();
        }
        return playerNames;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public String[] getSessionIds() {
        String[] sessionIds = new String[settings.getMaxPlayers()];
        for(int i = 0; i<settings.getMaxPlayers(); i++){
            if (players[i] != null)
                sessionIds[i] = players[i].getSessionId();
        }
        return sessionIds;
    }

    public GameServerLobby(int lobbyId, String playerId, int userId, String playerName, String lobbyName, String password, Settings settings, IServerHandlerGame serverHandler){
        this.lobbyId = lobbyId;
        this.lobbyName = lobbyName;
        this.password = password;
        this.settings = settings;

        players = new Player[settings.getMaxPlayers()];
        players[0] = new Player(playerId, userId, playerName);

        this.serverHandler = serverHandler;
    }

    public void fireShot(String playerId) {
        if (gameLogic != null){
            gameLogic.fireShot(playerId);
        }
    }

    public void moveSubmarine(String playerId, int rotate, int accelerate) {
        if (gameLogic != null){
            gameLogic.moveSubmarine(playerId, rotate, accelerate);
        }
    }

    public void joinLobby(String playerId, int userId, String playerName, String password) {
        if (gameLogic != null){
            serverHandler.joinFailed(playerId);
            return;
        }
        else{
            if (this.password == null || this.password.equals(password)){
                for(int i = 0; i<players.length; i++){
                    if (players[i] == null){
                        players[i] = new Player(playerId, userId, playerName);
                        serverHandler.joinSuccess(playerId, lobbyId, settings);
                        serverHandler.playersInLobbyUpdated(lobbyId);
                        return;
                    }
                }
            }
            serverHandler.joinFailed(playerId);
        }
    }

    public void startRound(String playerId) {
        if (players[0].getSessionId().equals(playerId)) {
            if (gameLogic == null) {
                gameLogic = new ServerGameLogic(players, settings.getRotationSpeed(), settings.getReloadTime(), settings.getAcceleration(), settings.getTorpedoSpeed(), settings.getMaxSpeed(), serverHandler);
                for (ISubmarine submarine : gameLogic.getSubmarines()) {
                    serverHandler.roundStarted(submarine.getPlayer().getSessionId(), submarine);
                }
                gameThread = new Thread(gameLogic);
                gameThread.start();
            }
        }
    }

    public void leaveLobby(String playerId) {
        if (players[0].getSessionId().equals(playerId)){
            closeLobby();
        }
        else {
            for (int i = 1; i < players.length; i++) {
                if (players[i].getSessionId().equals(playerId)) {
                    players[i] = null;
                    serverHandler.playersInLobbyUpdated(lobbyId);
                    return;
                }
            }
        }
    }

    private void closeLobby() {
        if (gameThread != null)
            gameThread.interrupt();
        serverHandler.lobbyClosed(lobbyId);
    }
}
