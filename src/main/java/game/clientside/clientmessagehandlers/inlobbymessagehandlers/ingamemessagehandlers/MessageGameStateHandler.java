package game.clientside.clientmessagehandlers.inlobbymessagehandlers.ingamemessagehandlers;

import game.clientside.clienthandler.IClientHandler;
import game.shared.messagehandlers.MessageHandler;
import game.shared.messages.inlobbymessages.ingamemessages.MessageGameState;

public class MessageGameStateHandler extends MessageHandler<MessageGameState> {

    public MessageGameStateHandler(IClientHandler clientHandler){
        super(clientHandler);
    }

    @Override
    public void handleMessageInternal(MessageGameState message, String sessionId){
        //You are useless damnit
    }
}