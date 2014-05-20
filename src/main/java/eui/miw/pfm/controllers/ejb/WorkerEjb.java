/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.WorkerEntity;
import eui.miw.pfm.util.moks.entities.TasksEntityMock;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Fred Peña
 * @author Jose M Villar
 */
public class WorkerEjb {

    public void delete(final WorkerEntity workerEntity) {
        AbstractDAOFactory.getFactory().getWorkerDAO().delete(workerEntity);
    }

    public void update(final WorkerEntity workerEntity) {
        AbstractDAOFactory.getFactory().getWorkerDAO().update(workerEntity);

    }

    public void create(final WorkerEntity workerEntity) {
        AbstractDAOFactory.getFactory().getWorkerDAO().create(workerEntity);
    }

    public List<WorkerEntity> getWorkers() {
        return AbstractDAOFactory.getFactory().getWorkerDAO().findAll();
    }

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
}
