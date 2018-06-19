package game.shared.messages.inlobbymessages.ingamemessages;

import game.shared.messages.inlobbymessages.MessageInLobby;

public class MessageGameState extends MessageInLobby{
    boolean gameState;

    public MessageGameState(int lobbyId, boolean gameState) {
        super(lobbyId);
        this.gameState = gameState;
    }

    public boolean getGameState() {
        return gameState;
    }
}
