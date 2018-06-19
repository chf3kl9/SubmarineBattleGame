package game.shared.encapsulatingmessagegenerator;

import game.shared.messages.EncapsulatingMessage;

public interface IEncapsulatingMessageGenerator {
    EncapsulatingMessage generateMessage(Object content);
    String generateMessageString(Object object);
}
