package api.dto;

public class UpdateScoreRequestDto extends BaseRequestDto {

    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UpdateScoreRequestDto(int userId){
        this.userId = userId;
    }
}
