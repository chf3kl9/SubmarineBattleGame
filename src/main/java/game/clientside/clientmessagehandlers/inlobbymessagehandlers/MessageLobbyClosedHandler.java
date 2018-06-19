package game.clientside.clientmessagehandlers.inlobbymessagehandlers;

import game.clientside.clienthandler.IClientHandler;
import game.shared.messagehandlers.MessageHandler;
import game.shared.messages.inlobbymessages.MessageLobbyClosed;

public class MessageLobbyClosedHandler extends MessageHandler<MessageLobbyClosed> {

    public MessageLobbyClosedHandler(IClientHandler clientHandler){
        super(clientHandler);
    }

    @Override
    public void handleMessageInternal(MessageLobbyClosed message, String sessionId){
        getClientHandler().lobbyClosed();
    }
}
