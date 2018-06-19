package game.shared.messages.lobbymessages;

public class MessagePlayersInLobbyUpdated {
    String[] players;

    public String[] getPlayers() {
        return players;
    }

    public MessagePlayersInLobbyUpdated(String[] players){
        this.players = players;
    }
}
