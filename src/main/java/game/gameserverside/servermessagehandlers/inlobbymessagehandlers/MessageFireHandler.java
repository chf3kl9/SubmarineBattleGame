package game.gameserverside.servermessagehandlers.inlobbymessagehandlers;

import game.gameserverside.serverhandler.IServerHandlerMessenger;
import game.shared.messagehandlers.MessageHandler;
import game.shared.messages.inlobbymessages.ingamemessages.MessageFire;

public class MessageFireHandler extends MessageHandler<MessageFire> {

    public MessageFireHandler(IServerHandlerMessenger serverHandler){
        super(serverHandler);
    }

    @Override
    public void handleMessageInternal(MessageFire message, String sessionId){
        getServerHandler().fireShot(sessionId, message.getLobbyId());
    }
}
