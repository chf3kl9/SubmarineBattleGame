package api.dto;

public class ValidateRequestDto extends BaseRequestDto {

    private int userId;

    private String validationToken;

    public int getUserId() {
        return userId;
    }

    public String getValidationToken() {
        return validationToken;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setValidationToken(String validationToken) {
        this.validationToken = validationToken;
    }

    public ValidateRequestDto(int userId, String validationToken){
        this.userId = userId;
        this.validationToken = validationToken;
    }
}
