package game.clientside.clientmessagehandlers.inlobbymessagehandlers;

import game.clientside.clienthandler.IClientHandler;
import game.shared.messagehandlers.MessageHandler;
import game.shared.messages.inlobbymessages.MessageRoundStarted;

public class MessageLobbyStartedHandler extends MessageHandler<MessageRoundStarted> {

    public MessageLobbyStartedHandler(IClientHandler clientHandler){
        super(clientHandler);
    }

    @Override
    public void handleMessageInternal(MessageRoundStarted message, String sessionId){
        getClientHandler().roundStarted(message.getSubmarine());
    }
}
