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

    public void delete(final WorkerEntity worker) {
        AbstractDAOFactory.getFactory().getWorkerDAO().delete(worker);
    }

    public boolean update(final WorkerEntity worker) {
        if (this.isUnique(worker, false)) {
            AbstractDAOFactory.getFactory().getWorkerDAO().update(worker);
            return true;//NOPMD
        } else {
            return false;
        }

    }

    public boolean create(final WorkerEntity worker) {
        if (this.isUnique(worker, true)) {
            AbstractDAOFactory.getFactory().getWorkerDAO().create(worker);
            return true;//NOPMD
        } else {
            return false;
        }

    }

    public List<WorkerEntity> getWorkers() {
        return AbstractDAOFactory.getFactory().getWorkerDAO().findAll();
    }

    private boolean isUnique(final WorkerEntity worker, final boolean create) {

        final List<WorkerEntity> list = AbstractDAOFactory.getFactory().getWorkerDAO().findAll();

        if (create) {
            for (WorkerEntity r : list) {
                if (r.getDni().equals(worker.getDni())) {
                    return false; //NOPMD
                }
            }
        } else {
            for (WorkerEntity r : list) {
                if (r.getDni().equals(worker.getDni()) && !r.getId().equals(worker.getId())) {
                    return false; //NOPMD
                }
            }
        }
        return true;
    }

    public WorkerEntity getWorker(final int code) {
        return AbstractDAOFactory.getFactory().getWorkerDAO().read(code);
    }
}
