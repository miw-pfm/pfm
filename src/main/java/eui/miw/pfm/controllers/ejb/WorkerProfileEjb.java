/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.WorkerEntity;
import java.util.List;

/**
 *
 * @author Fred Peña
 * @author Clemencio Morales
 * @author Pepe Bustamante
 */
public class WorkerProfileEjb {

    public List<ProjectEntity> findProjects(final WorkerEntity worker) {

//        final List<ProjectEntity> projects = worker.getProjects();
//        for (ProjectEntity pe : projects) {
//            for (TasksEntityMock te : pe.getTaskMock()) {
//                pe.getTaskMock().clear();
//                if (te.getWorker().equals(worker)) {
//                    pe.addTask(te);
//                }
//            }
//            projects.add(pe);
//        }
        return worker.getProjects();
    }

}
