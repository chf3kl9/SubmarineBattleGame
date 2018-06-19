package api.dto;

import java.util.List;

public class TopScoreResultDto extends BaseResultDto{

    private List<String> topx;

    public List<String> getTopx() {
        return topx;
    }

    public void setTopx(List<String> topx) {
        this.topx = topx;
    }

    public TopScoreResultDto(List<String> topx){
        this.topx = topx;
    }
}
