package game.shared.messages.lobbymessages;

import java.util.List;

public class MessageLobbies{
    List<String> lobbies;

    public MessageLobbies(List<String> lobbies){
        this.lobbies = lobbies;
    }

    public List<String> getLobbies() {
        return lobbies;
    }
}
