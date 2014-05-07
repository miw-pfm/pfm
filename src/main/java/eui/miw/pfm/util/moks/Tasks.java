package eui.miw.pfm.util.moks;
import java.io.Serializable;

public class Tasks implements Serializable {
    
    private String name;
    private int time;
    
    public Tasks() {}

    public Tasks(String name, int time) {
        this.name = name;
        this.time = time;
    }

     public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}