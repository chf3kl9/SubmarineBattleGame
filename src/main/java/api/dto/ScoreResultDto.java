package api.dto;


public class ScoreResultDto extends BaseResultDto{

    private String playerScore;

    public void setPlayerScore(String playerScore) {
        this.playerScore = playerScore;
    }

    public String getPlayerScore() {
        return playerScore;
    }

    public ScoreResultDto(String playerScore){
        this.playerScore = playerScore;
    }
}
