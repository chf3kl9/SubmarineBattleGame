package game.clientside.clientwebsockets;

import game.shared.messageprocessor.IMessageProcessor;

public interface IClientWebsocket {
    void start();

    void stop();

    void send(Object object);

    void setMessageHandler(IMessageProcessor handler);

    void onWebSocketMessageReceived(String message, String sessionId);
}
