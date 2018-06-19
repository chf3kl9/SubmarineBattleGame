package game.gameserverside.servermessageprocessor;

import game.gameserverside.serverhandler.IServerHandlerMessenger;
import game.gameserverside.servermessagehandlers.MessageValidateHandler;
import game.shared.messagehandlers.IMessageHandler;
import game.shared.messagehandlers.IMessageHandlerFactory;
import game.shared.messageprocessor.MessageProcessorBase;

import java.util.ArrayList;

public class ServerMessageProcessor extends MessageProcessorBase {

    ArrayList<String> validatedSessions = new ArrayList<>();

    private IServerHandlerMessenger serverHandler;

    public void processMessage(String sessionId, String type, String data){

        String simpleType = type.split("\\.")[type.split("\\.").length - 1];

        IMessageHandler handler = getMessageHandlerFactory().getHandler(simpleType, getHandler());

        if (handler.getClass().equals(MessageValidateHandler.class)){
            MessageValidateHandler validationHandler = (MessageValidateHandler) handler;
            validationHandler.setMessageProcessor(this);
            validationHandler.handleMessage(data, sessionId, getGson());
        }
        else if (validatedSessions.contains(sessionId)) {
            handler.handleMessage(data, sessionId, getGson());
        }
    }

    public void sessionValidated(String sessionId){
        validatedSessions.add(sessionId);
    }

    public ServerMessageProcessor(IMessageHandlerFactory messageHandlerFactory, IServerHandlerMessenger serverHandler)
    {
        super(messageHandlerFactory);
        this.serverHandler = serverHandler;
    }

    public IServerHandlerMessenger getHandler(){
        return serverHandler;
    }
}
