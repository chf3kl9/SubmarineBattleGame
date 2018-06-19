package game.clientside.clientmessagehandlers;

import game.clientside.clienthandler.IClientHandler;
import game.shared.messagehandlers.MessageHandler;
import game.shared.messages.lobbymessages.MessageLobbyCreated;

public class MessageLobbyCreatedHandler extends MessageHandler<MessageLobbyCreated> {

    public MessageLobbyCreatedHandler(IClientHandler clientHandler){
        super(clientHandler);
    }

    @Override
    public void handleMessageInternal(MessageLobbyCreated message, String sessionId){
        getClientHandler().lobbyCreated(message.getLobbyId());
    }
}
