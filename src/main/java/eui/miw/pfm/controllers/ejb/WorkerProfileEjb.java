/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.WorkerEntity;
import eui.miw.pfm.util.moks.profile.TasksEntityMock;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Fred Peña
 * @author Clemencio Morales
 * @author Pepe Bustamante
 */
public class WorkerProfileEjb {

    public List<ProjectEntity> findProjects(final WorkerEntity worker) {

        final List<ProjectEntity> lTaskProjects = new CopyOnWriteArrayList<>();
        final List<ProjectEntity> lWorkerTaskProjects = new CopyOnWriteArrayList<>();

        for (TasksEntityMock te : worker.getTaskMock()) {
            lWorkerTaskProjects.add(te.getProject());
        }

        for (ProjectEntity pe : new CopyOnWriteArrayList<>(lWorkerTaskProjects)) {
            try {
                for (TasksEntityMock te : pe.getTaskMock()) {
                    if (!te.getWorker().equals(worker)) {
                        pe.removeTask(te);
                    }
                }
            } catch (ConcurrentModificationException cme) {
            }
            lTaskProjects.add(pe);

        }

        return lTaskProjects;
    }

//    public static void main(String[] args) {
//        WorkerProfileEjb profileEjb = new WorkerProfileEjb();
//        for (ProjectEntity pe : profileEjb.findProjects(AbstractDAOFactory.getFactory().getWorkerDAO().read(2))) {
//            System.out.println("Project: " + pe);
//            for (TasksEntityMock te : pe.getTaskMock()) {
//                System.out.println("Task: " + te);
//            }
//
//        }
//    }

}
