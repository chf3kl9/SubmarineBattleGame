package game.gameserverside.servermessagehandlers;

import game.gameserverside.serverhandler.IServerHandlerMessenger;
import game.shared.messagehandlers.MessageHandler;
import game.shared.messages.lobbymessages.MessageLobbyRefresh;

public class MessageLobbyRefreshHandler extends MessageHandler<MessageLobbyRefresh> {

    public MessageLobbyRefreshHandler(IServerHandlerMessenger serverHandler){
        super(serverHandler);
    }

    @Override
    public void handleMessageInternal(MessageLobbyRefresh message, String sessionId){
        getServerHandler().lobbyRefresh(sessionId);
    }
}
