package game.clientside.clientmessagehandlers;

import game.clientside.clienthandler.IClientHandler;
import game.shared.messagehandlers.MessageHandler;
import game.shared.messages.lobbymessages.MessageLobbyJoinFailed;

public class MessageLobbyJoinFailedHandler extends MessageHandler<MessageLobbyJoinFailed> {

    public MessageLobbyJoinFailedHandler(IClientHandler clientHandler){
        super(clientHandler);
    }

    @Override
    public void handleMessageInternal(MessageLobbyJoinFailed message, String sessionId){
        getClientHandler().incorrectLogin();
    }
}
