package game.shared.messages.inlobbymessages.ingamemessages;

import game.shared.messages.inlobbymessages.MessageInLobby;

public class MessageMove extends MessageInLobby{

    int rotate;
    int accelerate;

    public int getAccelerate() {
        return accelerate;
    }

    public int getRotate() {
        return rotate;
    }

    public MessageMove(int lobbyId, int rotate, int accelerate) {
        super(lobbyId);
        this.accelerate = accelerate;
        this.rotate = rotate;
    }
}
