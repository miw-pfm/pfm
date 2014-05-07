/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.WorkerEntity;

/**
 *
 * @author Fred Peña
 */
public class WorkerEjb {

    public WorkerEjb() {
        super();
    }

    public void update(final WorkerEntity workerEntity1) {
        AbstractDAOFactory.getFactory().getWorkerDAO().update(workerEntity1);

    }

}
