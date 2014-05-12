/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.WorkerEntity;
import java.util.List;

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
}
