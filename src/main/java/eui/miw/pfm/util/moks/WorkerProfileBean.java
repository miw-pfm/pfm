package eui.miw.pfm.util.moks;

import eui.miw.pfm.controllers.beans.Bean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class WorkerProfileBean extends Bean implements Serializable {

//    private List<Implication> implications;
//
//    public WorkerProfileBean() {
//        populateImplications();
//    }
// 
//    public void populateImplications() {
//        implications = new ArrayList<Implication>();
//
//        Implication proyectox = new Implication("Proyecto X");
//        proyectox.getTasks().add(new TasksEntityMock("Tarea A", 2));
//        proyectox.getTasks().add(new TasksEntityMock("Tarea B", 3));
//        proyectox.getTasks().add(new TasksEntityMock("Tarea C", 8));
//        implications.add(proyectox);
//
//        Implication proyectoy = new Implication("Proyecto Y");
//        proyectoy.getTasks().add(new TasksEntityMock("Tarea M", 7));
//        proyectoy.getTasks().add(new TasksEntityMock("Tarea N", 8));
//        implications.add(proyectoy);
//
//        Implication proyectoz = new Implication("Proyecto Z");
//        proyectoz.getTasks().add(new TasksEntityMock("Tarea O", 1));
//        proyectoz.getTasks().add(new TasksEntityMock("Tarea P", 1));
//        proyectoz.getTasks().add(new TasksEntityMock("Tarea Q", 3));
//        proyectoz.getTasks().add(new TasksEntityMock("Tarea R", 5));
//        implications.add(proyectoz);
//    }
//
//    public List<Implication> getImplications() {
//        return implications;
//    }
}
