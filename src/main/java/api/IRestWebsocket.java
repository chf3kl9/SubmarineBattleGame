package api;

public interface IRestWebsocket {
    boolean validateLogin(int userId, String validationKey);
    void updateScore(int userId);
}
