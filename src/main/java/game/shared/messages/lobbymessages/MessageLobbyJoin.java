package game.shared.messages.lobbymessages;

public class MessageLobbyJoin{
    int userId;
    String playerName;
    String lobby;
    String password;

    public MessageLobbyJoin(String lobby, String playerName, String password, int userId){
        this.lobby = lobby;
        this.password = password;
        this.userId = userId;
        this.playerName = playerName;
    }

    public String getLobby() {
        return lobby;
    }

    public String getPassword() {
        return password;
    }

    public int getUserId() {
        return userId;
    }

    public String getPlayerName() {
        return playerName;
    }
}
