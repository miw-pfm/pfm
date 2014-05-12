package eui.miw.pfm.util.moks;

import eui.miw.pfm.util.moks.entities.TasksEntityMock;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Implication {

    private String name;

    private List<TasksEntityMock> tasks = new ArrayList<>();

    public List<TasksEntityMock> getTasks() {
        return tasks;
    }

    public void setTasks(List<TasksEntityMock> tasks) {
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
