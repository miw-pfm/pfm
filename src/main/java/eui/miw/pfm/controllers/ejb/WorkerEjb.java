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
 * @author aw0591
 */
public class WorkerEjb {

    public void delete(WorkerEntity worker) {
        AbstractDAOFactory.getFactory().getWorkerDAO().delete(worker);
    }
    
}
