package api;

import java.util.List;

public interface IRestClient {

    String login(String username, String password);
    boolean register(String username, String password);
    List<String> getx(int amount);
    String getScore(int userId);
}
