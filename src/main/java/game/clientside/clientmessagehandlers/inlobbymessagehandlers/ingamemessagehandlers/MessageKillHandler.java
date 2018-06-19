package game.clientside.clientmessagehandlers.inlobbymessagehandlers.ingamemessagehandlers;

import game.clientside.clienthandler.IClientHandler;
import game.shared.messagehandlers.MessageHandler;
import game.shared.messages.inlobbymessages.ingamemessages.MessageKill;

public class MessageKillHandler extends MessageHandler<MessageKill> {

    public MessageKillHandler (IClientHandler clientHandler){
        super(clientHandler);
    }

    @Override
    public void handleMessageInternal(MessageKill message, String sessionId){
        getClientHandler().kill();
    }
}
