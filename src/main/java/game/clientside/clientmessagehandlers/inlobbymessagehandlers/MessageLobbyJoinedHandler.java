package game.clientside.clientmessagehandlers.inlobbymessagehandlers;

import game.clientside.clienthandler.IClientHandler;
import game.shared.messagehandlers.MessageHandler;
import game.shared.messages.inlobbymessages.MessageLobbyJoined;

public class MessageLobbyJoinedHandler extends MessageHandler<MessageLobbyJoined> {

    public MessageLobbyJoinedHandler(IClientHandler clientHandler){
        super(clientHandler);
    }

    @Override
    public void handleMessageInternal(MessageLobbyJoined message, String sessionId){
        getClientHandler().lobbyJoined(message.getLobbyId(), message.getSettings());
    }
}
