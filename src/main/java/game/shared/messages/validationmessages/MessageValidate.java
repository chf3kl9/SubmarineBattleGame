package game.shared.messages.validationmessages;

public class MessageValidate {
    String validationKey;
    int userId;

    public MessageValidate(String validationKey, int userId){
        this.validationKey = validationKey;
        this.userId = userId;
    }

    public String getValidationKey(){
        return validationKey;
    }

    public int getUserId() {
        return userId;
    }
}
