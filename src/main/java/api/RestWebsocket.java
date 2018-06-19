package api;

import api.dto.BaseResultDto;
import api.dto.UpdateScoreRequestDto;
import api.dto.ValidateRequestDto;

public class RestWebsocket extends BaseRestClient implements IRestWebsocket{

    private final String url = "http://localhost:8095/submarine/rest";

    public String getBaseUr()
    {
        return url;
    }


    public boolean validateLogin(int userId, String validationKey) {
        ValidateRequestDto dto = new ValidateRequestDto(userId, validationKey);
        String query = "/validate";
        BaseResultDto result =  executeQueryPost(dto, query, BaseResultDto.class);
        return result.isSuccess();
    }

    public void updateScore(int userId) {
        UpdateScoreRequestDto dto = new UpdateScoreRequestDto(userId);
        String query = "/updateScore";
        BaseResultDto result =  executeQueryPut(dto, query, BaseResultDto.class);
        //return result.isSuccess();
    }
}
