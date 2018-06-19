package game.clientside.gui;

import game.clientside.clienthandler.IClientHandlerGUI;
import game.models.GameObject;
import game.models.interfaces.ISubmarine;
import game.models.Settings;

import java.util.List;

public interface IGUI {
    void setClientHandler(IClientHandlerGUI clientHandler);
    void lobbies(List<String> lobbies);
    void joinResult(boolean success, Settings settings);
    void lobbyCreated();
    void playersInLobbyUpdate(String[] players);
    void highscore(List<String> top100, String personalScore);
    void loginResult(boolean success);
    void registrationResult(boolean success);
    void lobbyClosed();
    void roundStarted(ISubmarine submarine);
    void updateGameScreen(List<GameObject> objects);
}
