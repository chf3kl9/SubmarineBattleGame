package game.gameserverside.servermessagehandlers;

import game.gameserverside.serverhandler.IServerHandlerMessenger;
import game.gameserverside.servermessagehandlers.inlobbymessagehandlers.*;
import game.shared.messagehandlers.IMessageHandler;
import game.shared.messagehandlers.IMessageHandlerFactory;

public class ServerMessageHandlerFactory implements IMessageHandlerFactory {

    public IMessageHandler getHandler(String simpleType, Object serverHandlerIn) {
        IServerHandlerMessenger serverHandler = (IServerHandlerMessenger) serverHandlerIn;

        switch(simpleType){
            case "MessageLobbyCreate":
                return new MessageLobbyCreateHandler(serverHandler);
            case "MessageLobbyJoin":
                return new MessageLobbyJoinHandler(serverHandler);
            case "MessageLobbyRefresh":
                return new MessageLobbyRefreshHandler(serverHandler);
            case "MessageLobbyLeave":
                return new MessageLobbyLeaveHandler(serverHandler);
            case "MessageRoundStart":
                return new MessageLobbyStartHandler(serverHandler);
            case "MessageFire":
                return new MessageFireHandler(serverHandler);
            case "MessageMove":
                return new MessageMoveHandler(serverHandler);
            case "MessageValidate":
                return new MessageValidateHandler(serverHandler);
            default:
                return null;
        }
    }
}
