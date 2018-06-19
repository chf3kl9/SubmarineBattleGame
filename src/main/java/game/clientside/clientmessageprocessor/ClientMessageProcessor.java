package game.clientside.clientmessageprocessor;

import game.clientside.clienthandler.IClientHandler;
import game.shared.messagehandlers.IMessageHandler;
import game.shared.messagehandlers.IMessageHandlerFactory;
import game.shared.messageprocessor.MessageProcessorBase;

public class ClientMessageProcessor extends MessageProcessorBase {

    public void processMessage(String sessionId, String type, String data){
        String simpleType = type.split("\\.")[type.split("\\.").length - 1];

        IMessageHandler handler = getMessageHandlerFactory().getHandler(simpleType, getHandler());
        handler.handleMessage(data, sessionId, getGson());
    }

    private IClientHandler clientHandler;

    public ClientMessageProcessor(IMessageHandlerFactory messageHandlerFactory, IClientHandler clientHandler)
    {
        super(messageHandlerFactory);
        this.clientHandler = clientHandler;
    }

    public IClientHandler getHandler(){
        return clientHandler;
    }
}
