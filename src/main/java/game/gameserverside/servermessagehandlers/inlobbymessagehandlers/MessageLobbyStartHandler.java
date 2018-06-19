package game.gameserverside.servermessagehandlers.inlobbymessagehandlers;

import game.gameserverside.serverhandler.IServerHandlerMessenger;
import game.shared.messagehandlers.MessageHandler;
import game.shared.messages.inlobbymessages.MessageRoundStart;

public class MessageLobbyStartHandler extends MessageHandler<MessageRoundStart> {

    public MessageLobbyStartHandler(IServerHandlerMessenger serverHandler){
        super(serverHandler);
    }

    @Override
    public void handleMessageInternal(MessageRoundStart message, String sessionId){
        getServerHandler().startRound(sessionId, message.getLobbyId());
    }
}
