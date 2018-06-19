package game.clientside.clientmessagehandlers.inlobbymessagehandlers.ingamemessagehandlers;

import game.clientside.clienthandler.IClientHandler;
import game.shared.messagehandlers.MessageHandler;
import game.shared.messages.inlobbymessages.ingamemessages.MessageDeath;

public class MessageDeathHandler extends MessageHandler<MessageDeath> {

    public MessageDeathHandler(IClientHandler clientHandler){
        super(clientHandler);
    }

    @Override
    public void handleMessageInternal(MessageDeath message, String sessionId){
        getClientHandler().death();
    }
}
