package stubs;

import game.gameserverside.serverwebsockets.IServerWebsocket;
import game.shared.messages.EncapsulatingMessage;
import game.shared.websocket.WebsocketBase;
import java.util.NoSuchElementException;

import javax.websocket.OnMessage;

public class ServerWebsocketStub extends WebsocketBase implements IServerWebsocket {

    public void sendTo(String sessionId, Object object) {
        String msg = getEncapsulatingMessageGenerator().generateMessageString(object);
        System.out.println("WebsocketStub is sending:");
        System.out.println(msg);
        System.out.println();
        throw new NoSuchElementException(msg);
    }

    @Override
    public void broadcast(Object object) {

    }

    @Override
    public void sendToGroup(String[] sessionId, Object object) {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @OnMessage
    public void onText(String message, String session) {
        String sessionId = session;
        System.out.println(session + " send message " + message);
        System.out.println();
        EncapsulatingMessage msg = getGson().fromJson(message, EncapsulatingMessage.class);
        getHandler().processMessage(sessionId, msg.getMessageType(), msg.getMessageData());
    }

}
