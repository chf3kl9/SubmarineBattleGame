package game.gameserverside.servermessagehandlers;

import game.gameserverside.serverhandler.IServerHandlerMessenger;
import game.gameserverside.servermessageprocessor.ServerMessageProcessor;
import game.shared.messagehandlers.MessageHandler;
import game.shared.messages.validationmessages.MessageValidate;

public class MessageValidateHandler extends MessageHandler<MessageValidate> {

    ServerMessageProcessor messageProcessor;
    public MessageValidateHandler(IServerHandlerMessenger serverHandler){
        super(serverHandler);
    }

    public void handleMessageInternal(MessageValidate message, String sessionId) {
        boolean validated = getServerHandler().validate(message.getUserId(), message.getValidationKey());
        getServerHandler().validationFinished(sessionId, validated);

        if (validated){
            messageProcessor.sessionValidated(sessionId);
        }
    }

    public void setMessageProcessor(ServerMessageProcessor messageProcessor){
        this.messageProcessor = messageProcessor;
    }
}
