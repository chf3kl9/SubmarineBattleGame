package game.gameserverside.gameserverlobby;

public class GameServerLobbyEnd implements IGameServerLobby {


    @Override
    public int getLobbyId() {
        return 0;
    }

    @Override
    public String getLobbyName() {
        return null;
    }

    @Override
    public String[] getSessionIds() {
        return new String[0];
    }

    @Override
    public String[] getPlayerNames() {
        return new String[0];
    }

    @Override
    public void fireShot(String playerId) {
        return;
    }

    @Override
    public void moveSubmarine(String playerId, int rotate, int accelerate) {
        return;
    }

    @Override
    public void joinLobby(String playerId, int userId, String playerName, String password) {
        return;
    }

    @Override
    public void startRound(String playerId) {
        return;
    }

    @Override
    public void leaveLobby(String playerId) {
        return;
    }
}
