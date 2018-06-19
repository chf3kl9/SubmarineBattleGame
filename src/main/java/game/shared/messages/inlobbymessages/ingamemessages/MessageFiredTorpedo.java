package game.shared.messages.inlobbymessages.ingamemessages;

import game.shared.messages.inlobbymessages.MessageInLobby;
import game.models.Torpedo;

import java.util.List;

public class MessageFiredTorpedo extends MessageInLobby{
    List<Torpedo> torpedoes;

    public MessageFiredTorpedo(List<Torpedo> torpedoes) {
        super();
        this.torpedoes = torpedoes;
    }

    public List<Torpedo> getTorpedoes() {
        return torpedoes;
    }
}
