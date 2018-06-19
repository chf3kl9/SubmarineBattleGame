package api.dto;

public class LoginRequestDto extends BaseRequestDto {

    private String userName;
    private String password;

    public LoginRequestDto(){}

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

    public LoginRequestDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
