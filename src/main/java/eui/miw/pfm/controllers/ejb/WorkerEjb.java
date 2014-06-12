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

    public int update(final WorkerEntity worker) {
        if (!this.isUniqueDNI(worker, false)) {
            return 1; //NOPMD
        } else if (!this.isUniqueEmail(worker, false)) {
            return 2; //NOPMD
        } else if (!this.isUniqueGitUser(worker, false)) {
            return 3; //NOPMD
        } else {
            AbstractDAOFactory.getFactory().getWorkerDAO().update(worker);
            return 0;
        }

    }

    public int create(final WorkerEntity worker) {
        if (!this.isUniqueDNI(worker, true)) {
            return 1; //NOPMD
        } else if (!this.isUniqueEmail(worker, true)) {
            return 2; //NOPMD
        } else if (!this.isUniqueGitUser(worker, true)) {
            return 3; //NOPMD
        } else {
            AbstractDAOFactory.getFactory().getWorkerDAO().create(worker);
            return 0;
        }
    }

    public List<WorkerEntity> getWorkers() {
        return AbstractDAOFactory.getFactory().getWorkerDAO().findAll();
    }

    private boolean isUniqueDNI(final WorkerEntity worker, final boolean create) {

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

    private boolean isUniqueEmail(final WorkerEntity worker, final boolean create) {

        final List<WorkerEntity> list = AbstractDAOFactory.getFactory().getWorkerDAO().findAll();

        if (create) {
            for (WorkerEntity r : list) {
                if (r.getEmail().equals(worker.getEmail())) {
                    return false; //NOPMD
                }
            }
        } else {
            for (WorkerEntity r : list) {
                if (r.getEmail().equals(worker.getEmail()) && !r.getId().equals(worker.getId())) {
                    return false; //NOPMD
                }
            }
        }
        return true;
    }

    private boolean isUniqueGitUser(final WorkerEntity worker, final boolean create) {

        final List<WorkerEntity> list = AbstractDAOFactory.getFactory().getWorkerDAO().findAll();

        if (create) {
            for (WorkerEntity r : list) {
                if (r.getGitUser().equals(worker.getGitUser())) {
                    return false; //NOPMD
                }
            }
        } else {
            for (WorkerEntity r : list) {
                if (r.getGitUser().equals(worker.getGitUser()) && !r.getId().equals(worker.getId())) {
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
