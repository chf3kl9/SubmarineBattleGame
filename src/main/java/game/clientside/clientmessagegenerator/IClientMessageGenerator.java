package game.clientside.clientmessagegenerator;

import game.models.Settings;

public interface IClientMessageGenerator {
    void sendFireShot(int lobbyId);
    void sendMove(int lobbyId, int rotate, int accelerate);
    void sendJoinLobby(String lobby, String playerName, String password, int userId);
    void sendStartRound(int lobbyId);
    void sendOpenLobby(String lobby, String playerName, String password, Settings settings, int userId);
    void sendLobbyRefresh();
    void sendValidate(int userId, String validationKey);
    void sendLobbyLeave(int lobbyId);
}
