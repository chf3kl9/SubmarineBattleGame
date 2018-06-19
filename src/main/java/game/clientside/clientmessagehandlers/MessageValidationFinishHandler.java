package game.clientside.clientmessagehandlers;

import game.clientside.clienthandler.IClientHandler;
import game.shared.messagehandlers.MessageHandler;
import game.shared.messages.validationmessages.MessageValidationFinish;

public class MessageValidationFinishHandler extends MessageHandler<MessageValidationFinish> {

    public MessageValidationFinishHandler(IClientHandler handler){
        super(handler);
    }

    public void handleMessageInternal(MessageValidationFinish message, String sessionId) {
        getClientHandler().validationFinish(message.getSuccess());
    }
}
