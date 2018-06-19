package game.shared.messages.inlobbymessages;

public abstract class MessageInLobby{
    int lobbyId;

    public MessageInLobby(int lobbyId){
        this.lobbyId = lobbyId;
    }
    public MessageInLobby(){this.lobbyId = -1;}

    public int getLobbyId() {
        return lobbyId;
    }
}
