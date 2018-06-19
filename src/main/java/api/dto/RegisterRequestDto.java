package api.dto;

public class RegisterRequestDto extends BaseRequestDto {

    private String userName;
    private String password;

    public RegisterRequestDto(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String hashedPassword) {
        this.password = password;
    }

    public RegisterRequestDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
