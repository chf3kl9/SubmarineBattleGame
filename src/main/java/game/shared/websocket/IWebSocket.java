package game.shared.websocket;

import game.shared.messageprocessor.IMessageProcessor;

public interface IWebSocket {
    void start();

    void stop();

    void setMessageHandler(IMessageProcessor handler);
}
