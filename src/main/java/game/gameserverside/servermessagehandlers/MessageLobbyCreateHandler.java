package game.gameserverside.servermessagehandlers;

import game.gameserverside.serverhandler.IServerHandlerMessenger;
import game.shared.messagehandlers.MessageHandler;
import game.shared.messages.lobbymessages.MessageLobbyCreate;

public class MessageLobbyCreateHandler extends MessageHandler<MessageLobbyCreate> {

    public MessageLobbyCreateHandler(IServerHandlerMessenger serverHandler){
        super(serverHandler);
    }

    @Override
    public void handleMessageInternal(MessageLobbyCreate message, String sessionId){
        getServerHandler().openLobby(sessionId, message.getUserId(), message.getPlayerName(), message.getLobbyName(), message.getPassword(), message.getSettings());
    }
}
