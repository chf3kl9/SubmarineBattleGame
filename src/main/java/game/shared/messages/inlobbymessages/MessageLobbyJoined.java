package game.shared.messages.inlobbymessages;

import game.models.Settings;

public class MessageLobbyJoined extends MessageInLobby{
    Settings settings;

    public MessageLobbyJoined(int lobbyId, Settings settings) {
        super(lobbyId);
        this.settings = settings;
    }

    public Settings getSettings() {
        return settings;
    }
}
