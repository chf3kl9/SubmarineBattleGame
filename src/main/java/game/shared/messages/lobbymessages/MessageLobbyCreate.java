package game.shared.messages.lobbymessages;

import game.models.Settings;

public class MessageLobbyCreate {
    int userId;
    String playerName;
    String lobbyName;
    String password;
    Settings settings;

    public MessageLobbyCreate(String lobbyName, String playername, String password, Settings settings, int userId){
        this.lobbyName = lobbyName;
        this.password = password;
        this.settings = settings;
        this.userId = userId;
        this.playerName = playername;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public String getPassword() {
        return password;
    }

    public Settings getSettings() {
        return settings;
    }

    public int getUserId() {
        return userId;
    }

    public String getPlayerName(){
        return playerName;
    }
}
