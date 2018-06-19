package game.clientside.clienthandler;

import game.clientside.gui.IGUI;
import game.models.GameObject;
import game.models.interfaces.ISubmarine;
import game.models.Settings;
import game.models.Torpedo;

import java.util.List;

public interface IClientHandler {
    void death();
    void kill();
    void moveConfirm(ISubmarine submarine);
    void sonar(List<GameObject> visibleObjects);
    void torpedoConfirm(List<Torpedo> torpedoes);
    void incorrectLogin();
    void lobbyClosed();
    void lobbyJoined(int lobbyId, Settings settings);
    void roundStarted(ISubmarine submarine);
    void getLobbies(List<String> lobbies);
    void lobbyCreated(int lobbyId);
    void updatePlayersInLobby(String[] players);
    void setGui(IGUI gui);
    void validationFinish(boolean success);
}
