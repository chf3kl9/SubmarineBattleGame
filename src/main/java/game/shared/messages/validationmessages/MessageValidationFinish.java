package game.shared.messages.validationmessages;


public class MessageValidationFinish {

    boolean success;

    public MessageValidationFinish(boolean success){
        this.success = success;
    }

    public boolean getSuccess() {
        return success;
    }
}
