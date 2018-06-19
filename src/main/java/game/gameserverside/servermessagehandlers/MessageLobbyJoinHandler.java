package game.gameserverside.servermessagehandlers;

import game.gameserverside.serverhandler.IServerHandlerMessenger;
import game.shared.messagehandlers.MessageHandler;
import game.shared.messages.lobbymessages.MessageLobbyJoin;

public class MessageLobbyJoinHandler extends MessageHandler<MessageLobbyJoin> {

    public MessageLobbyJoinHandler(IServerHandlerMessenger serverHandler){
        super(serverHandler);
    }

    @Override
    public void handleMessageInternal(MessageLobbyJoin message, String sessionId){
        getServerHandler().joinLobby(sessionId, message.getUserId(), message.getPlayerName(), message.getLobby(), message.getPassword());
    }
}
