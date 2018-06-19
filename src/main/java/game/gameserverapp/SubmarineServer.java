package game.gameserverapp;


import api.IRestWebsocket;
import api.RestWebsocket;
import game.gameserverside.serverhandler.IServerHandlerMessenger;
import game.gameserverside.serverhandler.ServerHandler;
import game.gameserverside.servermessagegenerator.IServerMessageGenerator;
import game.gameserverside.servermessagegenerator.ServerMessageGenerator;
import game.gameserverside.servermessagehandlers.ServerMessageHandlerFactory;
import game.gameserverside.serverwebsockets.ServerWebsocket;
import game.shared.logger.Logger;
import game.shared.messagehandlers.IMessageHandlerFactory;
import game.shared.messageprocessor.IMessageProcessor;
import game.gameserverside.servermessageprocessor.ServerMessageProcessor;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

public class SubmarineServer {

    private static final int PORT = 8095;


    public static void main(String[] args){
        IRestWebsocket RESTHandler = new RestWebsocket();

        final ServerWebsocket socket = new ServerWebsocket();
        IServerMessageGenerator messageGenerator = new ServerMessageGenerator(socket);

        IMessageHandlerFactory factory = new ServerMessageHandlerFactory();
        IServerHandlerMessenger serverHandler = new ServerHandler(messageGenerator, RESTHandler);
        IMessageProcessor messageHandler = new ServerMessageProcessor(factory, serverHandler);


        socket.setMessageHandler(messageHandler);

        Server webSocketServer = new Server();
        ServerConnector connector = new ServerConnector(webSocketServer);
        connector.setPort(PORT);
        webSocketServer.addConnector(connector);

        // Setup the basic application "context" for this application at "/"
        // This is also known as the handler tree (in jetty speak)
        ServletContextHandler webSocketContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
        webSocketContext.setContextPath("/");
        webSocketServer.setHandler(webSocketContext);

        try {
            // Initialize javax.websocket layer
            ServerContainer wscontainer = WebSocketServerContainerInitializer.configureContext(webSocketContext);

            // Add websocket endpoint to javax.websocket layer
            ServerEndpointConfig config = ServerEndpointConfig.Builder.create(socket.getClass(), socket.getClass().getAnnotation(ServerEndpoint.class).value())
                    .configurator(new ServerEndpointConfig.Configurator() {
                        @Override
                        public <T> T getEndpointInstance(Class<T> endpointClass) {
                            return (T) socket;
                        }
                    })
                    .build();
            wscontainer.addEndpoint(config);
            webSocketServer.start();
            webSocketServer.join();

        } catch (Exception ex) {
            Logger.getInstance().log(ex);
        }
    }
}
