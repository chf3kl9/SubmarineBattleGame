package api;

import api.dto.ScoreResultDto;
import api.dto.TopScoreResultDto;
import com.google.gson.Gson;
import api.dto.BaseResultDto;
import api.dto.LoginResultDto;

import java.util.List;

public class ResponseHelper {

    private ResponseHelper(){}

    private static final Gson gson = new Gson();

    public static String getErrorResponseString()
    {
        BaseResultDto response = new BaseResultDto();
        response.setSuccess(false);
        return gson.toJson(response);
    }

    public static String getLoginResultDtoResponseString(int id, String token)
    {
        LoginResultDto response = new LoginResultDto(id, token);
        response.setSuccess(true);
        return gson.toJson(response);
    }

    public static String getSuccessResponseString(){
        BaseResultDto response = new BaseResultDto();
        response.setSuccess(true);
        return gson.toJson(response);
    }

    public static String getTopXResponseString(List<String> topx){
        TopScoreResultDto response = new TopScoreResultDto(topx);
        response.setSuccess(true);
        return gson.toJson(response);
    }

    public static String getScoreResponseString(String score){
        ScoreResultDto response = new ScoreResultDto(score);
        response.setSuccess(true);
        return gson.toJson(response);
    }

}
