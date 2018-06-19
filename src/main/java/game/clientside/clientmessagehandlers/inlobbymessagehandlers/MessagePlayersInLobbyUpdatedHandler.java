package game.clientside.clientmessagehandlers.inlobbymessagehandlers;

import game.clientside.clienthandler.IClientHandler;
import game.shared.messagehandlers.MessageHandler;
import game.shared.messages.lobbymessages.MessagePlayersInLobbyUpdated;

public class MessagePlayersInLobbyUpdatedHandler extends MessageHandler<MessagePlayersInLobbyUpdated> {

    public MessagePlayersInLobbyUpdatedHandler(IClientHandler clientHandler){
        super(clientHandler);
    }

    @Override
    public void handleMessageInternal(MessagePlayersInLobbyUpdated message, String sessionId){
        getClientHandler().updatePlayersInLobby(message.getPlayers());
    }
}