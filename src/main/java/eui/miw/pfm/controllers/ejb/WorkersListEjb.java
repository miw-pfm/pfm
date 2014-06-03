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

    public List<WorkerEntity> obtainWorkersNotProject(final ProjectEntity project) {
        final List<WorkerEntity> listAll = AbstractDAOFactory.getFactory().getWorkerDAO().findAll();
        listAll.removeAll(project.getWorkers());
        return listAll;
    }
}
