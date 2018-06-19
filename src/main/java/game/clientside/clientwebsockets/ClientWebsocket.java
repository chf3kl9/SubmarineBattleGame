package game.clientside.clientwebsockets;

import game.shared.logger.LogLevel;
import game.shared.logger.Logger;
import game.shared.messageprocessor.IMessageProcessor;
import game.shared.messages.EncapsulatingMessage;
import game.shared.websocket.WebsocketBase;

import javax.websocket.*;

import javax.websocket.Session;
import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;

import java.io.IOException;
import java.net.URI;

@ClientEndpoint
public class ClientWebsocket extends WebsocketBase implements IClientWebsocket {

    private static final String serverUri = "ws://localhost:8095/submarine/websocket/";

    private Session session;
    IMessageProcessor handler;
    private static ClientWebsocket instance = null;

    public static ClientWebsocket getInstance() {
        if (instance == null) {
            instance = new ClientWebsocket();
        }
        return instance;
    }

    @Override
    public void start() {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this , new URI(serverUri));
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
        }
    }

    @Override
    public void stop() {
        try {
            if(session != null)
                session.close();

        } catch (Exception ex){
            Logger.getInstance().log(ex);
        }
    }

    @OnOpen
    public void onWebSocketConnect(Session session){
        this.session = session;
        Logger.getInstance().log("Disconnected", LogLevel.INFORMATION);
    }

    @OnMessage
    public void onWebSocketText(String message, Session session){
        Logger.getInstance().log("Received: " + message, LogLevel.INFORMATION);
        onWebSocketMessageReceived(message, session.getId());
    }

    public void onWebSocketMessageReceived(String message, String sessionId)
    {
        EncapsulatingMessage msg = getGson().fromJson(message, EncapsulatingMessage.class);
        handler.processMessage(sessionId, msg.getMessageType(), msg.getMessageData());
    }

    public void setMessageHandler(IMessageProcessor handler) {
        this.handler = handler;
    }

    @OnError
    public void onWebSocketError(Session session, Throwable cause) {
        Logger.getInstance().log(cause.getMessage(), LogLevel.ERROR);
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason){
        session = null;
        Logger.getInstance().log("[Closed]: " + reason, LogLevel.INFORMATION);
    }

    private void sendMessageToServer(String message)
    {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            Logger.getInstance().log(ex);
        }
    }

    public void send(Object object)
    {
        String msg = getEncapsulatingMessageGenerator().generateMessageString(object);
        sendMessageToServer(msg);
    }
}
