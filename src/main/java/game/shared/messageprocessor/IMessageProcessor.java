package game.shared.messageprocessor;

public interface IMessageProcessor {
    void processMessage(String sessionId, String messageType, String messageData);
}
