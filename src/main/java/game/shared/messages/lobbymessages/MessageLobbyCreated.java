package game.shared.messages.lobbymessages;

import game.shared.messages.inlobbymessages.MessageInLobby;

public class MessageLobbyCreated extends MessageInLobby{

    public MessageLobbyCreated(int lobbyId){
        super(lobbyId);
    }
}
