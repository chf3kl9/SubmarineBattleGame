package game.gameserverside.servermessagehandlers.inlobbymessagehandlers;

import game.gameserverside.serverhandler.IServerHandlerMessenger;
import game.shared.messagehandlers.MessageHandler;
import game.shared.messages.inlobbymessages.ingamemessages.MessageMove;

public class MessageMoveHandler extends MessageHandler<MessageMove> {

    public MessageMoveHandler(IServerHandlerMessenger serverHandler){
        super(serverHandler);
    }

    @Override
    public void handleMessageInternal(MessageMove message, String sessionId){
        getServerHandler().moveSubmarine(sessionId, message.getLobbyId(), message.getRotate(), message.getAccelerate());
    }
}
