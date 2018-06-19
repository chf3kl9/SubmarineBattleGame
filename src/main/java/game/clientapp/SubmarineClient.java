package game.clientapp;

import game.clientside.clienthandler.ClientHandler;
import game.clientside.clienthandler.IClientHandler;
import game.clientside.clienthandler.IClientHandlerGUI;
import game.clientside.clientmessagegenerator.ClientMessageGenerator;
import game.clientside.clientmessagegenerator.IClientMessageGenerator;
import game.clientside.clientmessagehandlers.ClientMessageHandlerFactory;
import game.clientside.clientmessageprocessor.ClientMessageProcessor;
import game.clientside.clientwebsockets.ClientWebsocket;
import game.clientside.clientwebsockets.IClientWebsocket;
import game.clientside.gui.GUI;
import game.clientside.gui.IGUI;
import game.shared.messagehandlers.IMessageHandlerFactory;
import game.shared.messageprocessor.IMessageProcessor;

public class SubmarineClient {

    public static void main(String[] args){
        IClientWebsocket socket = new ClientWebsocket();
        IClientMessageGenerator messageGenerator = new ClientMessageGenerator(socket);
        IMessageHandlerFactory factory = new ClientMessageHandlerFactory();
        IClientHandler clientHandler = new ClientHandler(messageGenerator);
        IMessageProcessor messageProcessor = new ClientMessageProcessor(factory, clientHandler);
        socket.setMessageHandler(messageProcessor);
        socket.start();

        IGUI gui = new GUI();
        gui.setClientHandler((IClientHandlerGUI) clientHandler);
        clientHandler.setGui(gui);
    }
}
