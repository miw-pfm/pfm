/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.WorkerEntity;
import java.util.List;

/**
 *
 * @author Roberto Amor
 * @author Jose M Villar
 */
public class WorkersListEjb {

    public List<WorkerEntity> obtainWorkers(final ProjectEntity project) {
        return project.getWorkers();
    }

    public void remove(final ProjectEntity project, final WorkerEntity worker) {
        project.removeWorker(worker);
        AbstractDAOFactory.getFactory().getProjectDAO().update(project);
        AbstractDAOFactory.getFactory().getWorkerDAO().update(worker);
    }

    public void add(final ProjectEntity project, final WorkerEntity worker) {
        project.addWorker(worker);
        AbstractDAOFactory.getFactory().getProjectDAO().update(project);
        AbstractDAOFactory.getFactory().getWorkerDAO().update(worker);
    }

    public List<WorkerEntity> obtainWorkersNotProject(final ProjectEntity project) {
        final List<WorkerEntity> listAll = AbstractDAOFactory.getFactory().getWorkerDAO().findAll();
        listAll.removeAll(project.getWorkers());
        return listAll;
    }
}
