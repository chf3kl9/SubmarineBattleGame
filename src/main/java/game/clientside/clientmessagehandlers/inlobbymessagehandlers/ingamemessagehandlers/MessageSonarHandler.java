package game.clientside.clientmessagehandlers.inlobbymessagehandlers.ingamemessagehandlers;

import game.clientside.clienthandler.IClientHandler;
import game.shared.messagehandlers.MessageHandler;
import game.shared.messages.inlobbymessages.ingamemessages.MessageSonar;

public class MessageSonarHandler extends MessageHandler<MessageSonar> {

    public MessageSonarHandler(IClientHandler clientHandler){
        super(clientHandler);
    }

    @Override
    public void handleMessageInternal(MessageSonar message, String sessionId){
        getClientHandler().sonar(message.getVision());
    }
}
