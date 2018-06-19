package game.clientside.clientmessagehandlers;

import game.clientside.clienthandler.IClientHandler;
import game.shared.messagehandlers.MessageHandler;
import game.shared.messages.lobbymessages.MessageLobbies;

public class MessageLobbiesHandler extends MessageHandler<MessageLobbies> {

    public MessageLobbiesHandler(IClientHandler clientHandler){
        super(clientHandler);
    }

    @Override
    public void handleMessageInternal(MessageLobbies message, String sessionId){
        getClientHandler().getLobbies(message.getLobbies());
    }
}
