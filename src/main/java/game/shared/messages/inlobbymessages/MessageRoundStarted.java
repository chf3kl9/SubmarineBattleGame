package game.shared.messages.inlobbymessages;

import game.models.interfaces.ISubmarine;
import game.models.Submarine;

public class MessageRoundStarted extends MessageInLobby{
    Submarine submarine;

    public MessageRoundStarted(Submarine submarine){
        super();
        this.submarine = submarine;
    }

    public ISubmarine getSubmarine() {
        return submarine;
    }
}
