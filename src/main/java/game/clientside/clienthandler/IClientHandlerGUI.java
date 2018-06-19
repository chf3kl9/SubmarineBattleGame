package game.clientside.clienthandler;

import game.models.Settings;
import javafx.scene.input.KeyCode;

public interface IClientHandlerGUI {
    void login(String username, String password);
    void register(String username, String password);
    void leaveLobby();
    void createLobby(String lobbyName, String password, Settings settings);
    void startRound();
    void joinLobby(String lobbyName, String password);
    void getLobbies();
    void getHighScore();
    void handleKeyInput(KeyCode inputkey, boolean released);
}
