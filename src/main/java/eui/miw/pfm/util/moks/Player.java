package eui.miw.pfm.util.moks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Player {

    private String name;

     private List<Stats> stats = new ArrayList<>();

    public List<Stats> getStats() {
        return stats;
    }

    public void setStats(List<Stats> stats) {
        this.stats = stats;
    }

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
