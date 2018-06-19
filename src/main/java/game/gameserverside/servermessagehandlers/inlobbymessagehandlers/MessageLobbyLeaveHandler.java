package game.gameserverside.servermessagehandlers.inlobbymessagehandlers;

import game.gameserverside.serverhandler.IServerHandlerMessenger;
import game.shared.messagehandlers.MessageHandler;
import game.shared.messages.inlobbymessages.MessageLobbyLeave;

public class MessageLobbyLeaveHandler extends MessageHandler<MessageLobbyLeave> {

    public MessageLobbyLeaveHandler(IServerHandlerMessenger serverHandler){
        super(serverHandler);
    }

    @Override
    public void handleMessageInternal(MessageLobbyLeave message, String sessionId){
        getServerHandler().lobbyLeave(sessionId, message.getLobbyId());
    }
}
