package api;

import api.dto.*;

import java.util.List;

public class RestClient extends BaseRestClient implements IRestClient {

    private final String url = "http://localhost:8095/submarine/rest";

    public String getBaseUr()
    {
        return url;
    }

    public String login(String username, String password)
    {
        LoginRequestDto dto = new LoginRequestDto(username, password);
        String query = "/login";
        LoginResultDto result =  executeQueryPost(dto, query, LoginResultDto.class);
        return result.getToken();
    }

    public boolean register(String username, String password){
        RegisterRequestDto dto = new RegisterRequestDto(username, password);
        String query = "/register";
        BaseResultDto result = executeQueryPost(dto, query, BaseResultDto.class);
        return result.isSuccess();
    }

    public List<String> getx(int amount) {
        String query = "/getTopScore/"+amount;
        TopScoreResultDto result = executeQueryGet(query, TopScoreResultDto.class);
        return result.getTopx();
    }

    public String getScore(int userId) {
        String query = "/getScore/"+userId;
        ScoreResultDto result = executeQueryGet(query, ScoreResultDto.class);
        return result.getPlayerScore();
    }
}
