package game.clientside.clientmessagehandlers.inlobbymessagehandlers.ingamemessagehandlers;

import game.clientside.clienthandler.IClientHandler;
import game.shared.messagehandlers.MessageHandler;
import game.shared.messages.inlobbymessages.ingamemessages.MessageFiredTorpedo;

public class MessageFiredTorpedoHandler extends MessageHandler<MessageFiredTorpedo> {

    public MessageFiredTorpedoHandler(IClientHandler clientHandler) {
        super(clientHandler);
    }

    @Override
    public void handleMessageInternal(MessageFiredTorpedo message, String sessionId) {
        getClientHandler().torpedoConfirm(message.getTorpedoes());
    }
}
