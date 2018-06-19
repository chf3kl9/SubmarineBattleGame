package api.dto;

public class LoginResultDto extends BaseResultDto{

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public int getId() {return id; }

    public void setId(int id) {this.id = id; }

    private int id;


    public LoginResultDto(int id, String token){
        this.id = id;
        this.token = token;
    }

}