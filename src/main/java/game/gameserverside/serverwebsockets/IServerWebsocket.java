package game.gameserverside.serverwebsockets;

public interface IServerWebsocket {
    void sendTo(String sessionId, Object object);

    void broadcast(Object object);

    void sendToGroup(String[] sessionId, Object object);
}
