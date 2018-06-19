package game.gameserverside;

import api.IRestWebsocket;
import game.models.Settings;
import game.gameserverside.serverhandler.IServerHandlerMessenger;
import game.gameserverside.serverhandler.ServerHandler;
import game.gameserverside.servermessagegenerator.IServerMessageGenerator;
import game.gameserverside.servermessagegenerator.ServerMessageGenerator;
import game.gameserverside.servermessagehandlers.ServerMessageHandlerFactory;
import game.gameserverside.servermessageprocessor.ServerMessageProcessor;
import game.shared.encapsulatingmessagegenerator.EncapsulatingMessageGenerator;
import game.shared.encapsulatingmessagegenerator.IEncapsulatingMessageGenerator;
import game.shared.messagehandlers.IMessageHandlerFactory;
import game.shared.messages.inlobbymessages.ingamemessages.MessageFire;
import game.shared.messages.inlobbymessages.ingamemessages.MessageMove;
import game.shared.messages.inlobbymessages.MessageLobbyClose;
import game.shared.messages.inlobbymessages.MessageRoundStart;
import game.shared.messages.lobbymessages.MessageLobbyCreate;
import stubs.RESTWebSocketServerHandlerStub;
import stubs.ServerWebsocketStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

public class ServerHandlerTest {

    IEncapsulatingMessageGenerator encapsulatingMessageGenerator;
    ServerWebsocketStub socket;
    IServerMessageGenerator messageGenerator;
    IMessageHandlerFactory factory;
    IServerHandlerMessenger serverHandler;
    ServerMessageProcessor messageHandler;


    @Before
    public void prepareTest(){
        IRestWebsocket RESTHandler = new RESTWebSocketServerHandlerStub();
        encapsulatingMessageGenerator = new EncapsulatingMessageGenerator();
        socket = new ServerWebsocketStub();
        messageGenerator = new ServerMessageGenerator(socket);

        factory = new ServerMessageHandlerFactory();
        serverHandler = new ServerHandler(messageGenerator, RESTHandler);
        messageHandler = new ServerMessageProcessor(factory, serverHandler);
        socket.setMessageHandler(messageHandler);
        messageHandler.sessionValidated("Client1");
    }

    @Test
    public void openLobby(){
        MessageLobbyCreate message = new MessageLobbyCreate("Test", "Test", "test", new Settings(1, 1, 1, 1, 1, 1), 1);
        String msg = encapsulatingMessageGenerator.generateMessageString(message);
        try{
            socket.onText(msg, "Client1");
        }
        catch(NoSuchElementException e){
            Assert.assertEquals(e.getMessage(), "{\"messageType\":\"public class game.shared.messages.lobbymessages.MessageLobbyCreated\",\"messageData\":\"{\\\"lobbyId\\\":1}\"}");
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void startLobby(){
        openLobby();

        MessageRoundStart message1 = new MessageRoundStart(1);
        String msg = encapsulatingMessageGenerator.generateMessageString(message1);
        try{
            socket.onText(msg, "Client1");
        }
        catch(NoSuchElementException e){
            Assert.assertEquals(e.getMessage(), "{\"messageType\":\"public class game.shared.messages.inlobbymessages.MessageRoundStarted\",\"messageData\":\"{\\\"submarine\\\":{\\\"player\\\":{\\\"sessionId\\\":\\\"Client1\\\",\\\"userId\\\":1,\\\"playerName\\\":\\\"Test\\\"},\\\"engineVector\\\":{\\\"x\\\":0.0,\\\"y\\\":0.0},\\\"rotationSpeed\\\":1.0,\\\"reloadTime\\\":1000.0,\\\"reloadedTime\\\":0.0,\\\"acceleration\\\":1.0,\\\"torpedoSpeed\\\":1.0,\\\"drag\\\":0.5,\\\"angle\\\":0.0,\\\"coördinate\\\":{\\\"x\\\":10,\\\"y\\\":10},\\\"velocityVector\\\":{\\\"x\\\":0.0,\\\"y\\\":0.0}},\\\"lobbyId\\\":-1}\"}");
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void moveSubmarine(){
        startLobby();

        MessageMove message1 = new MessageMove(1, 1, 1);
        String msg = encapsulatingMessageGenerator.generateMessageString(message1);
        try{
            socket.onText(msg, "Client1");
        }
        catch(NoSuchElementException e){
            Assert.assertEquals(e.getMessage(), "{\"messageType\":\"public class game.shared.messages.inlobbymessages.ingamemessages.MessageMovedSubmarine\",\"messageData\":\"{\\\"submarine\\\":{\\\"player\\\":{\\\"sessionId\\\":\\\"Client1\\\",\\\"userId\\\":1,\\\"playerName\\\":\\\"Test\\\"},\\\"engineVector\\\":{\\\"x\\\":0.99984765,\\\"y\\\":0.017452404},\\\"rotationSpeed\\\":1.0,\\\"reloadTime\\\":1000.0,\\\"reloadedTime\\\":0.0,\\\"acceleration\\\":1.0,\\\"torpedoSpeed\\\":1.0,\\\"drag\\\":0.5,\\\"angle\\\":1.0,\\\"coördinate\\\":{\\\"x\\\":10,\\\"y\\\":10},\\\"velocityVector\\\":{\\\"x\\\":0.0,\\\"y\\\":0.0}},\\\"lobbyId\\\":-1}\"}");
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void fireTorpedo(){
        startLobby();

        MessageFire message1 = new MessageFire(1);
        String msg = encapsulatingMessageGenerator.generateMessageString(message1);
        try{
            socket.onText(msg, "Client1");
        }
        catch(NoSuchElementException e){
            Assert.assertEquals(e.getMessage(), "{\"messageType\":\"public class game.shared.messages.inlobbymessages.ingamemessages.MessageFiredTorpedo\",\"messageData\":\"{\\\"torpedoes\\\":[{\\\"angle\\\":0.0,\\\"coördinate\\\":{\\\"x\\\":10,\\\"y\\\":10},\\\"velocityVector\\\":{\\\"x\\\":0.99999994,\\\"y\\\":0.0}}],\\\"lobbyId\\\":-1}\"}");
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }
}
