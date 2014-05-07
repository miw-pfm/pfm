package eui.miw.pfm.util.moks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Implication {

    private String name;

    private List<Tasks> tasks = new ArrayList<>();

    public List<Tasks> getTasks() {
        return tasks;
    }

    public void setTasks(List<Tasks> tasks) {
        this.tasks = tasks;
    }

    public Implication() {
    }

    public Implication(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
