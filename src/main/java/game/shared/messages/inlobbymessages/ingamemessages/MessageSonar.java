package game.shared.messages.inlobbymessages.ingamemessages;

import game.shared.messages.inlobbymessages.MessageInLobby;
import game.models.GameObject;

import java.util.List;

public class MessageSonar extends MessageInLobby{
    List<GameObject> vision;

    public MessageSonar(List<GameObject> vision) {
        super();
        this.vision = vision;
    }

    public List<GameObject> getVision() {
        return vision;
    }
}
