package game.clientside.clientmessagehandlers;

import game.clientside.clienthandler.IClientHandler;
import game.clientside.clientmessagehandlers.inlobbymessagehandlers.ingamemessagehandlers.*;
import game.clientside.clientmessagehandlers.inlobbymessagehandlers.MessageLobbyClosedHandler;
import game.clientside.clientmessagehandlers.inlobbymessagehandlers.MessageLobbyJoinedHandler;
import game.clientside.clientmessagehandlers.inlobbymessagehandlers.MessagePlayersInLobbyUpdatedHandler;
import game.clientside.clientmessagehandlers.inlobbymessagehandlers.MessageLobbyStartedHandler;
import game.shared.messagehandlers.IMessageHandler;
import game.shared.messagehandlers.IMessageHandlerFactory;

public class ClientMessageHandlerFactory implements IMessageHandlerFactory {

    public IMessageHandler getHandler(String simpleType, Object clientHandlerIn) {
        IClientHandler clientHandler = (IClientHandler) clientHandlerIn;
        switch(simpleType){
            case "MessageDeath":
                return new MessageDeathHandler(clientHandler);
            case "MessageFiredTorpedo":
                return new MessageFiredTorpedoHandler(clientHandler);
            case "MessageGameState":
                return new MessageGameStateHandler(clientHandler);
            case "MessageKill":
                return new MessageKillHandler(clientHandler);
            case "MessageMovedSubmarine":
                return new MessageMovedSubmarineHandler(clientHandler);
            case "MessageSonar":
                return new MessageSonarHandler(clientHandler);
            case "MessageLobbyClosed":
                return new MessageLobbyClosedHandler(clientHandler);
            case "MessageLobbyJoined":
                return new MessageLobbyJoinedHandler(clientHandler);
            case "MessageRoundStarted":
                return new MessageLobbyStartedHandler(clientHandler);
            case "MessageLobbies":
                return new MessageLobbiesHandler(clientHandler);
            case "MessageLobbyCreated":
                return new MessageLobbyCreatedHandler(clientHandler);
            case "MessageLobbyJoinFailed":
                return new MessageLobbyJoinFailedHandler(clientHandler);
            case "MessagePlayersInLobbyUpdated":
                return new MessagePlayersInLobbyUpdatedHandler(clientHandler);
            case "MessageValidationFinish":
                return new MessageValidationFinishHandler(clientHandler);
            default:
                return null;
        }
    }
}
