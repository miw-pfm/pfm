package eui.miw.pfm.util.moks;
import java.io.Serializable;

public class Stats implements Serializable {
    
    private String season;
    private int goals;
    
    public Stats() {}

    public Stats(String season, int goals) {
        this.season = season;
        this.goals = goals;
    }

     public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}