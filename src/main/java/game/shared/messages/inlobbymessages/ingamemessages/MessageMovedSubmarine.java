package game.shared.messages.inlobbymessages.ingamemessages;

import game.models.interfaces.ISubmarine;
import game.shared.messages.inlobbymessages.MessageInLobby;
import game.models.Submarine;

public class MessageMovedSubmarine extends MessageInLobby{
    Submarine submarine;

    public MessageMovedSubmarine(Submarine submarine) {
        super();
        this.submarine = submarine;
    }

    public ISubmarine getSubmarine() {
        return submarine;
    }
}
