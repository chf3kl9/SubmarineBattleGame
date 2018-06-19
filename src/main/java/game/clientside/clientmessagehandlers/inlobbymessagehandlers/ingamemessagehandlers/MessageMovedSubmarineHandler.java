package game.clientside.clientmessagehandlers.inlobbymessagehandlers.ingamemessagehandlers;

import game.clientside.clienthandler.IClientHandler;
import game.shared.messagehandlers.MessageHandler;
import game.shared.messages.inlobbymessages.ingamemessages.MessageMovedSubmarine;

public class MessageMovedSubmarineHandler extends MessageHandler<MessageMovedSubmarine> {

    public MessageMovedSubmarineHandler(IClientHandler clientHandler){
        super(clientHandler);
    }

    @Override
    public void handleMessageInternal(MessageMovedSubmarine message, String sessionId){
        getClientHandler().moveConfirm(message.getSubmarine());
    }
}
